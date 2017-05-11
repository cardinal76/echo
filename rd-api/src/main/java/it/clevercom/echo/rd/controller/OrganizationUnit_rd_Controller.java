package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.clevercom.echo.common.controller.EchoController;
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.jpa.CreateRequestProcessor;
import it.clevercom.echo.common.jpa.CriteriaRequestProcessor;
import it.clevercom.echo.common.jpa.UpdateRequestProcessor;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.rd.component.Validator;
import it.clevercom.echo.rd.model.dto.OrganizationUnitDTO;
import it.clevercom.echo.rd.model.entity.OrganizationUnit;
import it.clevercom.echo.rd.repository.IOrganizationUnit_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/assets/organizationunit")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Organization Unit Controller
 * @author luca
 */

public class OrganizationUnit_rd_Controller extends EchoController {

	@Autowired
	private Environment env;
	
	@Autowired
	private IOrganizationUnit_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	@PersistenceContext(unitName="rdPU")
	protected EntityManager em;

	// crud processors
	private CriteriaRequestProcessor<IOrganizationUnit_rd_Repository, OrganizationUnit, OrganizationUnitDTO> processor;
	private CreateRequestProcessor<IOrganizationUnit_rd_Repository, OrganizationUnit, OrganizationUnitDTO> creator;
	private UpdateRequestProcessor<IOrganizationUnit_rd_Repository, OrganizationUnit, OrganizationUnitDTO> updater;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	public static final String entity_name = "OrganizationUnit";
	public static final String entity_id = "idorganizationunit";
	
	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		// construct creator
		creator = new CreateRequestProcessor<IOrganizationUnit_rd_Repository, OrganizationUnit, OrganizationUnitDTO>(repo, rdDozerMapper, OrganizationUnit.class, entity_name, env, em);
		// construct updater
		updater = new UpdateRequestProcessor<IOrganizationUnit_rd_Repository, OrganizationUnit, OrganizationUnitDTO>(repo, rdDozerMapper, entity_name, entity_id, env, em);
		// costruct processor
		processor = new CriteriaRequestProcessor<IOrganizationUnit_rd_Repository, OrganizationUnit, OrganizationUnitDTO>(repo, rdDozerMapper, OrganizationUnitDTO.class, entity_name, env);
	}
	
	/**
	 * Get organization unit by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody OrganizationUnitDTO get(@PathVariable Long id) throws Exception {
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting"), entity_name, entity_id, id.toString()));
		
		// find entity
		OrganizationUnit entity = repo.findOne(id);
		
		// check if entity has been found
		if (entity == null) { 
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.returning.response"), entity_name, entity_id, id.toString()));
		return rdDozerMapper.map(entity, OrganizationUnitDTO.class);
	}
	
	/**
	 * Get organization unit by criteria
	 * @param criteria
	 * @param page
	 * @param size
	 * @param sort
	 * @param field
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<OrganizationUnitDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="1000", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue=entity_id, required=false) String field) throws Exception {
		
		// check enum string params
		validator.validateSort(sort);
		validator.validateSortField(field, OrganizationUnit.class, entity_name);

		// set processor params
		processor.setCriteria(criteria);
		processor.setPageCriteria(sort, field, page, size);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
				
		// process data request
		return processor.process();
	}
	
	/**
	 * Add an organization unit
	 * @author luca
	 * @category standard create REST method
	 * @param modality
	 * @param request
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<OrganizationUnitDTO> add(@RequestBody OrganizationUnitDTO organization, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTONullIdd(organization, entity_id);
				
		// invoke order creator
		creator.setCreatedUser(getLoggedUser(request));
		creator.setDto(organization);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
		
		// process
		return creator.process();
	}
	
	/**
	 * Update an organization unit
	 * @author luca
	 * @category standard update REST method
	 * @param modality
	 * @param request
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<OrganizationUnitDTO> update(@RequestBody OrganizationUnitDTO organization, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTOIdd(organization, entity_name);

		// set updater params
		updater.setDto(organization);
		updater.setUpdatedUser(getLoggedUser(request));
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, organization.getIdd().toString()));

		// return response
		return updater.process();
	}
	
	/**
	 * Delete an organization unit
	 * @author luca
	 * @category standard delete REST method
	 * @param modality
	 * @param request
	 * @since 1.2.0
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<OrganizationUnitDTO> delete(@RequestBody OrganizationUnitDTO organization, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTOIdd(organization, entity_name);

		// set updater params
		updater.setDto(organization);
		updater.setUpdatedUser(getLoggedUser(request));
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, organization.getIdd().toString()));

		// return response
		return updater.enable(false);
	}
}
