package it.clevercom.echo.tm.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tm/languageType")
public class LanguageType_tm_Controller {
	
	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER')")
	public @ResponseBody String get() {
		return "languageType";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody String add() {
		return "languageType";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody String update() {
		return "languageType";
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody String delete() {
		return "languageType";
	}
}
