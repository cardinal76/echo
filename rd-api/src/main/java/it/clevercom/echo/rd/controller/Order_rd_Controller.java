package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.jpa.CreateRequestProcessor;
import it.clevercom.echo.common.jpa.CriteriaRequestProcessor;
import it.clevercom.echo.common.jpa.UpdateRequestProcessor;
import it.clevercom.echo.common.jpa.specification.BooleanSpecification;
import it.clevercom.echo.common.jpa.specification.DateIntervalSpecification;
import it.clevercom.echo.common.jpa.specification.StringSpecification;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.common.model.factory.ResponseFactory;
import it.clevercom.echo.common.util.DateUtil;
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
import it.clevercom.echo.rd.repository.IOrderLog_rd_Repository;
import it.clevercom.echo.rd.repository.IOrderService_rd_Repository;
import it.clevercom.echo.rd.repository.IOrder_rd_Repository;
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
	private IWorkStatus_rd_Repository repo_ws;

	@Autowired
	private IWorkPriority_rd_Repository repo_wp;
	
	@Autowired
	private IService_rd_Repository repo_s;
	
	@Autowired
	private OrderValidator orderValidator; 
	
	@Autowired
	private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;

	private final Logger logger = Logger.getLogger(this.getClass());

	// used to bind it in exception message
	public static final String entity_name = "Order";
	public static final String entity_id = "idorder";
	public static final String entity_cd1 = "code";
	public static final String entity_s_name = "Service";
	public static final String entity_os_name = "Ordered Service";
	public static final String entity_o_name = "Organization Unit";
	
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
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting"), entity_name, entity_id, id.toString()));
		
		// find entity
		Order entity = repo.findOne(id);
		
		// check if entity has been found
		if (entity == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.returning.response"), entity_name, entity_id, id.toString()));
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
			@RequestParam(defaultValue = entity_id, required = false) String field) throws Exception {
		
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
				
		// validate
		validator.validateStatus(status);
		validator.validatePriority(priority);
		validator.validateSort(sort);
		validator.validateSortField(field, Order.class, entity_name);
		
		// create processor
		CriteriaRequestProcessor<IOrder_rd_Repository, Order, OrderDTO> rp = 
				new CriteriaRequestProcessor<IOrder_rd_Repository, Order, OrderDTO>(repo, 
						rdDozerMapper, 
						OrderDTO.class, 
						entity_name, 
						criteria, 
						sort, 
						field, 
						page, 
						size,
						env);
		
		// get dates
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
				DateIntervalSpecification<Order, Order> interval = new DateIntervalSpecification<Order, Order>(null, t1, t2, WorkStatusDateFieldDecoder.decodeDateFieldFromWorkStatus(WorkStatusEnum.getInstanceFromCodeValue(statusItems[i])));
				current = Specifications.where(current).and(st).and(interval);
				if (i==0) {
					rp.addAndSpecification(Specifications.where(current));
				} else {
					rp.addOrSpecification(Specifications.where(current));
				}
			}
		} else {
			// create standard specification based on date interval and standard field name
			// parse long parameter to Date Object
			DateIntervalSpecification<Order, Order> interval = new DateIntervalSpecification<Order, Order>(null, t1, t2, WorkStatusDateFieldDecoder.decodeDateFieldFromWorkStatus(null));
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
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
		
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
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTONullIdd(order, entity_id);
		orderValidator.validateCreateRequest(order);

		// create the processor
		CreateRequestProcessor<IOrder_rd_Repository, Order, OrderDTO> rp = 
				new CreateRequestProcessor<IOrder_rd_Repository, Order, OrderDTO>(repo,
						rdDozerMapper,
						Order.class,
						entity_name,
						getLoggedUser(request),
						order,
						env);
				
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
		
		// process order insert
		Order newOrder = rp.create();		
		
		Set<OrderService> orderServices = newOrder.getOrderServices();
		for (OrderService orderService : orderServices) {
			orderService.setUserupdate(getLoggedUser(request));
			repo_os.saveAndFlush(orderService);
		}
		
		order.setIdOrder(newOrder.getIdorder()); 

		// create standard response
		String message = MessageFormat.format(env.getProperty("echo.api.crud.saved"), entity_name);
		return ResponseFactory.getCreateResponseDTO(order, entity_name, message);		
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

		// FIXME once a service is canceled it cannot be activate anymore (costraint violation)
		// TODO allocate order on rd_modality_daily_allocation (only on schedulation)
		
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
						
		// validate
		validator.validateDTOIdd(order, entity_name);		
		orderValidator.validateUpdateRequest(order);
	
		// update changed services only if order status is lower in order value than accepted
		Order orderToUpdate = repo.findOne(order.getIdOrder());
		if (WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).order() <= WorkStatusEnum.ACCEPTED.order()) {
			// create two maps (active, inactive)
			Map<Long, BaseObjectDTO> oldActiveServiceMap = new HashMap<Long, BaseObjectDTO>();
			Map<Long, BaseObjectDTO> oldInactiveServiceMap = new HashMap<Long, BaseObjectDTO>();
				
			for (OrderService orderService : orderToUpdate.getOrderServices()) {
				if (orderService.getActive().equals(Boolean.valueOf(true))) {
					oldActiveServiceMap.put(orderService.getIdorderservice(),rdDozerMapper.map(orderService.getService(), BaseObjectDTO.class));
				} else if (orderService.getActive().equals(Boolean.valueOf(false))) {
					oldInactiveServiceMap.put(orderService.getIdorderservice(),rdDozerMapper.map(orderService.getService(), BaseObjectDTO.class));
				}
			}
	
			// save active services
			for (OrderedServiceDTO current : order.getServices()) {
				if (!oldActiveServiceMap.containsValue(current)) {
					if (!oldInactiveServiceMap.containsValue(current)) {
						OrderService orderService = new OrderService();
						orderService.setActive(true);
						orderService.setAddedreason(current.getAddedReason());
						orderService.setOrder(orderToUpdate);
						orderService.setService(rdDozerMapper.map(current, Service.class));
						orderService.setUserupdate(getLoggedUser(request));
						repo_os.saveAndFlush(orderService);
					} else {
						// reactivate service
						Service serv = repo_s.findOne(Long.valueOf(current.getId()));
						OrderService orderService = repo_os.findByOrderAndService(orderToUpdate, serv);
						orderService.setUserupdate(getLoggedUser(request));
						orderService.setActive(false);
						orderService.setCanceledreason(null);
						orderService.setAddedreason(current.getAddedReason());
						repo_os.saveAndFlush(orderService);
					}
				}
			}
			
			// save inactive services
			for (OrderedServiceDTO current : order.getCanceledServices()) {
				if (oldActiveServiceMap.containsValue(current)) {
					Service serv = repo_s.findOne(Long.valueOf(current.getId()));
					OrderService orderService = repo_os.findByOrderAndService(orderToUpdate, serv);
					orderService.setUserupdate(getLoggedUser(request));
					orderService.setActive(false);
					orderService.setCanceledreason(current.getCancelReason());
					repo_os.saveAndFlush(orderService);
				}
			}
		}
		
		// create and save log
		OrderLog log = rdDozerMapper.map(orderToUpdate, OrderLog.class);
		log.setUserupdate(getLoggedUser(request));
		repo_ol.saveAndFlush(log);
		
		// create processor
		UpdateRequestProcessor<IOrder_rd_Repository, Order, OrderDTO> rp = 
				new UpdateRequestProcessor<IOrder_rd_Repository, Order, OrderDTO>(repo, 
						rdDozerMapper,
						entity_name,
						entity_id,
						getLoggedUser(request), 
						order, 
						env);
					
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, order.getIdd().toString()));

		return rp.process();
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
	// TODO on entity Order rejectReason and cancelReason must be the same db data type
	@Transactional("rdTm")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<OrderDTO> delete(@PathVariable Long id, 
			@RequestParam(defaultValue = "", required = false) String rejectReason, 
			@RequestParam(defaultValue = "", required = false) String cancelReason, 
			HttpServletRequest request) throws Exception {
		
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
								
		// validate
		validator.validateId(id, entity_name);
		orderValidator.validateDeleteRequest(id, rejectReason, cancelReason);
			
		// build dto to deactivate
		OrderDTO toDeactivate = new OrderDTO();
		toDeactivate.setIdOrder(id);
		toDeactivate.setCanceledDate(new Date().getTime());
		toDeactivate.setCancelReason((StringUtils.isNullEmptyWhiteSpaceOnly(cancelReason)) ? null : cancelReason);
		toDeactivate.setRejectReason((StringUtils.isNullEmptyWhiteSpaceOnly(rejectReason)) ? null : rejectReason);
		
		// create processor		
		UpdateRequestProcessor<IOrder_rd_Repository, Order, OrderDTO> rp = 
				new UpdateRequestProcessor<IOrder_rd_Repository, Order, OrderDTO>(repo, 
						rdDozerMapper,
						entity_name,
						entity_id,
						getLoggedUser(request), 
						toDeactivate, 
						env);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, id.toString()));

		// return response
		return rp.enable(false);
	}
}
