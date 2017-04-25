package it.clevercom.echo.tm.controller.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.clevercom.echo.tm.model.dto.gateway.EventRestResponseDTO;
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
	private Weight_tm_Repository repository;

	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody EventRestResponseDTO add(@RequestBody WeightEvent event) {
		try {
			repository.saveAndFlush(event);
			return EventRestResponseDTO.createSuccess();
		} catch (Exception ex) {
			return EventRestResponseDTO.createFailed(ex);
		}
	}
}
