package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import it.clevercom.echo.rd.model.dto.ModalityGroupDTO;
import it.clevercom.echo.rd.model.dto.ModalityTypeDailyAllocationDTO;
import it.clevercom.echo.rd.model.dto.OrderDTO;
import it.clevercom.echo.rd.model.dto.OrderHL7DTO;
import it.clevercom.echo.rd.model.dto.OrderedServiceDTO;
import it.clevercom.echo.rd.model.dto.WorkSessionDTO;
import it.clevercom.echo.rd.model.entity.Modality;
import it.clevercom.echo.rd.model.entity.ModalityDailyAllocation;
import it.clevercom.echo.rd.model.entity.ModalityType;
import it.clevercom.echo.rd.model.entity.Order;
import it.clevercom.echo.rd.model.entity.OrderLog;
import it.clevercom.echo.rd.model.entity.OrderService;
import it.clevercom.echo.rd.model.entity.Patient;
import it.clevercom.echo.rd.model.entity.Service;
import it.clevercom.echo.rd.model.entity.User;
import it.clevercom.echo.rd.model.entity.VModalitytypeAllocation;
import it.clevercom.echo.rd.model.entity.WorkPriority;
import it.clevercom.echo.rd.model.entity.WorkSession;
import it.clevercom.echo.rd.model.entity.WorkStatus;
import it.clevercom.echo.rd.model.entity.WorkTask;
import it.clevercom.echo.rd.repository.IModalityDailyAllocation_rd_Repository;
import it.clevercom.echo.rd.repository.IModalityType_rd_Repository;
import it.clevercom.echo.rd.repository.IModality_rd_Repository;
import it.clevercom.echo.rd.repository.IOrderLog_rd_Repository;
import it.clevercom.echo.rd.repository.IOrderService_rd_Repository;
import it.clevercom.echo.rd.repository.IOrder_rd_Repository;
import it.clevercom.echo.rd.repository.IPatient_rd_Repository;
import it.clevercom.echo.rd.repository.IService_rd_Repository;
import it.clevercom.echo.rd.repository.IUser_rd_Repository;
import it.clevercom.echo.rd.repository.IVModalitytypeAllocation_rd_Repository;
import it.clevercom.echo.rd.repository.IWorkPriority_rd_Repository;
import it.clevercom.echo.rd.repository.IWorkSession_rd_Repository;
import it.clevercom.echo.rd.repository.IWorkStatus_rd_Repository;
import it.clevercom.echo.rd.repository.IWorkTask_rd_Repository;
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
	private IPatient_rd_Repository repo_p;
	
	@Autowired
	private IService_rd_Repository repo_s;
	
	@Autowired
	private IUser_rd_Repository repo_u;
	
	@Autowired
	private IWorkSession_rd_Repository repo_wss;
	
	@Autowired
	private IWorkTask_rd_Repository repo_wt;
	
	@Autowired
	private IModalityDailyAllocation_rd_Repository repo_a;
	
	@Autowired
	private IModality_rd_Repository repo_m;
	
	@Autowired
	private OrderValidator orderValidator; 
	
	@Autowired
	private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	@PersistenceContext(unitName="rdPU")
	protected EntityManager em;
	
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
		
		// set processor params
		CriteriaRequestProcessor<IOrder_rd_Repository, Order, OrderDTO> processor = getProcessor();
		processor.setCriteria(criteria);
		processor.setPageCriteria(sort, field, page, size);
		
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
					processor.addAndSpecification(Specifications.where(current));
				} else {
					processor.addOrSpecification(Specifications.where(current));
				}
			}
		} else {
			// create standard specification based on date interval and standard field name
			// parse long parameter to Date Object
			DateIntervalSpecification<Order, Order> interval = new DateIntervalSpecification<Order, Order>(null, t1, t2, WorkStatusDateFieldDecoder.decodeDateFieldFromWorkStatus(null));
			processor.addAndSpecification(interval);
		}
		
		// if there's a selected priority, create and set priority specification
		if (!priority.equals("*")) {
			WorkPrioritySpecification<Order> pr = new WorkPrioritySpecification<Order>(repo_wp.findByCode(WorkPriorityEnum.getInstanceFromCodeValue(priority).code()).getIdworkpriority());
			processor.addAndSpecification(pr);
		}

		// if there's a selected patient name, create and set patient name specification
		if ((!name.equals("*"))) {
			StringSpecification<Order, Patient> n = new StringSpecification<Order, Patient>("patient", "name", name.toLowerCase()); 
			processor.addAndSpecification(n);
		}

		// if there's a selected patient surname, create and set patient surname specification
		if ((!surname.equals("*"))) {
			StringSpecification<Order, Patient> sn = new StringSpecification<Order, Patient>("patient", "surname", surname.toLowerCase());
			processor.addAndSpecification(sn);
		}
		
		// if there's a selected patient taxcode, create and set patient surname specification
		if (!taxCode.equals("*")) {
			StringSpecification<Order, Patient> tc = new StringSpecification<Order, Patient>("patient", "taxcode", taxCode.toLowerCase());
			processor.addAndSpecification(tc);
		}
		
		// if there's a selected target organization unit, create and set target organization unit specification
		if (!targetorgid.equals(Long.valueOf(0))) {
			OrganizationUnitSpecification<Order> tou = new OrganizationUnitSpecification<Order>("organizationUnitByTargetorganizationunitid", targetorgid);
			processor.addAndSpecification(tou);
		}

		// if there's a selected origin organization unit, create and set origin organization unit specification
		if (!originorgid.equals(Long.valueOf(0))) {
			OrganizationUnitSpecification<Order> oou = new OrganizationUnitSpecification<Order>("organizationUnitByOriginorganizationunitid", originorgid);
			processor.addAndSpecification(oou);
		}
		
		// include only active if active!false is not specified in criteria
		if ((criteria.equals("null")) || ((!criteria.equals("null")) && (!criteria.contains("active!false")) && (!criteria.contains("active!true")))) {
			BooleanSpecification<Order, Order> act = new BooleanSpecification<Order, Order>(null, "active", true);
			processor.addAndSpecification(act);
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
		
		// process data request
		return processor.process();
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
	@RequestMapping( value = "/hl7", method = RequestMethod.POST, consumes = "*")
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<OrderHL7DTO> addHL7(@RequestBody OrderHL7DTO order, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTONullIdd(order, entity_id);
		orderValidator.validateCreateHL7Request(order);
		logger.debug("size pid "+order.getPids().size());
		// invoke order creator
		CreateRequestProcessor<IOrder_rd_Repository, Order, OrderHL7DTO> creator = getCreatorHL7();
		creator.setCreatedUser(getLoggedUser(request));
		creator.setDto(order);
				
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
		
		// process 
		return creator.process();
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

		// invoke order creator
		CreateRequestProcessor<IOrder_rd_Repository, Order, OrderDTO> creator = getCreator();
		creator.setCreatedUser(getLoggedUser(request));
		creator.setDto(order);
				
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
		
		// process 
		return creator.process();
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
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
						
		// validate
		validator.validateDTOIdd(order, entity_name);		
		orderValidator.validateUpdateRequest(order);
			
		// find order to update 
		Order orderToUpdate = repo.findOne(order.getIdOrder());
		
		UpdateRequestProcessor<IOrder_rd_Repository, Order, OrderDTO> updater = getUpdater();
		updater.setOriginDTO(rdDozerMapper.map(orderToUpdate, OrderDTO.class));
		
		// update changed services only if order status is lower in order value than accepted
		if (WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).order() <= WorkStatusEnum.ACCEPTED.order()) {
			// boolean (requested service changed)
			boolean changeRequest = false;
			
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
						orderService.setActive(true);
						repo_os.saveAndFlush(orderService);
					}
					changeRequest = true;
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
					changeRequest = true;
				}
			}
			
			// ----------------------------------------------------------------------------------------------
			// generate worksession and work task if update status = scheduled and current status = requested
			// ----------------------------------------------------------------------------------------------
			
			if ((WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).order() == WorkStatusEnum.REQUESTED.order()) 
					&& (WorkStatusEnum.getInstanceFromCodeValue(order.getWorkStatus().getCode()).order() == WorkStatusEnum.SCHEDULED.order())) {
				// create work session and put dto into order to update
				WorkSession ws = this.createWorkSessionTree(order);
				order.setWorkSession(rdDozerMapper.map(ws, WorkSessionDTO.class));
				
				// ------------------------------------
				// update or create modality allocation
				// ------------------------------------
				
				// find allocation for selected modality
				Modality scheduledModality = repo_m.findOne(Long.valueOf(order.getScheduledModality().getIdModality()));
				ModalityDailyAllocation allocation = repo_a.findByModalityAndDay(scheduledModality, new Date(order.getScheduledDate()));
				
				if (allocation == null) {
					// if not found create a new allocation
					allocation = new ModalityDailyAllocation();
					allocation.setModality(scheduledModality);
					allocation.setPatientallocation(1);
					allocation.setPatientexcess(0);
					allocation.setServiceallocation(ws.getWorkTasks().size());
					allocation.setServiceexcess(0);
					allocation.setUserupdate(getLoggedUser(request));
					allocation.setDay(new Date(order.getScheduledDate()));
				} else {
					// update counters
					allocation.setPatientallocation(allocation.getPatientallocation()+1);
					allocation.setServiceallocation(allocation.getServiceallocation()+ws.getWorkTasks().size());
					int dpc = scheduledModality.getDailypatientcapacity();
					int dsc = scheduledModality.getDailyservicecapacity();
					allocation.setPatientexcess(new Integer((allocation.getPatientallocation()-dpc > 0) ? allocation.getPatientallocation()-dpc : 0));
					allocation.setServiceexcess(new Integer((allocation.getServiceallocation()-dsc > 0) ? allocation.getServiceallocation()-dsc : 0));
					allocation.setUserupdate(getLoggedUser(request));
				}
				
				// flush modification
				repo_a.saveAndFlush(allocation);
			} 
			
			// ----------------------------------------------------------------------------------
			// reallocate modality work load and update work session with new task and new status
			// ----------------------------------------------------------------------------------
			
			else if (((WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).equals(WorkStatusEnum.SCHEDULED))) || (WorkStatusEnum.getInstanceFromCodeValue(orderToUpdate.getWorkStatus().getCode()).equals(WorkStatusEnum.ACCEPTED)) &&
					 ((WorkStatusEnum.getInstanceFromCodeValue(order.getWorkStatus().getCode()).equals(WorkStatusEnum.SCHEDULED) || WorkStatusEnum.getInstanceFromCodeValue(order.getWorkStatus().getCode()).equals(WorkStatusEnum.ACCEPTED)))) {
				
				if (changeRequest==true) {
					// get session
					WorkSession sessionToUpdate = orderToUpdate.getWorkSession();
					
					// old task size
					int oldTaskSize = sessionToUpdate.getWorkTasks().size();
					
					// recreate new task list
					WorkSession updatedSession = this.recreateWorkSessionTree(sessionToUpdate, order);
					// fix input dto with new worksession
					order.setWorkSession(rdDozerMapper.map(updatedSession, WorkSessionDTO.class));
					
					// new task size
					int newTaskSize = sessionToUpdate.getWorkTasks().size();
					
					// update allocation for selected modality and schedule date
					Modality scheduledModality = repo_m.findOne(Long.valueOf(order.getScheduledModality().getIdModality()));
					if (!order.getScheduledDate().equals(orderToUpdate.getScheduleddate().getTime())) {
						// update old allocation
						ModalityDailyAllocation oldAllocation = repo_a.findByModalityAndDay(scheduledModality, new Date(orderToUpdate.getScheduleddate().getTime()));
						oldAllocation.setPatientallocation(oldAllocation.getPatientallocation()-1);
						oldAllocation.setServiceallocation(oldAllocation.getServiceallocation()-oldTaskSize);
						oldAllocation.setServiceexcess((oldAllocation.getServiceexcess()==0) ? 0 : oldAllocation.getServiceexcess()-oldTaskSize);
						oldAllocation.setPatientexcess((oldAllocation.getPatientexcess()==0) ? 0 : oldAllocation.getPatientexcess()-1);
						oldAllocation.setUserupdate(getLoggedUser(request));
						// flush modification
						repo_a.saveAndFlush(oldAllocation);
						
						// create or update new allocation
						// update old allocation
						ModalityDailyAllocation newAllocation = repo_a.findByModalityAndDay(scheduledModality, new Date(order.getScheduledDate()));
						if (newAllocation == null) {
							// if not found create a new allocation
							newAllocation = new ModalityDailyAllocation();
							newAllocation.setModality(scheduledModality);
							newAllocation.setPatientallocation(1);
							newAllocation.setPatientexcess(0);
							newAllocation.setServiceallocation(newTaskSize);
							newAllocation.setServiceexcess(0);
							newAllocation.setUserupdate(getLoggedUser(request));
							newAllocation.setDay(new Date(order.getScheduledDate()));
						} else {
							// update counters
							newAllocation.setPatientallocation(newAllocation.getPatientallocation()+1);
							newAllocation.setServiceallocation(newAllocation.getServiceallocation()+newTaskSize);
							int dpc = scheduledModality.getDailypatientcapacity();
							int dsc = scheduledModality.getDailyservicecapacity();
							newAllocation.setPatientexcess(new Integer((newAllocation.getPatientallocation()-dpc > 0) ? newAllocation.getPatientallocation()-dpc : 0));
							newAllocation.setServiceexcess(new Integer((newAllocation.getServiceallocation()-dsc > 0) ? newAllocation.getServiceallocation()-dsc : 0));
							newAllocation.setUserupdate(getLoggedUser(request));
						}
						
						// flush modification
						repo_a.saveAndFlush(newAllocation);
					} else {
						// update old allocation
						ModalityDailyAllocation oldAllocation = repo_a.findByModalityAndDay(scheduledModality, new Date(orderToUpdate.getScheduleddate().getTime()));
						//oldAllocation.setPatientallocation(oldAllocation.getPatientallocation()-1);
						//oldAllocation.setPatientexcess((oldAllocation.getPatientexcess()==0) ? 0 : oldAllocation.getPatientexcess()-1);
						oldAllocation.setServiceallocation(oldAllocation.getServiceallocation()+(newTaskSize-oldTaskSize));
						oldAllocation.setServiceexcess((oldAllocation.getServiceexcess()==0) ? 0 : oldAllocation.getServiceexcess()-(newTaskSize-oldTaskSize));
						oldAllocation.setUserupdate(getLoggedUser(request));
						// flush modification
						repo_a.saveAndFlush(oldAllocation);
					}
				} else {
					// ----------------------------------------
					// update work session and work task status
					// ----------------------------------------
					
					// get session
					WorkSession sessionToUpdate = orderToUpdate.getWorkSession();
					// get new status
					WorkStatus newStatus = repo_ws.findByCode(order.getWorkStatus().getCode());
					// update new status for each element
					for (WorkTask element : sessionToUpdate.getWorkTasks()) {
						element.setWorkStatus(newStatus);
						element.setUserupdate(getLoggedUser(request));
						repo_wt.saveAndFlush(element);
					}
					
					// update session
					sessionToUpdate.setWorkStatus(newStatus);
					if (WorkStatusEnum.getInstanceFromCodeValue(order.getWorkStatus().getCode()).equals(WorkStatusEnum.ACCEPTED)) {
						sessionToUpdate.setAcceptancedate(new Date());
					}
					repo_wss.saveAndFlush(sessionToUpdate);
					em.refresh(sessionToUpdate);
				}				
			}
			
			em.refresh(orderToUpdate);
		}
		
		// create and save log
		OrderLog log = rdDozerMapper.map(orderToUpdate, OrderLog.class);
		log.setUserupdate(getLoggedUser(request));
		repo_ol.saveAndFlush(log);
		
		// set updater params		
		updater.setSourceDto(order);
		updater.setUpdatedUser(getLoggedUser(request));
					
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, order.getIdd().toString()));

		return updater.process();
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
		
		// find order to update 
		Order orderToUpdate = repo.findOne(id);
		
		// deactivate requested services 
		for (OrderService element : orderToUpdate.getOrderServices()) {
			element.setActive(false);
			element.setCanceledreason((StringUtils.isNullEmptyWhiteSpaceOnly(cancelReason)) ? rejectReason : cancelReason);
		}
		
		// deactivate session and task
		WorkStatus canceledStatus = repo_ws.findOne((long)WorkStatusEnum.CANCELED.order());
		WorkSession ws = orderToUpdate.getWorkSession();
		ws.setActive(false);
		ws.setWorkStatus(canceledStatus);
		
		Modality scheduledModality = null;
		int taskToRemove = 0;
		for (WorkTask element : ws.getWorkTasks()) {
			element.setActive(false);
			element.setWorkStatus(canceledStatus);
			scheduledModality = element.getModality();
			taskToRemove++;
		}
		repo_wss.saveAndFlush(ws);
		
		// remove allocation
		ModalityDailyAllocation oldAllocation = repo_a.findByModalityAndDay(scheduledModality, new Date(orderToUpdate.getScheduleddate().getTime()));
		oldAllocation.setPatientallocation(oldAllocation.getPatientallocation()-1);
		oldAllocation.setServiceallocation(oldAllocation.getServiceallocation()-taskToRemove);
		repo_a.saveAndFlush(oldAllocation);
		
		// build dto to deactivate
		OrderDTO toDeactivate = new OrderDTO();
		toDeactivate.setIdOrder(id);
		toDeactivate.setCanceledDate(new Date().getTime());
		toDeactivate.setCancelReason((StringUtils.isNullEmptyWhiteSpaceOnly(cancelReason)) ? null : cancelReason);
		toDeactivate.setRejectReason((StringUtils.isNullEmptyWhiteSpaceOnly(rejectReason)) ? null : rejectReason);
		toDeactivate.setWorkStatus(new BaseObjectDTO(String.valueOf(WorkStatusEnum.CANCELED.order()), WorkStatusEnum.CANCELED.name(), WorkStatusEnum.CANCELED.code()));
		
		// set updater params
		UpdateRequestProcessor<IOrder_rd_Repository, Order, OrderDTO> updater = getUpdater();
		updater.setSourceDto(toDeactivate);
		updater.setUpdatedUser(getLoggedUser(request));
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, id.toString()));

		// return response
		return updater.enable(false);
	}
	
	/**
	 * 
	 * @param order
	 * @return
	 */
	private WorkSession createWorkSessionTree(OrderDTO order) {
		// query objects
		// TODO must remove set
		// TODO can be optimized?
		Set<Order> orders = new HashSet<Order>();
		orders.add(repo.findOne(order.getIdOrder()));
		Patient p = repo_p.findOne(order.getPatient().getIdPatient());
		Date scheduleDate = new Date(order.getScheduledDate());
		WorkPriority priority = repo_wp.findByCode(order.getWorkPriority().getCode());
		WorkStatus status = repo_ws.findByCode(order.getWorkStatus().getCode());
		User systemUser = repo_u.findOne("SYSTEM");
		Modality modality = repo_m.findOne(Long.valueOf(order.getScheduledModality().getIdModality()));
		
		// create worksession
		WorkSession workSession = new WorkSession();
		workSession.setPatient(p);
		workSession.setScheduleddate(scheduleDate);
		workSession.setWorkPriority(priority);
		workSession.setWorkStatus(status);
		workSession.setOrders(orders);
		
		// create task list
		Set<WorkTask> workTasks = new HashSet<WorkTask>();
		for (OrderedServiceDTO orderedServiceDTO : order.getServices()) {
			WorkTask task = new WorkTask();
			task.setScheduleddate(new Date(order.getScheduledDate()));
			task.setService(repo_s.findOne(Long.valueOf(orderedServiceDTO.getId())));
			task.setWorkPriority(priority);
			task.setWorkStatus(status);
			task.setUser(systemUser);
			task.setAccessionnumber(new Long(123123123));
			task.setModality(modality);
			task.setWorkSession(workSession);
			workTasks.add(task);
		}
		
		workSession.setWorkTasks(workTasks);
		workSession = repo_wss.saveAndFlush(workSession);
		
		// return session
		return workSession;
	}
	
	private WorkSession recreateWorkSessionTree(WorkSession sessionToUpdate, OrderDTO order) {
		// remove all tasks
		Iterator<WorkTask> it = sessionToUpdate.getWorkTasks().iterator();
		while (it.hasNext()) {
			WorkTask element = it.next();
			repo_wt.delete(element);
			it.remove();
			repo_wt.flush();
		}

		// em.refresh(sessionToUpdate);
		
		// create task list
		Set<WorkTask> workTasks = new HashSet<WorkTask>();
		for (OrderedServiceDTO orderedServiceDTO : order.getServices()) {
			WorkTask task = new WorkTask();
			task.setScheduleddate(new Date(order.getScheduledDate()));
			task.setService(repo_s.findOne(Long.valueOf(orderedServiceDTO.getId())));
			task.setWorkPriority(repo_wp.findByCode(order.getWorkPriority().getCode()));
			task.setWorkStatus(repo_ws.findByCode(order.getWorkStatus().getCode()));
			task.setUser(repo_u.findOne("SYSTEM"));
			task.setAccessionnumber(new Long(123123123));
			task.setModality(repo_m.findOne(Long.valueOf(order.getScheduledModality().getIdModality())));
			task.setWorkSession(sessionToUpdate);
			workTasks.add(task);
			//repo_wt.saveAndFlush(task);
		}
		
		sessionToUpdate.setWorkTasks(workTasks);
		sessionToUpdate = repo_wss.saveAndFlush(sessionToUpdate);
		em.refresh(sessionToUpdate);
		
		// TODO Auto-generated method stub
		return sessionToUpdate;
	}

	@Override
	protected CreateRequestProcessor<IOrder_rd_Repository, Order, OrderDTO> getCreator() {
		// construct creator
		return new CreateRequestProcessor<IOrder_rd_Repository, Order, OrderDTO>(repo, rdDozerMapper, Order.class, entity_name, env, em);
	}

	@Override
	protected UpdateRequestProcessor<IOrder_rd_Repository, Order, OrderDTO> getUpdater() {
		// construct updater
		return new UpdateRequestProcessor<IOrder_rd_Repository, Order, OrderDTO>(repo, rdDozerMapper, entity_name, entity_id, env, em);
	}

	@Override
	protected CriteriaRequestProcessor<IOrder_rd_Repository, Order, OrderDTO> getProcessor() {
		// costruct processor
		return new CriteriaRequestProcessor<IOrder_rd_Repository, Order, OrderDTO>(repo, rdDozerMapper, OrderDTO.class, entity_name, env);
	}

	protected CreateRequestProcessor<IOrder_rd_Repository, Order, OrderHL7DTO> getCreatorHL7() {
		return new CreateRequestProcessor<IOrder_rd_Repository, Order, OrderHL7DTO>(repo, rdDozerMapper, Order.class, entity_name, env, em);
	}
}
