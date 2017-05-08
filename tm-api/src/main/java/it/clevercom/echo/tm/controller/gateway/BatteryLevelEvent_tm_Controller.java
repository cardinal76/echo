package it.clevercom.echo.tm.controller.gateway;

import java.text.MessageFormat;
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
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.common.util.EchoValidator;
import it.clevercom.echo.tm.model.dto.gateway.BatteryLevelEventDTO;
import it.clevercom.echo.tm.model.entity.gateway.BatteryLevelEvent;
import it.clevercom.echo.tm.model.entity.gateway.GatewayEventBattery;
import it.clevercom.echo.tm.model.entity.gateway.BatteryLevelEvent;
import it.clevercom.echo.tm.repository.gateway.BatteryLevel_tm_Repository;

/**
 * @author gfares
 * @since 0.1
 * 
 *        API Rest exposed to receive battery level events
 */

@Controller
@RestController
@RequestMapping("gateway/v1/battery")
public class BatteryLevelEvent_tm_Controller extends EchoController{

	@Autowired
	private Environment env;
	
	@Autowired
	private BatteryLevel_tm_Repository repository;

	@Autowired
    private DozerBeanMapper tmDozerMapper;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private EchoValidator validator;
	
	public static final String entity_name = "Battery Event";
	public static final String entity_id = "battery";

	/**
	 * 
	 * @param event
	 * @return
	 * @throws Exception
	 */
	@Transactional("tmTm")
	@RequestMapping(method = RequestMethod.PUT)
	@Loggable
	public @ResponseBody CreateResponseDTO<BatteryLevelEventDTO> add(@RequestBody BatteryLevelEventDTO event) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTONullIdd(event, entity_id);
		
		// create processor
		CreateRequestProcessor<BatteryLevel_tm_Repository, GatewayEventBattery, BatteryLevelEventDTO> rp = 
				new CreateRequestProcessor<BatteryLevel_tm_Repository, GatewayEventBattery, BatteryLevelEventDTO>(repository, 
						tmDozerMapper, 
						GatewayEventBattery.class, 
						entity_name, 
						"SYSTEM", 
						event,
						env);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
		
		// process
		return rp.process();
	}
	
	
	/**
	 * Get a body battery by id
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
	public @ResponseBody BatteryLevelEventDTO get(@PathVariable Long id) throws Exception {
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting"), entity_name, entity_id, id.toString()));
		
		// validate
		validator.validateId(id, entity_name);
		
		// find entity
		GatewayEventBattery entity = repository.findOne(id);
		
		// check if entity has been found
		if (entity == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.returning.response"), entity_name, entity_id, id.toString()));
		return tmDozerMapper.map(entity, BatteryLevelEventDTO.class);
	}
	
	/**
	 * Get a body battery list by criteria with pagination
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
	public @ResponseBody PagedDTO<BatteryLevelEventDTO> getByCriteria (
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
		validator.validateSortField(field, BatteryLevelEvent.class, entity_name);
		
		// create processor
		CriteriaRequestProcessor<BatteryLevel_tm_Repository, GatewayEventBattery, BatteryLevelEventDTO> rp = 
				new CriteriaRequestProcessor<BatteryLevel_tm_Repository, GatewayEventBattery, BatteryLevelEventDTO>(repository, 
						tmDozerMapper, 
						BatteryLevelEventDTO.class, 
						entity_name, 
						criteria, 
						sort, 
						field, 
						page, 
						size,
						env);
		
//		final Date t1 = DateUtil.getStartOfDay(new Date(from));
//		final Date t2 = DateUtil.getEndOfDay(new Date(to));
//		DateIntervalSpecification<BatteryLevelEvent> interval = new DateIntervalSpecification<BatteryLevelEvent>(t1, t2, "timestamp");
//		rp.addAndSpecification(interval);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
		
		// process data request
		return rp.process();	
	}
}
