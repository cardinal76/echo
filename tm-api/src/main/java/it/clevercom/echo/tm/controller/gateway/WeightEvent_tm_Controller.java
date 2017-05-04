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
import it.clevercom.echo.tm.model.dto.gateway.WeightEventDTO;
import it.clevercom.echo.tm.model.entity.gateway.WeightEvent;
import it.clevercom.echo.tm.repository.gateway.Weight_tm_Repository;

/**
 * @author gfares
 * @since 0.1
 * 
 *        API Rest exposed to receive weight events
 */

@Controller
@RestController
@RequestMapping("gateway/v1/weight")
public class WeightEvent_tm_Controller {

	@Autowired
	private Environment env;
	
	@Autowired
	private Weight_tm_Repository repository;

	@Autowired
    private DozerBeanMapper tmDozerMapper;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private EchoValidator validator;
	
	public static final String entity_name = "Weight Event";
	public static final String entity_id = "id";

	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody CreateResponseDTO<WeightEventDTO> add(@RequestBody WeightEventDTO event) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTONullIdd(event, entity_id);
		
		// create processor
		CreateRequestProcessor<Weight_tm_Repository, WeightEvent, WeightEventDTO> rp = 
				new CreateRequestProcessor<Weight_tm_Repository, WeightEvent, WeightEventDTO>(repository, 
						tmDozerMapper, 
						WeightEvent.class, 
						entity_name, 
						"SYSTEM", 
						event,
						env);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
		
		// process
		return rp.process();
		
//		try {
//			repository.saveAndFlush(event);
//			return EventRestResponseDTO.createSuccess();
//		} catch (Exception ex) {
//			return EventRestResponseDTO.createFailed(ex);
//		}
	}
}
