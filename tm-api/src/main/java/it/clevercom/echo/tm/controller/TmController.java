package it.clevercom.echo.tm.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("protected")
public class TmController {

	@RequestMapping(value = "admin", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_TM_ADMIN')")
	public @ResponseBody String getDaHoneyAdmin() {
		return "OK ADMIN!";
	}

	@RequestMapping(value = "user", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_TM_USER')")
	public @ResponseBody String getDaHoneyUser() {
		return "OK USER!";
	}

}
