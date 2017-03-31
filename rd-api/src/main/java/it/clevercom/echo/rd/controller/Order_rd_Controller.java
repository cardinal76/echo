package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import it.clevercom.echo.rd.enums.WorkStatusEnum;
import it.clevercom.echo.rd.model.dto.OrderDTO;
import it.clevercom.echo.rd.model.dto.PagedDTO;
import it.clevercom.echo.rd.model.entity.Order;
import it.clevercom.echo.rd.model.entity.WorkStatus;
import it.clevercom.echo.rd.model.jpa.helper.SearchCriteria;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationQueryHelper;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationsBuilder;
import it.clevercom.echo.rd.repository.IOrder_rd_Repository;
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

public class Order_rd_Controller extends EchoController{

	@Autowired
	private Environment env;

	@Autowired
	private IOrder_rd_Repository repo;

	@Autowired
	private IWorkStatus_rd_Repository repo_ws;

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
			@RequestParam(defaultValue = "today", required = false) Long from,
			@RequestParam(defaultValue = "tomorrow", required = false) Long to,
			@RequestParam(defaultValue = "REQUESTED", required = false) String status,
			@RequestParam(defaultValue = "null", required = false) String criteria,
			@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam(defaultValue = "15", required = false) int size,
			@RequestParam(defaultValue = "asc", required = false) String sort,
			@RequestParam(defaultValue = "idorder", required = false) String field) throws Exception {
		
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

		if (!criteria.equals("null")) {
			SpecificationsBuilder<Order, SpecificationQueryHelper<Order>> builder = new SpecificationsBuilder<Order, SpecificationQueryHelper<Order>>();
			Pattern pattern = Pattern.compile(SearchCriteria.pattern);
			Matcher matcher = pattern.matcher(criteria + ",");
			while (matcher.find()) {
				builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
			}
			Specification<Order> spec = builder.build();

			// obtain records
			rs = repo.findAll(spec, request);
		} else {
			rs = repo.findAll(request);
		}

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
	 * Get order by interval and status with pagination
	 * @param from
	 * @param to
	 * @param workstatus
	 * @param page
	 * @param size
	 * @param sort
	 * @param field
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value = "/from/{from}/to/{to}/workstatus/{status}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<OrderDTO> getByCreationDateAndWorkStatus(
			@PathVariable Long from,
			@PathVariable Long to,
			@PathVariable String status,
			@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam(defaultValue = "15", required = false) int size,
			@RequestParam(defaultValue = "asc", required = false) String sort,
			@RequestParam(defaultValue = "idorder", required = false) String field) throws Exception {
		
		// parse long parameter to Date Object
		Date t1 = DateUtil.getStartOfDay(new Date(from));
		Date t2 = DateUtil.getStartOfDay(new Date(to));
		
		if (!WorkStatusEnum.contains(status)) {
			throw new BadRequestException(
					MessageFormat.format(env.getProperty("echo.api.exception.search.params.wrongparam"),
							env.getProperty("echo.api.crud.fields.workstatus"),
							WorkStatus.class.getDeclaringClass().getEnumConstants().toString()));
		}
		
		// create paged request
		PageRequest request = null;
		
		if (sort.equalsIgnoreCase("asc")) {
			request = new PageRequest(page - 1, size, Direction.ASC, field);
		} else if (sort.equalsIgnoreCase("desc")) {
			request = new PageRequest(page - 1, size, Direction.DESC, field);
		} else {
			throw new BadRequestException(env.getProperty("echo.api.exception.search.sort.wrongsortparam"));
		}
		 
		WorkStatus statusEntity = repo_ws.findByCode(WorkStatusEnum.valueOf(status).code());
		
		List<Order> orders = repo.findByCreationdateBetweenAndWorkStatus(t1, t2, statusEntity, request);
		
		if (orders.size() == 0)
			throw new RecordNotFoundException(entity_name, entity_cd1, statusEntity.getCode());
		
		List<OrderDTO> orderDTOList = new ArrayList<OrderDTO>();
		for (Order order : orders) {
			orderDTOList.add(rdDozerMapper.map(order, OrderDTO.class));
		}
		
		// assembly dto
		PagedDTO<OrderDTO> dto = new PagedDTO<OrderDTO>();
		dto.setElements(orderDTOList);
		dto.setPageSize(size);
		dto.setCurrentPage(page);
		// get total count
		long totalCount = repo.countByCreationdateBetweenAndWorkStatus(t1, t2, statusEntity);
		dto.setTotalPages((int)Math.ceil(((double) totalCount) / ((double) size)));
		dto.setTotalElements(totalCount);
		
		// return dto;
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
	public @ResponseBody UpdateResponseDTO<OrderDTO> update(@RequestBody OrderDTO order, HttpServletRequest request)
			throws Exception {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);

		// if an id is not present throw bad request
		if (order.getIdOrder() == null)
			throw new BadRequestException(
					MessageFormat.format(env.getProperty("echo.api.exception.missing.id"), entity_name));

		// find entity to update (oldValue)
		Order oldValueEntity = repo.findOne(order.getIdOrder());
		// if an entity with given id is not found in DB throw record not found
		if (oldValueEntity == null)
			throw new RecordNotFoundException(entity_name, entity_id, order.getIdOrder().toString());

		// validate create request
		this.validateUpdateRequest(order, oldValueEntity);

		// get created date
		Date created = oldValueEntity.getCreated();
		// map old value to a dto
		OrderDTO oldValueDTO = rdDozerMapper.map(oldValueEntity, OrderDTO.class);

		// begin update of oldValue
		rdDozerMapper.map(order, oldValueEntity);

		// adSchedule Dated technical field
		oldValueEntity.setUserupdate(username);
		oldValueEntity.setUpdated(new Date());
		oldValueEntity.setCreated(created);

		// save and map to out dto
		Order newValueEntity = repo.saveAndFlush(oldValueEntity);
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

		// order logs should not be present here
		if ((order.getOrderLogs() != null) && (order.getOrderLogs().size() > 0)) {
			exceptions.addFieldError(env.getProperty("echo.api.crud.fields.orderlogs"),
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

		switch (WorkStatusEnum.valueOf(order.getWorkStatus().getName())) {
		case ACCEPTED:
			break;
		case SCHEDULED:
			break;
		case CANCELED:
			break;
		case REQUESTED:
			break;
		default:
			break;
		}
	}
}
