package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
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
	private IWorkStatus_rd_Repository repo_ws;

	@Autowired
	private IWorkPriority_rd_Repository repo_wp;
	
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

	/**
	 * Get order by id
	 * @param id
	 * @return
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
	 * @param criteria
	 * @param page
	 * @param size
	 * @param sort
	 * @param field
	 * @return
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
			@RequestParam(defaultValue = "null", required = false) String criteria,
			@RequestParam(defaultValue = "*", required = false) String name,
			@RequestParam(defaultValue = "*", required = false) String surname,
			@RequestParam(defaultValue = "*", required = false) String taxCode,
			@RequestParam(defaultValue = "*", required = false) String channel,
			@RequestParam(defaultValue = "*", required = false) Long originorgid,
			@RequestParam(defaultValue = "*", required = false) Long targetorgid,
			@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam(defaultValue = "15", required = false) int size,
			@RequestParam(defaultValue = "asc", required = false) String sort,
			@RequestParam(defaultValue = "idorder", required = false) String field) throws Exception {
		
		// parse long parameter to Date Object
		final Date t1 = DateUtil.getStartOfDay(new Date(from));
		final Date t2 = DateUtil.getEndOfDay(new Date(to));
		
		// check status code
		WorkStatus statusEntity = new WorkStatus();
		if ((!status.equals("*")) && (!WorkStatusEnum.contains(status))) {
			throw new BadRequestException(
					MessageFormat.format(env.getProperty("echo.api.exception.search.params.wrongparam"),
							env.getProperty("echo.api.crud.fields.workstatus"),
							WorkStatusEnum.enumValuesToString()));
		} else if ((!status.equals("*")) && (WorkStatusEnum.contains(status))) {
			statusEntity = repo_ws.findByCode(WorkStatusEnum.valueOf(status).code());
		}
		final Long statusId = statusEntity.getIdworkstatus();
		
		// check priority code
		WorkPriority priorityEntity = new WorkPriority();
		if ((!priority.equals("*")) && (!WorkPriorityEnum.contains(priority))) {
			throw new BadRequestException(
					MessageFormat.format(env.getProperty("echo.api.exception.search.params.wrongparam"),
							env.getProperty("echo.api.crud.fields.workpriority"),
							WorkPriorityEnum.enumValuesToString()));
		} else if ((!priority.equals("*")) && (WorkPriorityEnum.contains(priority))) {
			priorityEntity = repo_wp.findByCode(WorkPriorityEnum.valueOf(priority).code());
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
		
		// check patient name and surname
		if ((!name.equals("*")) && (!surname.equals("*"))) {
			Specification<Order> sp = new Specification<Order>() {
				@Override
				public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate surname_p = cb.equal(cb.lower(root.<Patient>get("patient").<String>get("surname")), surname.toLowerCase());
					Predicate name_p = cb.equal(cb.lower(root.<Patient>get("patient").<String>get("name")), name.toLowerCase());
					return cb.and(surname_p, name_p);
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
		
		// check channel
		if (!channel.equals("*")) {
			Specification<Order> sp = new Specification<Order>() {
				@Override
				public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.equal(cb.lower(root.<String>get("acquisitionChannel")), channel.toLowerCase());
				}
			};
			
			// add to specification list
			spec =  Specifications.where(spec).and(sp);
		}
		
		// check target organization unit
		if (!targetorgid.equals("*")) {
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
		if (!originorgid.equals("*")) {
			Specification<Order> sp = new Specification<Order>() {
				@Override
				public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.equal(root.<OrganizationUnit>get("organizationUnitByOriginorganizationunitid").<Long>get("idorganizationunit"), originorgid);
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
	 * @param order
	 * @param request
	 * @return
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
	 * @param order
	 * @param request
	 * @return
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
		
		// create log
		OrderLog log = rdDozerMapper.map(oldValueEntity, OrderLog.class);
		log.setUserupdate(username);
		
		// if an entity with given id is not found in DB throw record not found
		if (oldValueEntity == null)
			throw new RecordNotFoundException(entity_name, entity_id, order.getIdOrder().toString());

		// validate create request
		this.validateUpdateRequest(order, oldValueEntity);

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
	 * @param id
	 * @param request
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<OrderDTO> delete(@PathVariable Long id, HttpServletRequest request) {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);

		// get entity to update
		Order entity = repo.findOne(id);
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
	 * Validate a create order request
	 * 
	 * @author luca
	 * @category request validation
	 * @param order passed to the create method
	 * @since 1.2.0
	 */
	private void validateCreateRequest(OrderDTO order) throws ValidationException {
		// these fields should not be inserted
		// in the create request and must be empty or null
		ValidationExceptionDTO exceptions = new ValidationExceptionDTO();

		// idOrder should not be present here
		if (order.getIdOrder() != null) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.idorder"),
					env.getProperty("echo.api.crud.validation.mustbeempty"));
		}

		// schedule date should not be present here
		if (order.getScheduledDate() != null) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.scheduledate"),
					env.getProperty("echo.api.crud.validation.mustbeempty"));
		}

		// acceptance date should not be present here
		if (order.getAcceptanceDate() != null) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.acceptancedate"),
					env.getProperty("echo.api.crud.validation.mustbeempty"));
		}

		// reject reason should not be present here
		if (order.getRejectReason() != null) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.rejectreason"),
					env.getProperty("echo.api.crud.validation.mustbeempty"));
		}

		// work session should not be present here
		if ((order.getWorkSession() != null)) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.worksession"),
					env.getProperty("echo.api.crud.validation.mustbeempty"));
		}

		// work status should be equal to request
		if ((!order.getWorkStatus().getCode().equals(WorkStatusEnum.REQUESTED.code()))) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.workstatus"),
					MessageFormat.format(env.getProperty("echo.api.crud.validation.mustbe"),
							env.getProperty("echo.api.crud.fields.workstatus"), WorkStatusEnum.REQUESTED.code()));
		}

		// check if we have some services has been selected
		if (order.getServices().size() <= 0) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.service"),
					MessageFormat.format(env.getProperty("echo.api.crud.validation.emptylist"),
							env.getProperty("echo.api.crud.fields.service")));
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
	 * 
	 * @author luca
	 * @category request validation
	 * @param order passed to the create method
	 * @since 1.2.0
	 */
	private void validateUpdateRequest(OrderDTO order, Order orderToUpdate) throws ValidationException {

//		switch (WorkStatusEnum.valueOf(order.getWorkStatus().getName())) {
//		case ACCEPTED:
//			break;
//		case SCHEDULED:
//			break;
//		case CANCELED:
//			break;
//		case REQUESTED:
//			break;
//		default:
//			break;
//		}
	}
}
