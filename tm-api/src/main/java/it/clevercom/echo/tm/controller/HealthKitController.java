package it.clevercom.echo.tm.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("protected")
public class HealthKitController {

	@RequestMapping(value = "healthKit", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER')")
	public @ResponseBody String get() {
		return "healthKit";
	}
	
	@RequestMapping(value = "healthKit", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody String add() {
		return "healthKit";
	}
	
	@RequestMapping(value = "healthKit", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody String update() {
		return "healthKit";
	}
	
	@RequestMapping(value = "healthKit", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody String delete() {
		return "healthKit";
	}
}
