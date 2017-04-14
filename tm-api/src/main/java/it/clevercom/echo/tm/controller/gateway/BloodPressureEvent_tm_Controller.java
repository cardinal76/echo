package it.clevercom.echo.tm.controller.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.clevercom.echo.tm.model.dto.gateway.EventRestResponseDTO;
import it.clevercom.echo.tm.model.entity.gateway.BloodPressureEvent;
import it.clevercom.echo.tm.repository.gateway.BloodPressure_tm_Repository;

/**
 * @author gfares
 * @since 0.1
 * 
 *        API Rest exposed to receive blood pressure events
 */

@Controller
@RestController
@RequestMapping("gateway/v1/blood")
public class BloodPressureEvent_tm_Controller {

	@Autowired
	private BloodPressure_tm_Repository repository;

	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody EventRestResponseDTO add(@RequestBody BloodPressureEvent event) {
		try {
			repository.saveAndFlush(event);
			return EventRestResponseDTO.createSuccess();
		} catch (Exception ex) {
			return EventRestResponseDTO.createFailed(ex);
		}
	}
}
