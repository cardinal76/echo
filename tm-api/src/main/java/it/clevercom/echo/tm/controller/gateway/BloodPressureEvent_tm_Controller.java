package it.clevercom.echo.tm.controller.gateway;

import java.text.MessageFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
import it.clevercom.echo.common.jpa.specification.DateIntervalSpecification;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.common.model.factory.ResponseFactory;
import it.clevercom.echo.common.util.DateUtil;
import it.clevercom.echo.common.util.EchoValidator;
import it.clevercom.echo.tm.model.dto.gateway.BaseGatewayEntityDTO;
import it.clevercom.echo.tm.model.dto.gateway.BloodPressureEventDTO;
import it.clevercom.echo.tm.model.entity.gateway.GatewayEvent;
import it.clevercom.echo.tm.model.entity.gateway.GatewayEventBloodPressure;
import it.clevercom.echo.tm.repository.gateway.BaseGatewayEntity_tm_Repository;
import it.clevercom.echo.tm.repository.gateway.BloodPressure_tm_Repository;

/**
 * @author gfares
 * @since 0.1
 * 
 *        API Rest exposed to receive battery level events
 */

@Controller
@RestController
@RequestMapping("gateway/v1/blood")
public class BloodPressureEvent_tm_Controller extends EchoController {
	
	@Autowired
	private Environment env;

	@Autowired
	private DozerBeanMapper tmDozerMapper;

	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private EchoValidator validator;

	@Autowired
	private BloodPressure_tm_Repository repository;

	@Autowired
	private BaseGatewayEntity_tm_Repository baseRepository;
	
	public static final String entity_name = "Blood Event";
	public static final String entity_id = "idbloodpressure";

	/**
	 * 
	 * @param event
	 * @return
	 * @throws Exception
	 */
	@Transactional("tmTm")
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody CreateResponseDTO<BloodPressureEventDTO> add(@RequestBody BloodPressureEventDTO event) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTONullIdd(event, entity_id);
		
		// create processor
		BaseGatewayEntityDTO baseDto = tmDozerMapper.map(event, BaseGatewayEntityDTO.class);
		CreateRequestProcessor<BaseGatewayEntity_tm_Repository, GatewayEvent, BaseGatewayEntityDTO> rp = 
				new CreateRequestProcessor<BaseGatewayEntity_tm_Repository, GatewayEvent, BaseGatewayEntityDTO>(baseRepository, 
						tmDozerMapper, 
						GatewayEvent.class, 
						entity_name, 
						"SYSTEM", 
						baseDto,
						env);
		
		// create base event
		GatewayEvent gatewayEvent = rp.create();
		
		// create processor
		CreateRequestProcessor<BloodPressure_tm_Repository, GatewayEventBloodPressure, BloodPressureEventDTO> rp1 = 
				new CreateRequestProcessor<BloodPressure_tm_Repository, GatewayEventBloodPressure, BloodPressureEventDTO>(repository, 
						tmDozerMapper, 
						GatewayEventBloodPressure.class, 
						entity_name, 
						"SYSTEM", 
						event,
						env);

		// create blood event
		GatewayEventBloodPressure bloodEvent = rp1.create();
	
		// update reference
		bloodEvent.setGatewayEvent(gatewayEvent);
		bloodEvent = repository.saveAndFlush(bloodEvent);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
		
		// process
		return ResponseFactory.getCreateResponseDTO(tmDozerMapper.map(bloodEvent, BloodPressureEventDTO.class), entity_name, MessageFormat.format(env.getProperty("echo.api.crud.saved"), entity_name));
	}
	
	
	/**
	 * Get a blood pressure by id
	 * @author marco
	 * @category standard get by id REST method
	 * @param id
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("tmTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@Loggable
	public @ResponseBody BloodPressureEventDTO get(@PathVariable Long id) throws Exception {
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting"), entity_name, entity_id, id.toString()));
		
		// validate
		validator.validateId(id, entity_name);
		
		// find entity
		GatewayEvent event = baseRepository.findOne(id);
		GatewayEventBloodPressure entity = repository.findByGatewayEvent(event);
		
		// check if entity has been found
		if (entity == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.returning.response"), entity_name, entity_id, id.toString()));
		return tmDozerMapper.map(entity, BloodPressureEventDTO.class);
	}
	
	/**
	 * Get a body weight list by criteria with pagination
	 * @author marco
	 * @category standard get by criteria REST method
	 * @param criteria
	 * @param page
	 * @param size
	 * @param sort
	 * @param field
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("tmTm")
	@RequestMapping(value="", method = RequestMethod.GET)
	@Loggable
	public @ResponseBody PagedDTO<BloodPressureEventDTO> getByCriteria (
			@RequestParam(defaultValue = "system_start", required = false) Long from,
			@RequestParam(defaultValue = "system_end", required = false) Long to,
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="15", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue=entity_id, required=false) String field) throws Exception {
		
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
				
		// validate
		validator.validateSort(sort);
		validator.validateSortField(field, GatewayEventBloodPressure.class, entity_name);
		
		// create processor
		CriteriaRequestProcessor<BloodPressure_tm_Repository, GatewayEventBloodPressure, BloodPressureEventDTO> rp = 
				new CriteriaRequestProcessor<BloodPressure_tm_Repository, GatewayEventBloodPressure, BloodPressureEventDTO>(repository, 
						tmDozerMapper, 
						BloodPressureEventDTO.class, 
						field, 
						criteria, 
						sort, 
						field, 
						page, 
						size, 
						env);
		
		final Date t1 = DateUtil.getStartOfDay(new Date(from));
		final Date t2 = DateUtil.getEndOfDay(new Date(to));
		DateIntervalSpecification<GatewayEventBloodPressure, GatewayEventBloodPressure> interval = new DateIntervalSpecification<GatewayEventBloodPressure, GatewayEventBloodPressure>("gatewayEvent", t1, t2, "timestamp");
		rp.addAndSpecification(interval);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
		
		// process data request
		return rp.process();	
	}


	@Override
	protected CreateRequestProcessor<?, ?, ?> getCreator() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected UpdateRequestProcessor<?, ?, ?> getUpdater() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected CriteriaRequestProcessor<?, ?, ?> getProcessor() {
		// TODO Auto-generated method stub
		return null;
	}
}
