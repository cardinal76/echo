package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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

import it.clevercom.echo.common.controller.EchoController;
import it.clevercom.echo.common.exception.model.BadRequestException;
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.exception.model.ValidationException;
import it.clevercom.echo.common.jpa.CriteriaRequestProcessor;
import it.clevercom.echo.common.jpa.specification.BooleanSpecification;
import it.clevercom.echo.common.jpa.specification.DateIntervalSpecification;
import it.clevercom.echo.common.jpa.specification.StringSpecification;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.common.model.dto.response.ValidationExceptionDTO;
import it.clevercom.echo.common.util.DateUtil;
import it.clevercom.echo.common.util.JwtTokenUtils;
import it.clevercom.echo.common.util.StringUtils;
import it.clevercom.echo.rd.component.Validator;
import it.clevercom.echo.rd.enums.OrganizationUnitTypeEnum;
import it.clevercom.echo.rd.enums.WorkPriorityEnum;
import it.clevercom.echo.rd.enums.WorkStatusEnum;
import it.clevercom.echo.rd.jpa.specification.OrganizationUnitSpecification;
import it.clevercom.echo.rd.jpa.specification.WorkPrioritySpecification;
import it.clevercom.echo.rd.jpa.specification.WorkStatusSpecification;
import it.clevercom.echo.rd.model.dto.BaseObjectDTO;
import it.clevercom.echo.rd.model.dto.OrderDTO;
import it.clevercom.echo.rd.model.entity.Order;
import it.clevercom.echo.rd.model.entity.OrderLog;
import it.clevercom.echo.rd.model.entity.OrderService;
import it.clevercom.echo.rd.model.entity.OrganizationUnit;
import it.clevercom.echo.rd.model.entity.Patient;
import it.clevercom.echo.rd.model.entity.Service;
import it.clevercom.echo.rd.model.entity.WorkStatus;
import it.clevercom.echo.rd.repository.IOrderLog_rd_Repository;
import it.clevercom.echo.rd.repository.IOrderService_rd_Repository;
import it.clevercom.echo.rd.repository.IOrder_rd_Repository;
import it.clevercom.echo.rd.repository.IOrganizationUnit_rd_Repository;
import it.clevercom.echo.rd.repository.IService_rd_Repository;
import it.clevercom.echo.rd.repository.IWorkPriority_rd_Repository;
import it.clevercom.echo.rd.repository.IWorkStatus_rd_Repository;
import it.clevercom.echo.rd.util.WorkStatusDateFieldDecoder;

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
	
	@Autowired
	private Validator validator;

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
			@RequestParam(defaultValue = "system_start", required = false) Long from,
			@RequestParam(defaultValue = "system_end", required = false) Long to,
			@RequestParam(defaultValue = "*", required = false) String status,
			@RequestParam(defaultValue = "false", required = false) String h,
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
		
		// check enum string params
		validator.validateStatus(status);
		validator.validatePriority(priority);
		validator.validateSort(sort);
		
		CriteriaRequestProcessor<IOrder_rd_Repository, Order, OrderDTO> rp = 
				new CriteriaRequestProcessor<IOrder_rd_Repository, Order, OrderDTO>(repo, 
						rdDozerMapper, 
						OrderDTO.class, 
						entity_name, 
						criteria, 
						sort, 
						field, 
						page, 
						size);
		
		final Date t1 = DateUtil.getStartOfDay(new Date(from));
		final Date t2 = DateUtil.getEndOfDay(new Date(to));
		
		// if there's a selected status, create and set status specification
		if (!status.equals("*")) {
			// start decoding status
			String[] statusItems = StringUtils.split(status, "\\|");
			// create new array of specs
			Specifications[] cumulativeStatus = new Specifications[statusItems.length];
			// iterate status item and create the right specification
			for (int i = 0; i < statusItems.length; i++) {
				Specifications<Order> current = null;
				// create status specification
				WorkStatusSpecification<Order> st = new WorkStatusSpecification<Order>(repo_ws.findByCode(WorkStatusEnum.getInstanceFromCodeValue(statusItems[i]).code()).getIdworkstatus());
				// create interval specification based on right date field
				DateIntervalSpecification<Order> interval = new DateIntervalSpecification<Order>(t1, t2, WorkStatusDateFieldDecoder.decodeDateFieldFromWorkStatus(WorkStatusEnum.getInstanceFromCodeValue(statusItems[i])));
				current = Specifications.where(current).and(st).and(interval);
				cumulativeStatus[i] = Specifications.where(current);
				rp.addOrSpecification(cumulativeStatus[i]);
			}			
		} else {
			// create standard specification based on date interval and standard field name
			// parse long parameter to Date Object
			DateIntervalSpecification<Order> interval = new DateIntervalSpecification<Order>(t1, t2, WorkStatusDateFieldDecoder.decodeDateFieldFromWorkStatus(null));
			rp.addAndSpecification(interval);
		}
		
		// if there's a selected priority, create and set priority specification
		if (!priority.equals("*")) {
			WorkPrioritySpecification<Order> pr = new WorkPrioritySpecification<Order>(repo_wp.findByCode(WorkPriorityEnum.getInstanceFromCodeValue(priority).code()).getIdworkpriority());
			rp.addAndSpecification(pr);
		}

		// if there's a selected patient name, create and set patient name specification
		if ((!name.equals("*"))) {
			StringSpecification<Order, Patient> n = new StringSpecification<Order, Patient>("patient", "name", name.toLowerCase()); 
			rp.addAndSpecification(n);
		}

		// if there's a selected patient surname, create and set patient surname specification
		if ((!surname.equals("*"))) {
			StringSpecification<Order, Patient> sn = new StringSpecification<Order, Patient>("patient", "surname", surname.toLowerCase());
			rp.addAndSpecification(sn);
		}
		
		// if there's a selected patient taxcode, create and set patient surname specification
		if (!taxCode.equals("*")) {
			StringSpecification<Order, Patient> tc = new StringSpecification<Order, Patient>("patient", "taxcode", taxCode.toLowerCase());
			rp.addAndSpecification(tc);
		}
		
		// if there's a selected target organization unit, create and set target organization unit specification
		if (!targetorgid.equals(Long.valueOf(0))) {
			OrganizationUnitSpecification<Order> tou = new OrganizationUnitSpecification<Order>("organizationUnitByTargetorganizationunitid", targetorgid);
			rp.addAndSpecification(tou);
		}

		// if there's a selected origin organization unit, create and set origin organization unit specification
		if (!originorgid.equals(Long.valueOf(0))) {
			OrganizationUnitSpecification<Order> oou = new OrganizationUnitSpecification<Order>("organizationUnitByOriginorganizationunitid", originorgid);
			rp.addAndSpecification(oou);
		}
		
		// include only active if active!false is not specified in criteria
		if ((criteria.equals("null")) || ((!criteria.equals("null")) && (!criteria.contains("active!false")) && (!criteria.contains("active!true")))) {
			BooleanSpecification<Order, Order> act = new BooleanSpecification<Order, Order>(null, "active", true);
			rp.addAndSpecification(act);
		}
		
		// process data request
		return rp.process();
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
		
		// work status must be equal to requested code
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

		// check that target organization unit is a valid operation unit
		if ((order.getTargetOrganizationUnit() == null) || (StringUtils.isNullEmptyWhiteSpaceOnly(order.getTargetOrganizationUnit().getId()))) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.targetorgunit"), 
					env.getProperty("echo.api.crud.validation.invalidelement"));
		} else {
			OrganizationUnit ou = repo_ou.findOne(Long.valueOf(order.getTargetOrganizationUnit().getId()));
		
			if (ou == null) {
				exceptions.addFieldError(env.getProperty("echo.api.crud.fields.targetorgunit"), 
						env.getProperty("echo.api.crud.validation.invalidelement"));
			} else if (!OrganizationUnitTypeEnum.getInstanceFromCodeValue(ou.getCode()).equals(OrganizationUnitTypeEnum.OPERATION_UNIT)) {
				exceptions.addFieldError(env.getProperty("echo.api.crud.fields.targetorgunit"), 
						MessageFormat.format(env.getProperty("echo.api.crud.validation.mustbeoftype"), 
								entity_o_name,
								ou.getIdorganizationunit(),
								OrganizationUnitTypeEnum.OPERATION_UNIT.toString()
								));
			}
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
		if (StringUtils.isNotNullNotEmptyNotWhiteSpaceOnly(order.getCancelReason())) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.cancelreason"),
					env.getProperty("echo.api.crud.validation.mustbeempty"));
		}
		
		// identification document must not be present here
		if (StringUtils.isNotNullNotEmptyNotWhiteSpaceOnly(order.getIdentificationdocument())) {
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
		if ((orderToUpdate.getRequestingphysician() != null) &&  (!updatedOrder.getRequestingPhysician().equals(orderToUpdate.getRequestingphysician()))) {
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
	 * @category business request validation
	 * @param updatedOrder
	 * @param orderToUpdate
	 * @return
	 */
	private Map<String, String> validateFromReportingStatus(OrderDTO updatedOrder, Order orderToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @category business request validation
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
