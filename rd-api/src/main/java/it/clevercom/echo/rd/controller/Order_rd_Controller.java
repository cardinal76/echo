package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
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
import it.clevercom.echo.common.jpa.CriteriaRequestProcessor;
import it.clevercom.echo.common.jpa.specification.BooleanSpecification;
import it.clevercom.echo.common.jpa.specification.DateIntervalSpecification;
import it.clevercom.echo.common.jpa.specification.StringSpecification;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.common.util.DateUtil;
import it.clevercom.echo.common.util.JwtTokenUtils;
import it.clevercom.echo.common.util.StringUtils;
import it.clevercom.echo.rd.component.OrderValidator;
import it.clevercom.echo.rd.component.Validator;
import it.clevercom.echo.rd.enums.WorkPriorityEnum;
import it.clevercom.echo.rd.enums.WorkStatusEnum;
import it.clevercom.echo.rd.jpa.specification.OrganizationUnitSpecification;
import it.clevercom.echo.rd.jpa.specification.WorkPrioritySpecification;
import it.clevercom.echo.rd.jpa.specification.WorkStatusSpecification;
import it.clevercom.echo.rd.model.dto.BaseObjectDTO;
import it.clevercom.echo.rd.model.dto.OrderDTO;
import it.clevercom.echo.rd.model.dto.OrderedServiceDTO;
import it.clevercom.echo.rd.model.entity.Order;
import it.clevercom.echo.rd.model.entity.OrderLog;
import it.clevercom.echo.rd.model.entity.OrderService;
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
	private OrderValidator orderValidator; 
	
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
	public static String entity_name = "Order";
	public static String entity_id = "idorder";
	public static String entity_cd1 = "code";
	public static String entity_s_name = "Service";
	public static String entity_os_name = "Ordered Service";
	public static String entity_o_name = "Organization Unit";
	
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
			// iterate status item and create the right specification
			for (int i = 0; i < statusItems.length; i++) {
				Specifications<Order> current = null;
				// create status specification
				WorkStatusSpecification<Order> st = new WorkStatusSpecification<Order>(repo_ws.findByCode(WorkStatusEnum.getInstanceFromCodeValue(statusItems[i]).code()).getIdworkstatus());
				// create interval specification based on right date field
				DateIntervalSpecification<Order> interval = new DateIntervalSpecification<Order>(t1, t2, WorkStatusDateFieldDecoder.decodeDateFieldFromWorkStatus(WorkStatusEnum.getInstanceFromCodeValue(statusItems[i])));
				current = Specifications.where(current).and(st).and(interval);
				rp.addOrSpecification(Specifications.where(current));
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
		orderValidator.validateCreateRequest(order);

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

		// TODO allocate order on rd_modality_daily_allocation (only on schedulation)
		
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
		// if an id is not present throw bad request
		if (order.getIdOrder() == null)
			throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.missing.id"), entity_name));

		// find entity to update (oldValue)
		Order oldValueEntity = repo.findOne(order.getIdOrder());
		
		// if an entity with given id is not found in DB throw record not found
		if (oldValueEntity == null)
			throw new RecordNotFoundException(entity_name, entity_id, order.getIdOrder().toString());
		
		// get all ordered service
		Set<OrderService> allOrderService = oldValueEntity.getOrderServices();
		logger.info("allOrderService size :" + allOrderService.size());
		
		// validate create request
		orderValidator.validateUpdateRequest(order, oldValueEntity);

		// create log
		OrderLog log = rdDozerMapper.map(oldValueEntity, OrderLog.class);
		log.setUserupdate(getLoggedUser(request));
		
		// save created date
		Date created = oldValueEntity.getCreated();
		
		// save old value to a dto
		OrderDTO oldValueDTO = rdDozerMapper.map(oldValueEntity, OrderDTO.class);
		
		// map new value to entity
		rdDozerMapper.map(order, oldValueEntity);

		// set technical field
		oldValueEntity.setUserupdate(getLoggedUser(request));
		oldValueEntity.setUpdated(new Date());
		oldValueEntity.setCreated(created);
		oldValueEntity.setActive(true);

		/**
		 * SAVE data
		 */
		
		// save updated entity
		Order newValueEntity = repo.saveAndFlush(oldValueEntity);
		
		// create two maps (active, inactive)
		Map<Long, BaseObjectDTO> oldActiveServiceMap = new HashMap<Long, BaseObjectDTO>();
		Map<Long, BaseObjectDTO> oldInactiveServiceMap = new HashMap<Long, BaseObjectDTO>();
		
		for (OrderService orderService : allOrderService) {
			if (orderService.getActive().equals(Boolean.valueOf(true))) {
				oldActiveServiceMap.put(orderService.getIdorderservice(),rdDozerMapper.map(orderService.getService(), BaseObjectDTO.class));
			} else if (orderService.getActive().equals(Boolean.valueOf(false))) {
				oldInactiveServiceMap.put(orderService.getIdorderservice(),rdDozerMapper.map(orderService.getService(), BaseObjectDTO.class));
			}
		}
		
		// 
		Set<OrderedServiceDTO> active = order.getServices();
		for (OrderedServiceDTO current : active) {
			if (!oldActiveServiceMap.containsValue(current)) {
				OrderService orderService = new OrderService();
				orderService.setActive(true);
				orderService.setAddedreason(current.getAddedReason());
				orderService.setCreated(new Date());
				orderService.setOrder(newValueEntity);
				orderService.setService(rdDozerMapper.map(current, Service.class));
				orderService.setUpdated(new Date());
				orderService.setUserupdate(getLoggedUser(request));
				
				// save new item
				repo_os.saveAndFlush(orderService);
			}
		}
		
		Set<OrderedServiceDTO> inactive = order.getCanceledServices();
		for (OrderedServiceDTO current : inactive) {
			if (oldActiveServiceMap.containsValue(current)) {
				// repo_os.findOne(id);
				OrderService orderService = rdDozerMapper.map(current, OrderService.class);
				// save new item
				orderService.setUserupdate(getLoggedUser(request));
				orderService.setActive(false);
				
				repo_os.saveAndFlush(orderService);
			}
		}
		
//		// check new item
//		for (BaseObjectDTO service : order.getServices()) {
//			if (!oldService.containsValue(service)) {
//				OrderService orderService = new OrderService();
//				orderService.setActive(true);
//				orderService.setCreated(new Date());
//				orderService.setOrder(newValueEntity);
//				orderService.setService(rdDozerMapper.map(service, Service.class));
//				orderService.setUpdated(new Date());
//				orderService.setUserupdate(getLoggedUser(request));
//				
//				// save new item
//				repo_os.saveAndFlush(orderService);
//			}
//		}
//
//		// check disabled item
//		for (OrderService orderService : oldOrderService) {
//			BaseObjectDTO current = rdDozerMapper.map(orderService.getService(), BaseObjectDTO.class);
//			if (!order.getServices().contains(current)) {
//				// save new item
//				orderService.setUserupdate(getLoggedUser(request));
//				orderService.setActive(false);
//				repo_os.saveAndFlush(orderService);
//			}
//		}
		
		
		
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
		orderValidator.validateDeleteRequest(entity, rejectReason, cancelReason);
				
		OrderDTO oldValueDTO = rdDozerMapper.map(entity, OrderDTO.class);
		
		// get canceled workstatus
		WorkStatus workStatus = repo_ws.findByCode(WorkStatusEnum.CANCELED.code());

		// update entity
		entity.setWorkStatus(workStatus);
		entity.setActive(false);
		entity.setUserupdate(username);
		entity.setCancelreason(cancelReason);
		entity.setRejectreason(rejectReason);

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
}
