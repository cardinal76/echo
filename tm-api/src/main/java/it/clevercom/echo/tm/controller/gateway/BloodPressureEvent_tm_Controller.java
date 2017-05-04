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

import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.jpa.CreateRequestProcessor;
import it.clevercom.echo.common.jpa.CriteriaRequestProcessor;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.common.util.EchoValidator;
import it.clevercom.echo.tm.model.dto.gateway.BloodPressureEventDTO;
import it.clevercom.echo.tm.model.entity.gateway.BloodPressureEvent;
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
public class BloodPressureEvent_tm_Controller {
	
	@Autowired
	private Environment env;

	@Autowired
	private DozerBeanMapper tmDozerMapper;

	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private EchoValidator validator;

	@Autowired
	private BloodPressure_tm_Repository repository;

	public static final String entity_name = "Blood Event";
	public static final String entity_id = "pulserate";

	
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody CreateResponseDTO<BloodPressureEventDTO> add(@RequestBody BloodPressureEventDTO event) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTONullIdd(event, entity_id);
		
		// create processor
		CreateRequestProcessor<BloodPressure_tm_Repository, BloodPressureEvent, BloodPressureEventDTO> rp = 
				new CreateRequestProcessor<BloodPressure_tm_Repository, BloodPressureEvent, BloodPressureEventDTO>(repository, 
						tmDozerMapper, 
						BloodPressureEvent.class, 
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
	 * Get a body weight by id
	 * @author marco
	 * @category standard get by id REST method
	 * @param id
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@Loggable
	public @ResponseBody BloodPressureEventDTO get(@PathVariable Long id) throws Exception {
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting"), entity_name, entity_id, id.toString()));
		
		// validate
		validator.validateId(id, entity_name);
		
		// find entity
		BloodPressureEvent entity = repository.findOne(id);
		
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
	@Transactional("rdTm")
	@RequestMapping(value="", method = RequestMethod.GET)
	@Loggable
	public @ResponseBody PagedDTO<BloodPressureEventDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="15", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue=entity_id, required=false) String field) throws Exception {
		
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
				
		// validate
		validator.validateSort(sort);
		validator.validateSortField(field, BloodPressureEvent.class, entity_name);
		
		// create processor
		CriteriaRequestProcessor<BloodPressure_tm_Repository, BloodPressureEvent, BloodPressureEventDTO> rp = 
				new CriteriaRequestProcessor<BloodPressure_tm_Repository, BloodPressureEvent, BloodPressureEventDTO>(repository, 
						tmDozerMapper, 
						BloodPressureEventDTO.class, 
						entity_name, 
						criteria,
						sort, 
						field, 
						page, 
						size,
						env);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
		
		// process data request
		return rp.process();	
	}
}
