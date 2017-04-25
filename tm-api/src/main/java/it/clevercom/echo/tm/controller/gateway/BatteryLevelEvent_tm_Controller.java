package it.clevercom.echo.tm.controller.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.clevercom.echo.tm.model.dto.gateway.EventRestResponseDTO;
import it.clevercom.echo.tm.model.entity.gateway.BatteryLevelEvent;
import it.clevercom.echo.tm.repository.gateway.BatteryLevel_tm_Repository;

/**
 * @author gfares
 * @since 0.1
 * 
 *        API Rest exposed to receive blood pressure events
 */

@Controller
@RestController
@RequestMapping("gateway/v1/battery")
public class BatteryLevelEvent_tm_Controller {

	@Autowired
	private BatteryLevel_tm_Repository repository;

	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody EventRestResponseDTO add(@RequestBody BatteryLevelEvent event) {
		try {
			repository.saveAndFlush(event);
			return EventRestResponseDTO.createSuccess();
		} catch (Exception ex) {
			return EventRestResponseDTO.createFailed(ex);
		}
	}
}
