package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.rd.model.dto.ServiceDTO;
import it.clevercom.echo.rd.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.rd.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.rd.model.entity.Service;
import it.clevercom.echo.rd.repository.IService_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/service")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

public class Service_rd_Controller {
	@Autowired
	private Environment env;
	
	@Autowired
	private IService_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper dozerMapper;
	
	// used to bind it in exception message
	private static String entity = "Service";
	private static String ca_rel_entity = "CodingActor";
	
	/**
	 * 
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	public @ResponseBody ServiceDTO get(@RequestParam Integer id) throws RecordNotFoundException {
		Service entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), Service_rd_Controller.entity, id.toString()));
		ServiceDTO dto = dozerMapper.map(entity, ServiceDTO.class);
		return dto;
	}
	
	/**
	 * 
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	public @ResponseBody CreateResponseDTO add() {
		return new CreateResponseDTO();
	}
	
	/**
	 * 
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	public @ResponseBody UpdateResponseDTO update() {
		return new UpdateResponseDTO();
	}
	
	/**
	 * 
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	public @ResponseBody String delete() {
		return "patient";
	}
}
