package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.clevercom.echo.common.exception.model.BadRequestException;
import it.clevercom.echo.common.exception.model.PageNotFoundException;
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.exception.model.ValidationException;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.common.model.dto.response.ValidationExceptionDTO;
import it.clevercom.echo.common.util.DateUtil;
import it.clevercom.echo.common.util.JwtTokenUtils;
import it.clevercom.echo.common.util.StringUtils;
import it.clevercom.echo.rd.enums.OrganizationUnitTypeEnum;
import it.clevercom.echo.rd.enums.WorkPriorityEnum;
import it.clevercom.echo.rd.enums.WorkStatusEnum;
import it.clevercom.echo.rd.model.dto.BaseObjectDTO;
import it.clevercom.echo.rd.model.dto.OrderDTO;
import it.clevercom.echo.rd.model.dto.PagedDTO;
import it.clevercom.echo.rd.model.entity.Order;
import it.clevercom.echo.rd.model.entity.OrderLog;
import it.clevercom.echo.rd.model.entity.OrderService;
import it.clevercom.echo.rd.model.entity.OrganizationUnit;
import it.clevercom.echo.rd.model.entity.Patient;
import it.clevercom.echo.rd.model.entity.Service;
import it.clevercom.echo.rd.model.entity.WorkPriority;
import it.clevercom.echo.rd.model.entity.WorkStatus;
import it.clevercom.echo.rd.model.jpa.helper.SearchCriteria;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationQueryHelper;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationsBuilder;
import it.clevercom.echo.rd.repository.IOrderLog_rd_Repository;
import it.clevercom.echo.rd.repository.IOrderService_rd_Repository;
import it.clevercom.echo.rd.repository.IOrder_rd_Repository;
import it.clevercom.echo.rd.repository.IOrganizationUnit_rd_Repository;
import it.clevercom.echo.rd.repository.IService_rd_Repository;
import it.clevercom.echo.rd.repository.IWorkPriority_rd_Repository;
import it.clevercom.echo.rd.repository.IWorkStatus_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/assets/order")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Order Controller
 * @author luca
 */

public class Order_rd_Controller extends EchoController {

	@Autowired
	private Environment env;

	@Autowired
	private IOrder_rd_Repository repo;
	
	@Autowired
	private IOrderService_rd_Repository repo_os;
	
	@Autowired
	private IOrderLog_rd_Repository repo_ol;
	
	@Autowired
	private IOrganizationUnit_rd_Repository repo_ou;
	
	@Autowired
	private IWorkStatus_rd_Repository repo_ws;

	@Autowired
	private IWorkPriority_rd_Repository repo_wp;
	
	@Autowired
	private IService_rd_Repository repo_s;
	
	@Autowired
	private DozerBeanMapper rdDozerMapper;

	@Value("${jwt.token.header}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtils tokenUtils;

	private final Logger logger = Logger.getLogger(this.getClass());

	// used to bind it in exception message
	private static String entity_name = "Order";
	private static String entity_id = "idorder";
	private static String entity_cd1 = "code";
	private static String entity_s_name = "Service";
	private static String entity_o_name = "Organization Unit";
	
	/**
	 * Get order by id
	 * @author luca
	 * @category standard get order by id REST method
	 * @param id
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody OrderDTO get(@PathVariable Long id) throws Exception {
		Order entity = repo.findOne(id);
		if (entity == null)
			throw new RecordNotFoundException(entity_name, entity_id, id.toString());		
		return rdDozerMapper.map(entity, OrderDTO.class);
	}

	/**
	 * Get order list by criteria with pagination
	 * @author luca
	 * @category standard get order by criteria REST method
	 * @param criteria
	 * @param page
	 * @param size
	 * @param sort
	 * @param field
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value = "", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<OrderDTO> getByCriteria(
			@RequestParam(defaultValue = "today_start", required = false) Long from,
			@RequestParam(defaultValue = "today_end", required = false) Long to,
			@RequestParam(defaultValue = "*", required = false) String status,
			@RequestParam(defaultValue = "*", required = false) String priority,
			@RequestParam(defaultValue = "0", required = false) Long originorgid,
			@RequestParam(defaultValue = "0", required = false) Long targetorgid,
			@RequestParam(defaultValue = "null", required = false) String criteria,
			@RequestParam(defaultValue = "*", required = false) String name,
			@RequestParam(defaultValue = "*", required = false) String surname,
			@RequestParam(defaultValue = "*", required = false) String taxCode,
			@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam(defaultValue = "15", required = false) int size,
			@RequestParam(defaultValue = "asc", required = false) String sort,
			@RequestParam(defaultValue = "idorder", required = false) String field) throws Exception {
		
		// parse long parameter to Date Object
		final Date t1 = DateUtil.getStartOfDay(new Date(from));
		final Date t2 = DateUtil.getEndOfDay(new Date(to));
		
		// check status code
		WorkStatus statusEntity = new WorkStatus();
		if ((!status.equals("*")) && (WorkStatusEnum.getInstanceFromCodeValue(status) == null)) {
			throw new BadRequestException(
					MessageFormat.format(env.getProperty("echo.api.exception.search.params.wrongparam"),
							env.getProperty("echo.api.crud.fields.workstatus"),
							WorkStatusEnum.enumValuesToString()));
		} else if ((!status.equals("*")) && (WorkStatusEnum.getInstanceFromCodeValue(status) != null)) {
			statusEntity = repo_ws.findByCode(WorkStatusEnum.getInstanceFromCodeValue(status).code());
		}
		
		final Long statusId = statusEntity.getIdworkstatus();
		
		// check priority code
		WorkPriority priorityEntity = new WorkPriority();
		if ((!priority.equals("*")) && (WorkPriorityEnum.getInstanceFromCodeValue(priority) == null)) {
			throw new BadRequestException(
					MessageFormat.format(env.getProperty("echo.api.exception.search.params.wrongparam"),
							env.getProperty("echo.api.crud.fields.workpriority"),
							WorkPriorityEnum.enumValuesToString()));
		} else if ((!priority.equals("*")) && (WorkPriorityEnum.getInstanceFromCodeValue(priority) != null)) {
			priorityEntity = repo_wp.findByCode(WorkPriorityEnum.getInstanceFromCodeValue(priority).code());
		}
		final Long priorityId = priorityEntity.getIdworkpriority();
		
		// create paged request
		PageRequest request = null;

		if (sort.equalsIgnoreCase("asc")) {
			request = new PageRequest(page - 1, size, Direction.ASC, field);
		} else if (sort.equalsIgnoreCase("desc")) {
			request = new PageRequest(page - 1, size, Direction.DESC, field);
		} else {
			throw new BadRequestException(env.getProperty("echo.api.exception.search.sort.wrongsortparam"));
		}

		// create predicate if criteria is not null
		Page<Order> rs = null;
		Specification<Order> spec = null;
		
		if (!criteria.equals("null")) {
			SpecificationsBuilder<Order, SpecificationQueryHelper<Order>> builder = new SpecificationsBuilder<Order, SpecificationQueryHelper<Order>>();
			Pattern pattern = Pattern.compile(SearchCriteria.pattern);
			Matcher matcher = pattern.matcher(criteria + ",");
			while (matcher.find()) {
				builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
			}
			spec = builder.build();
		}
			
		// set date interval lower limit
		Specification<Order> t1s = new Specification<Order>() {
			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.greaterThanOrEqualTo(root.<Date>get("creationdate"), t1);
			}
		};
		
		// set date interval upper limit
		Specification<Order> t2s = new Specification<Order>() {
			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.lessThanOrEqualTo(root.<Date>get("creationdate"), t2);
			}
		};
		
		// add date interval to specification list
		spec =  Specifications.where(spec).and(t1s).and(t2s);
		
		// check status and add it to specification
		if (!status.equals("*")) {
			Specification<Order> ss = new Specification<Order>() {
				@Override
				public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.equal(root.<WorkStatus>get("workStatus").<Long>get("idworkstatus"), statusId);
				}
			};
			
			// add to specification list
			spec =  Specifications.where(spec).and(ss);
		}
		
		// check status and add it to specification
		if (!priority.equals("*")) {
			Specification<Order> sp = new Specification<Order>() {
				@Override
				public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.equal(root.<WorkPriority>get("workPriority").<Long>get("idworkpriority"), priorityId);
				}
			};
			
			// add to specification list
			spec =  Specifications.where(spec).and(sp);
		}
		
		// check patient name
		if ((!name.equals("*"))) {
			Specification<Order> sp = new Specification<Order>() {
				@Override
				public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate name_p = cb.equal(cb.lower(root.<Patient>get("patient").<String>get("name")), name.toLowerCase());
					return cb.and(name_p);
				}
			};
			
			// add to specification list
			spec =  Specifications.where(spec).and(sp);
		}
		
		// check patient surname
		if ((!surname.equals("*"))) {
			Specification<Order> sp = new Specification<Order>() {
				@Override
				public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate surname_p = cb.equal(cb.lower(root.<Patient>get("patient").<String>get("surname")), surname.toLowerCase());
					return cb.and(surname_p);
				}
			};
			
			// add to specification list
			spec =  Specifications.where(spec).and(sp);
		}
		
		// check patient taxcode
		if (!taxCode.equals("*")) {
			Specification<Order> sp = new Specification<Order>() {
				@Override
				public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.equal(cb.lower(root.<Patient>get("patient").<String>get("taxcode")), taxCode.toLowerCase());
				}
			};
			
			// add to specification list
			spec =  Specifications.where(spec).and(sp);
		}
		
		// check target organization unit
		if (!targetorgid.equals(Long.valueOf(0))) {
			Specification<Order> sp = new Specification<Order>() {
				@Override
				public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.equal(root.<OrganizationUnit>get("organizationUnitByTargetorganizationunitid").<Long>get("idorganizationunit"), targetorgid);
				}
			};
			
			// add to specification list
			spec =  Specifications.where(spec).and(sp);
		}

		// check origin organization unit
		if (!originorgid.equals(Long.valueOf(0))) {
			Specification<Order> sp = new Specification<Order>() {
				@Override
				public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.equal(root.<OrganizationUnit>get("organizationUnitByOriginorganizationunitid").<Long>get("idorganizationunit"), originorgid);
				}
			};
			
			// add to specification list
			spec =  Specifications.where(spec).and(sp);
		}
		
		// include only active if active!false is not specified in criteria
		if ((criteria.equals("null")) || ((!criteria.equals("null")) && (!criteria.contains("active!false")) && (!criteria.contains("active!true")))) {
			Specification<Order> sp = new Specification<Order>() {
				// add standard active=true
				@Override
				public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.isTrue(root.<Boolean>get("active"));
				}
			};
			
			// add to specification list
			spec =  Specifications.where(spec).and(sp);
		}
		
		// find with specification and pagination
		rs = repo.findAll(spec, request);

		int totalPages = rs.getTotalPages();
		long totalElements = rs.getTotalElements();
		List<Order> entities = rs.getContent();

		if (entities.size() == 0)
			throw new PageNotFoundException(entity_name, page);

		// map list
		List<OrderDTO> orderDTOList = new ArrayList<OrderDTO>();
		for (Order s : entities) {
			orderDTOList.add(rdDozerMapper.map(s, OrderDTO.class));
		}

		// assembly dto
		PagedDTO<OrderDTO> dto = new PagedDTO<OrderDTO>();
		dto.setElements(orderDTOList);
		dto.setPageSize(size);
		dto.setCurrentPage(page);
		dto.setTotalPages(totalPages);
		dto.setTotalElements(totalElements);
		return dto;
	}
	
	/**
	 * Add order
	 * @author luca
 	 * @category standard create order REST method
	 * @param order
	 * @param request
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<OrderDTO> add(@RequestBody OrderDTO order, HttpServletRequest request) throws Exception {
		// validate create request
		this.validateCreateRequest(order);

		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);

		// map
		Order entity = rdDozerMapper.map(order, Order.class);

		// add technical field
		entity.setUserupdate(username);

		// save and map to out dto
		entity = repo.saveAndFlush(entity);
		Set<OrderService> associations = entity.getOrderServices();
		for (OrderService orderService : associations) {
			orderService.setUserupdate(username);
			repo_os.saveAndFlush(orderService);
		}
		
		// TODO map entity instead of set ID
		// order = rdDozerMapper.map(entity, OrderDTO.class);
		order.setIdOrder(entity.getIdorder());

		// create standard response
		CreateResponseDTO<OrderDTO> response = new CreateResponseDTO<OrderDTO>();
		response.setEntityName(entity_name);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), entity_name));
		List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
		orderDTOs.add(order);
		response.setNewValue(orderDTOs);

		// return standard response
		return response;
	}

	/**
	 * Update order
	 * @author luca
	 * @category standard update order REST method
	 * @param order
	 * @param request
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<OrderDTO> update(@RequestBody OrderDTO order, HttpServletRequest request) throws Exception {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);

		// if an id is not present throw bad request
		if (order.getIdOrder() == null)
			throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.missing.id"), entity_name));

		// find entity to update (oldValue)
		Order oldValueEntity = repo.findOne(order.getIdOrder());
		
		// if an entity with given id is not found in DB throw record not found
		if (oldValueEntity == null)
			throw new RecordNotFoundException(entity_name, entity_id, order.getIdOrder().toString());
		
		// validate create request
		this.validateUpdateRequest(order, oldValueEntity);

		// create log
		OrderLog log = rdDozerMapper.map(oldValueEntity, OrderLog.class);
		log.setUserupdate(username);
		
		// save created date
		Date created = oldValueEntity.getCreated();
		// save old value to a dto
		OrderDTO oldValueDTO = rdDozerMapper.map(oldValueEntity, OrderDTO.class);
		
		// save old order services
		Set<OrderService> oldOrderService = oldValueEntity.getOrderServices();
		Map<Long, BaseObjectDTO> oldService = new HashMap<Long, BaseObjectDTO>();
		for (OrderService orderService : oldOrderService) {
			oldService.put(orderService.getIdorderservice(),rdDozerMapper.map(orderService.getService(), BaseObjectDTO.class));
		}
		
		// map new value to entity
		rdDozerMapper.map(order, oldValueEntity);

		// set technical field
		oldValueEntity.setUserupdate(username);
		oldValueEntity.setUpdated(new Date());
		oldValueEntity.setCreated(created);
		oldValueEntity.setActive(true);

		/**
		 * SAVE data
		 */
		
		// save updated entity
		Order newValueEntity = repo.saveAndFlush(oldValueEntity);
		
		// check new item
		for (BaseObjectDTO service : order.getServices()) {
			if (!oldService.containsValue(service)) {
				OrderService orderService = new OrderService();
				orderService.setActive(true);
				orderService.setCreated(new Date());
				orderService.setOrder(newValueEntity);
				orderService.setService(rdDozerMapper.map(service, Service.class));
				orderService.setUpdated(new Date());
				orderService.setUserupdate(username);
				
				// save new item
				repo_os.saveAndFlush(orderService);
			}
		}

		// check disabled item
		for (OrderService orderService : oldOrderService) {
			BaseObjectDTO current = rdDozerMapper.map(orderService.getService(), BaseObjectDTO.class);
			if (!order.getServices().contains(current)) {
				// save new item
				orderService.setUserupdate(username);
				orderService.setActive(false);
				repo_os.saveAndFlush(orderService);
			}
		}
		
		// TODO save order logs
		repo_ol.saveAndFlush(log);
		
		
		// TODO map newValueDTO instead of using input order
		// OrderDTO newValueDTO = rdDozerMapper.map(newValueEntity,
		// OrderDTO.class);

		// create standard response
		UpdateResponseDTO<OrderDTO> response = new UpdateResponseDTO<OrderDTO>();
		response.setEntityName(entity_name);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), entity_name));
		// add new dtos values
		List<OrderDTO> newOrderDTOs = new ArrayList<OrderDTO>();
		newOrderDTOs.add(order);
		// newOrderDTOs.add(newValueDTO);
		response.setNewValue(newOrderDTOs);
		// add old dtos values
		List<OrderDTO> oldOrderDTOs = new ArrayList<OrderDTO>();
		oldOrderDTOs.add(oldValueDTO);
		response.setOldValue(oldOrderDTOs);

		// return response
		return response;
	}

	/**
	 * Delete order by id
	 * @author luca
	 * @category standard order delete REST method
	 * @param id
	 * @param request
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<OrderDTO> delete(@PathVariable Long id, 
			@RequestParam(defaultValue = "", required = false) String rejectReason, 
			@RequestParam(defaultValue = "", required = false) String cancelReason, 
			HttpServletRequest request) throws Exception {		
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);

		// get entity to update
		Order entity = repo.findOne(id);
		
		// if an entity with given id is not found in DB throw record not found
		if (entity == null)
			throw new RecordNotFoundException(entity_name, entity_id, entity.getIdorder().toString());
		
		// validate create request
		this.validateDeleteRequest(entity, rejectReason, cancelReason);
				
		OrderDTO oldValueDTO = rdDozerMapper.map(entity, OrderDTO.class);

		// get canceled workstatus
		WorkStatus workStatus = repo_ws.findByCode(WorkStatusEnum.CANCELED.code());

		// update entity
		entity.setWorkStatus(workStatus);
		entity.setActive(false);
		entity.setUserupdate(username);

		repo.saveAndFlush(entity);

		OrderDTO newValueDTO = rdDozerMapper.map(entity, OrderDTO.class);

		// create standard response
		UpdateResponseDTO<OrderDTO> response = new UpdateResponseDTO<OrderDTO>();
		response.setEntityName(entity_name);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), entity_name));
		// add new dtos values
		List<OrderDTO> newOrderDTOs = new ArrayList<OrderDTO>();
		newOrderDTOs.add(newValueDTO);
		// newOrderDTOs.add(newValueDTO);
		response.setNewValue(newOrderDTOs);
		// add old dtos values
		List<OrderDTO> oldOrderDTOs = new ArrayList<OrderDTO>();
		oldOrderDTOs.add(oldValueDTO);
		response.setOldValue(oldOrderDTOs);

		// return response
		return response;
	}

	/*-----------------------------*/
	/* business validation methods */
	/*-----------------------------*/

	/**
	 * Validate a delete order request
	 * @author luca
	 * @category business request validation
	 * @param entity
	 * @param rejectReason
	 * @param cancelReason
	 * @since 1.2.0
	 */
	private void validateDeleteRequest(Order entity, String rejectReason, String cancelReason) throws ValidationException {
		ValidationExceptionDTO exceptions = new ValidationExceptionDTO();
		
		// check that only a reason has been provided by the client		
		if (!((StringUtils.isNotNullNotEmptyNotWhiteSpaceOnly(rejectReason)) ^ (StringUtils.isNotNullNotEmptyNotWhiteSpaceOnly(cancelReason)))) {
			if ((StringUtils.isNullEmptyWhiteSpaceOnly(rejectReason)) && (StringUtils.isNullEmptyWhiteSpaceOnly(cancelReason))) {
				// 0:0
				exceptions.addFieldError(env.getProperty("echo.api.crud.fields.cancelreason") + "or" + env.getProperty("echo.api.crud.fields.rejectreason"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.mustprovideatleastonefield"), 
								env.getProperty("echo.api.crud.fields.cancelreason"), 
								env.getProperty("echo.api.crud.fields.rejectreason")));
			} else if ((StringUtils.isNotNullNotEmptyNotWhiteSpaceOnly(rejectReason)) && (StringUtils.isNotNullNotEmptyNotWhiteSpaceOnly(cancelReason))) {
				// 1:1
				exceptions.addFieldError(env.getProperty("echo.api.crud.fields.cancelreason") + "or" + env.getProperty("echo.api.crud.fields.rejectreason"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.mustprovideatmaximumonefield"), 
								env.getProperty("echo.api.crud.fields.cancelreason"), 
								env.getProperty("echo.api.crud.fields.rejectreason")));
			}
		}
		
		if (exceptions.getFieldErrors().size() > 0) {
			throw new ValidationException(env.getProperty("echo.api.crud.validation.genericmessage"), exceptions);
		} else {
			exceptions = null;
		}
	}
	
	/**
	 * Validate a create order request
	 * @author luca
	 * @category business request validation
	 * @param order passed to the create method
	 * @since 1.2.0
	 * @throws ValidationException
	 */
	private void validateCreateRequest(OrderDTO order) throws ValidationException {
		// these fields must not be inserted
		// in the create request and must be empty or null
		ValidationExceptionDTO exceptions = new ValidationExceptionDTO();

		// idOrder must not be present here
		if (order.getIdOrder() != null) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.idorder"),
					env.getProperty("echo.api.crud.validation.mustbeempty"));
		}
		
		// work session must not be present here
		if ((order.getWorkSession() != null)) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.worksession"),
					env.getProperty("echo.api.crud.validation.mustbeempty"));
		}
		
		// work status must be equal to request
		if ((!order.getWorkStatus().getCode().equals(WorkStatusEnum.REQUESTED.code()))) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.workstatus"),
					MessageFormat.format(env.getProperty("echo.api.crud.validation.mustbe"),
							env.getProperty("echo.api.crud.fields.workstatus"), WorkStatusEnum.REQUESTED.code()));
		}
		
		// work priority must not be null and must correspond to a valid enum code
		if ((order.getWorkPriority()==null) || (StringUtils.isNullEmptyWhiteSpaceOnly(order.getWorkPriority().getCode())) || (WorkPriorityEnum.getInstanceFromCodeValue(order.getWorkPriority().getCode()) == null)) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.workpriority"),
					MessageFormat.format(env.getProperty("echo.api.exception.search.params.wrongparam"),
							env.getProperty("echo.api.crud.fields.workpriority"), WorkPriorityEnum.enumValuesToString()));
		}
			
		// acquisition channel must contains a non empty string
		if (StringUtils.isNullEmptyWhiteSpaceOnly(order.getAcquisitionChannel())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.acquisitionchannel"),
					env.getProperty("echo.api.crud.validation.mustnotbeempty"));
		}

		// creation date must be == today
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(new Date(order.getCreationDate()));
		cal2.setTime(new Date());
		boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
		if(!sameDay) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.creationdate"), 
					MessageFormat.format(env.getProperty("echo.api.crud.validation.mustbetoday"), 
							env.getProperty("echo.api.crud.fields.creationdate"), 
							new Date().toString()));
		}
		
		// schedule date must not be present here
		if (order.getScheduledDate() != null) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.scheduledate"),
					env.getProperty("echo.api.crud.validation.mustbeempty"));
		}

		// acceptance date should not be present here
		if (order.getAcceptanceDate() != null) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.acceptancedate"),
					env.getProperty("echo.api.crud.validation.mustbeempty"));
		}

		// check that target organization unit is an operation unit
		OrganizationUnit ou = repo_ou.findOne(Long.valueOf(order.getTargetOrganizationUnit().getId()));
		if (OrganizationUnitTypeEnum.getInstanceFromCodeValue(ou.getType())==OrganizationUnitTypeEnum.OPERATION_UNIT) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.targetorgunit"), 
					MessageFormat.format(env.getProperty("echo.api.crud.validation.mustbeoftype"), 
							entity_o_name,
							ou.getIdorganizationunit(),
							OrganizationUnitTypeEnum.OPERATION_UNIT.toString()
							));
		}
		
		// check that clinical question is not null
		if (StringUtils.isNullEmptyWhiteSpaceOnly(order.getClinicalQuestion())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.clinicalquestion"), 
					env.getProperty("echo.api.crud.validation.mustnotbeempty"));
		}
		
		// reject reason should not be present here
		if (order.getRejectReason() != null) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.rejectreason"),
					env.getProperty("echo.api.crud.validation.mustbeempty"));
		}
		
		// anamnesys must be not null here
		if (StringUtils.isNullEmptyWhiteSpaceOnly(order.getAnamnesys())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.anamnesys"),
					env.getProperty("echo.api.crud.validation.mustnotbeempty"));
		}
		
		// cancel reason must not be present here
		if (StringUtils.isNullEmptyWhiteSpaceOnly(order.getCancelReason())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.cancelreason"),
					env.getProperty("echo.api.crud.validation.mustbeempty"));
		}
		
		// cancel reason must not be present here
		if (StringUtils.isNullEmptyWhiteSpaceOnly(order.getIdentificationdocument())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.identificationdocument"), 
					env.getProperty("echo.api.crud.validation.mustbeempty"));
		}
		
		// check if some services has been selected
		if (order.getServices().size() == 0) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.service"),
					MessageFormat.format(env.getProperty("echo.api.crud.validation.emptylist"),
							env.getProperty("echo.api.crud.fields.service")));
		} else if (order.getServices().size() > 0) {
			// check that services belongs to same modality
			Long masterModalityType = 0l;
			String masterModalityName = null;
			int i = 0;
			for (BaseObjectDTO element : order.getServices()) {
				Service current = repo_s.findOne(Long.valueOf(element.getId()));
				if (i==0) {
					masterModalityType = current.getModalityType().getIdmodalitytype();
					masterModalityName = current.getModalityType().getType();
				}
				if ((i>0) && (!masterModalityType.equals(current.getModalityType().getIdmodalitytype()))) {
					exceptions.addFieldError(env.getProperty("echo.api.crud.fields.service"), MessageFormat.format(env.getProperty("echo.api.crud.validation.differentkind"), 
							entity_s_name, 
							masterModalityName, 
							masterModalityType));
					break;
				}
				i++;
			}
		}

		// check if a patient has been selected
		if (!((order.getPatient() != null) && (order.getPatient().getIdPatient() != null))) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.patient"),
					env.getProperty("echo.api.crud.validation.mustnotbeempty"));
		}

		if (exceptions.getFieldErrors().size() > 0) {
			throw new ValidationException(env.getProperty("echo.api.crud.validation.genericmessage"), exceptions);
		} else {
			exceptions = null;
		}
	}

	/**
	 * Validate an update order request
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @throws ValidationException
	 */
	private void validateUpdateRequest(OrderDTO updatedOrder, Order orderToUpdate) throws ValidationException {
		// create exception dto
		ValidationExceptionDTO exceptions = new ValidationExceptionDTO();
		
		// --------------------------------------------------------
		// generic constraints valid for all update operation
		// all the following update are not permitted at this stage
		// --------------------------------------------------------
		
		// acquisition channel cannot never be updated
		if (!updatedOrder.getAcquisitionChannel().equals(orderToUpdate.getAcquisitionchannel())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.acquisitionchannel"), 
					MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdate"), 
							entity_name,
							env.getProperty("echo.api.crud.fields.acquisitionchannel")));
		}
		
		// creation date cannot never be updated
		if (!updatedOrder.getCreationDate().equals((Long)orderToUpdate.getCreationdate().getTime())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.creationdate"),
					MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdate"), 
							entity_name,
							env.getProperty("echo.api.crud.fields.creationdate")));
		}
		
		// requesting physician cannot never be updated
		if (!updatedOrder.getRequestingPhysician().equals(orderToUpdate.getRequestingphysician())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.requestingphysician"),
					MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdate"), 
							entity_name,
							env.getProperty("echo.api.crud.fields.requestingphysician")));
		}
		
		// clinical question cannot never be updated
		if (!updatedOrder.getClinicalQuestion().equals(orderToUpdate.getClinicalquestion())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.clinicalquestion"),
					MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdate"), 
							entity_name,
							env.getProperty("echo.api.crud.fields.clinicalquestion")));
		}
		
		// anamnesys cannot never be updated
		if (!updatedOrder.getAnamnesys().equals(orderToUpdate.getAnamnesys())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.anamnesys"),
					MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdate"), 
							entity_name,
							env.getProperty("echo.api.crud.fields.anamnesys")));
		}
		
		// patient cannot never be updated
		if (!updatedOrder.getPatient().getIdPatient().equals(orderToUpdate.getPatient().getIdpatient())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.patient"),
					MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdate"), 
							entity_name,
							env.getProperty("echo.api.crud.fields.patient")));
		}
		
		// --------------------------
		// generic service validation
		// --------------------------
		
		// services must not be empty
		if (updatedOrder.getServices().size() == 0) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.service"),
					MessageFormat.format(env.getProperty("echo.api.crud.validation.emptylist"),
							env.getProperty("echo.api.crud.fields.service")));
		} else if (updatedOrder.getServices().size() > 0) {
			// get old service modality type (check only the first element)
			Long oldModalityType = 0l;
			String oldModalityName = null;
			for (OrderService current : orderToUpdate.getOrderServices()) {
				oldModalityType = current.getService().getModalityType().getIdmodalitytype();
				oldModalityName = current.getService().getModalityType().getType();
				break;
			}
			
			// check that updated services belongs to same modality
			Long masterModalityType = 0l;
			String masterModalityName = null;
			int i = 0;
			for (BaseObjectDTO element : updatedOrder.getServices()) {
				Service current = repo_s.findOne(Long.valueOf(element.getId()));
				if (i==0) {
					masterModalityType = current.getModalityType().getIdmodalitytype();
					masterModalityName = current.getModalityType().getType();
				}
				if ((i>0) && (!masterModalityType.equals(current.getModalityType().getIdmodalitytype()))) {
					exceptions.addFieldError(env.getProperty("echo.api.crud.fields.service"), MessageFormat.format(env.getProperty("echo.api.crud.validation.differentkind"), entity_s_name, masterModalityName, masterModalityType));
					break;
				}
				i++;
			}
			
			if (!oldModalityType.equals(masterModalityType)) {
				exceptions.addFieldError(env.getProperty("echo.api.crud.fields.service"), MessageFormat.format(env.getProperty("echo.api.crud.validation.differentkind"), entity_s_name, oldModalityName, oldModalityType));
			}
		}
		
		// ------------------------
		// status switch validation
		// ------------------------
		
		// check order update ratio from source (orderToUpdate) to destination (updateOrder)
		switch (WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode())) {
			// validate a switch from REQUESTED status
			case REQUESTED: {
				exceptions.addFieldErrorList(this.validateFromRequestedStatus(updatedOrder, orderToUpdate));
				break;
			}
			// validate a switch from SCHEDULED status
			case SCHEDULED: {
				exceptions.addFieldErrorList(this.validateFromScheduledStatus(updatedOrder, orderToUpdate));
				break;
			}
			// validate a switch from ACCEPTED status
			case ACCEPTED: {
				exceptions.addFieldErrorList(this.validateFromAcceptedStatus(updatedOrder, orderToUpdate));
				break;
			}
			// validate a switch from EXECUTED status
			case EXECUTING: {
				exceptions.addFieldErrorList(this.validateFromExecutingStatus(updatedOrder, orderToUpdate));
				break;
			}
			// validate a switch from EXECUTED status
			case EXECUTED: {
				exceptions.addFieldErrorList(this.validateFromExecutedStatus(updatedOrder, orderToUpdate));
				break;
			}
			// validate a switch from REPORTED status
			case REPORTING: {
				exceptions.addFieldErrorList(this.validateFromReportingStatus(updatedOrder, orderToUpdate));
				break;
			}
			// validate a switch from REPORTED status
			case REPORTED: {
				exceptions.addFieldErrorList(this.validateFromReportedStatus(updatedOrder, orderToUpdate));
				break;
			}
			// validate a switch from SIGNED status
			case SIGNED: {
				exceptions.addFieldErrorList(this.validateFromSignedStatus(updatedOrder, orderToUpdate));
				break;
			}
			// validate a switch from DELIVERED status
			case DELIVERED: {
				exceptions.addFieldErrorList(this.validateFromDeliveredStatus(updatedOrder, orderToUpdate));
				break;
			}
			// validate a switch from ARCHIVED status
			case ARCHIVED: {
				exceptions.addFieldErrorList(this.validateFromArchivedStatus(updatedOrder, orderToUpdate));
				break;
			}
			// validate a switch from CANCELED status
			case CANCELED: {
				exceptions.addFieldErrorList(this.validateFromCanceledStatus(updatedOrder, orderToUpdate));
				break;
			}
			default: {
				break;
			}
		}
		
		// throws an exception if field error are > 0
		if (exceptions.getFieldErrors().size() > 0) {
			throw new ValidationException(env.getProperty("echo.api.crud.validation.genericmessage"), exceptions);
		} else {
			exceptions = null;
		}
	}

	/**
	 * 
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @return
	 */
	private Map<String, String> validateFromReportingStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @return
	 */
	private Map<String, String> validateFromExecutingStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @return
	 */
	private Map<String, String> validateFromCanceledStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		Map<String, String> out = new HashMap<String, String>();
		// validate status switch 				
		if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.CANCELED) {
			out.put(env.getProperty("echo.api.crud.fields.workstatus"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdatestatus"), 
								entity_name,
								WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).name(),
								WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()).name())
				);		
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.CANCELED) {
			
		}
		return out;
	}

	/**
	 * 
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @return
	 */
	private Map<String, String> validateFromArchivedStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		Map<String, String> out = new HashMap<String, String>();
		// validate status switch 				
		if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.ARCHIVED) {
				out.put(env.getProperty("echo.api.crud.fields.workstatus"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdatestatus"), 
								entity_name,
								WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).name(),
								WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()).name())
				);
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.ARCHIVED) {

		}
		return out;
	}

	/**
	 * 
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @return
	 */
	private Map<String, String> validateFromDeliveredStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		Map<String, String> out = new HashMap<String, String>();
		// validate status switch 				
		if ((WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.DELIVERED) &&  
		    (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.ARCHIVED)) {
				out.put(env.getProperty("echo.api.crud.fields.workstatus"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdatestatus"), 
								entity_name,
								WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).name(),
								WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()).name())
				);
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.DELIVERED) {

		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.ARCHIVED) {

		}
		return out;
	}

	/**
	 * 
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @return
	 */
	private Map<String, String> validateFromSignedStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		Map<String, String> out = new HashMap<String, String>();
		// validate status switch 				
		if ((WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.SIGNED) &&  
		    (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.DELIVERED)) {
				out.put(env.getProperty("echo.api.crud.fields.workstatus"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdatestatus"), 
								entity_name,
								WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).name(),
								WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()).name())
				);
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.SIGNED) {

		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.DELIVERED) {

		}
		return out;
	}

	/**
	 * 
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @return
	 */
	private Map<String, String> validateFromValidatedStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		Map<String, String> out = new HashMap<String, String>();
		// validate status switch 				
		if (((WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.SIGNED))) {
				out.put(env.getProperty("echo.api.crud.fields.workstatus"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdatestatus"), 
								entity_name,
								WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).name(),
								WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()).name())
				);
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.SIGNED) {

		}
		return out;
	}

	/**
	 * 
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @return
	 */
	private Map<String, String> validateFromReportedStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		Map<String, String> out = new HashMap<String, String>();
		// validate status switch 				
		if ((WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.REPORTED)) {
				out.put(env.getProperty("echo.api.crud.fields.workstatus"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdatestatus"), 
								entity_name,
								WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).name(),
								WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()).name())
				);
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.REPORTED) {

		}
		
		return out;
	}

	/**
	 * 
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @return
	 */
	private Map<String, String> validateFromExecutedStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		Map<String, String> out = new HashMap<String, String>();
		// validate status switch 				
		if ((WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.EXECUTED) &&  
		    (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.REPORTED)) {
				out.put(env.getProperty("echo.api.crud.fields.workstatus"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdatestatus"), 
								entity_name,
								WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).name(),
								WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()).name())
				);
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.EXECUTED) {

		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.REPORTED) {

		}
		return out;
	}

	/**
	 * 
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @return
	 */
	private Map<String, String> validateFromAcceptedStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		Map<String, String> out = new HashMap<String, String>();
		// validate status switch 				
		if ((WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.ACCEPTED) &&  
		    (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.EXECUTED) &&
		    (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.CANCELED)) {
				out.put(env.getProperty("echo.api.crud.fields.workstatus"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdatestatus"), 
								entity_name,
								WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).name(),
								WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()).name())
				);
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.ACCEPTED) {

		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.EXECUTED) {

		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.CANCELED) {

		}
		return out;
	}

	/**
	 * 
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @return
	 */
	private Map<String, String> validateFromScheduledStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		Map<String, String> out = new HashMap<String, String>();
		// validate status switch 				
		if ((WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.SCHEDULED) &&  
		    (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.ACCEPTED) &&
		    (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.CANCELED)) {
				out.put(env.getProperty("echo.api.crud.fields.workstatus"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdatestatus"), 
								entity_name,
								WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).name(),
								WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()).name())
				);
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.SCHEDULED) {

		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.ACCEPTED) {

		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.CANCELED) {

		}
		return out;
	}

	/**
	 * 
	 * @author luca
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @since 1.2.0
	 * @return
	 */
	private Map<String, String> validateFromRequestedStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		Map<String, String> out = new HashMap<String, String>();
		// work session must be null
		if (updatedOrder.getWorkSession() != null) {
			out.put(env.getProperty("echo.api.crud.fields.worksession"),
					MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdate"), 
							entity_name,
							env.getProperty("echo.api.crud.fields.worksession")));
		}
		
		// validate status switch
		if ((WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.REQUESTED) &&  
		    (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.SCHEDULED) &&
		    (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) != WorkStatusEnum.CANCELED)) {
				out.put(env.getProperty("echo.api.crud.fields.workstatus"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.cannotupdatestatus"), 
								entity_name,
								WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).name(),
								WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()).name())
				);
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.REQUESTED) {
			
		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.SCHEDULED) {

		} else if (WorkStatusEnum.getInstanceFromCodeValue(updatedOrder.getWorkStatus().getCode()) == WorkStatusEnum.CANCELED) {

		}
		return out;
	}
}
