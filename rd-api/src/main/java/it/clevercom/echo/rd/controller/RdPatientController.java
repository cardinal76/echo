package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;

import org.dozer.DozerBeanMapper;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
import it.clevercom.echo.rd.model.dto.PatientDTO;
import it.clevercom.echo.rd.model.entity.Patient;
import it.clevercom.echo.rd.repository.RdPatientRepository;

@Controller
@RestController
@RequestMapping("rd/patient")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

public class RdPatientController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private RdPatientRepository repo;
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
    private DozerBeanMapper dozerMapper;
	
	// used to bind it in exception message
	private static String managedEntityName = "Patient";
	
	/**
	 * 
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	public @ResponseBody PatientDTO get(@RequestParam Integer id) throws RecordNotFoundException {
		Patient entity = repo.findOne(id);	
		if (entity == null) throw new RecordNotFoundException(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), managedEntityName, id.toString()));
		Hibernate.initialize(entity);
		
		PatientDTO dto = dozerMapper.map(entity, PatientDTO.class);
		return dto;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "hl7", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	public @ResponseBody String getHL7() {
		return "patient";
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	public @ResponseBody String add() {
		return "patient";
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	public @ResponseBody String update() {
		return "patient";
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	public @ResponseBody String delete() {
		return "patient";
	}
}
