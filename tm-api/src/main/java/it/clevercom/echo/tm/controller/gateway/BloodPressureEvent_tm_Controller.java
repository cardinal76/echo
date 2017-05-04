package it.clevercom.echo.tm.controller.gateway;

import java.text.MessageFormat;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.clevercom.echo.common.jpa.CreateRequestProcessor;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
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

	public static final String entity_name = "Weight Event";
	public static final String entity_id = "id";

	
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
}
