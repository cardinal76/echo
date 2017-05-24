package it.clevercom.echo.tm.controller;

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

import it.clevercom.echo.common.component.Validator;
import it.clevercom.echo.common.controller.EchoController;
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.jpa.CreateRequestProcessor;
import it.clevercom.echo.common.jpa.CriteriaRequestProcessor;
import it.clevercom.echo.common.jpa.UpdateRequestProcessor;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.tm.model.dto.MaritalStatusDTO;
import it.clevercom.echo.tm.model.entity.Maritalstatus;
import it.clevercom.echo.tm.repository.IMaritalStatus_tm_Repository;

@Controller
@RestController
@RequestMapping("tm/types/maritalstatus")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Maritalstatus Controller
 * @author luca
 */

public class MaritalStatus_tm_Controller extends EchoController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private IMaritalStatus_tm_Repository repo;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	@PersistenceContext(unitName="tmPU")
	protected EntityManager em;

	// crud processors
	private CriteriaRequestProcessor<IMaritalStatus_tm_Repository, Maritalstatus, MaritalStatusDTO> processor;
	private CreateRequestProcessor<IMaritalStatus_tm_Repository, Maritalstatus, MaritalStatusDTO> creator;
	private UpdateRequestProcessor<IMaritalStatus_tm_Repository, Maritalstatus, MaritalStatusDTO> updater;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	public static final String entity_name = "Maritalstatus";
	public static final String entity_id = "idmaritalstatus";
	
	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		// construct creator
		creator = new CreateRequestProcessor<IMaritalStatus_tm_Repository, Maritalstatus, MaritalStatusDTO>(repo, rdDozerMapper, Maritalstatus.class, entity_name, env, em);
		// construct updater
		updater = new UpdateRequestProcessor<IMaritalStatus_tm_Repository, Maritalstatus, MaritalStatusDTO>(repo, rdDozerMapper, entity_name, entity_id, env, em);
		// costruct processor
		processor = new CriteriaRequestProcessor<IMaritalStatus_tm_Repository, Maritalstatus, MaritalStatusDTO>(repo, rdDozerMapper, MaritalStatusDTO.class, entity_name, env);
	}
	
	/**
	 * Get marital status by id
	 * @author luca
	 * @category standard get by id REST method
	 * @param id
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("tmTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody MaritalStatusDTO get(@PathVariable Long id) throws Exception {
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting"), entity_name, entity_id, id.toString()));
			
		// validate
		validator.validateId(id, entity_name);
		
		// find entity
		Maritalstatus entity = repo.findOne(id);
		
		// check if entity has been found
		if (entity == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.returning.response"), entity_name, entity_id, id.toString()));
		return rdDozerMapper.map(entity, MaritalStatusDTO.class);
	}
	
	/**
	 * Get marital status by criteria with pagination
	 * @author luca
	 * @category standard get by criteria REST method
	 * @param criteria
	 * @param page
	 * @param size
	 * @param sort
	 * @param field
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("tmTm")
	@RequestMapping(value="", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<MaritalStatusDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="20", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue="idmaritalstatus", required=false) String field) throws Exception {
		
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
				
		// validate
		validator.validateSort(sort);
		validator.validateSortField(field, Maritalstatus.class, entity_name);

		// set processor params
		processor.setCriteria(criteria);
		processor.setPageCriteria(sort, field, page, size);
				
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));		
		
		// process data request
		return processor.process();
	}
	
	/**
	 * Add marital status
	 * @author luca
	 * @category standard create REST method
	 * @param maritalStatus
	 * @param request
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("tmTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<MaritalStatusDTO> add(@RequestBody MaritalStatusDTO maritalStatus, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTONullIdd(maritalStatus, entity_id);
		
		// invoke order creator
		creator.setCreatedUser(getLoggedUser(request));
		creator.setDto(maritalStatus);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
		
		// process
		return creator.process();
	}
	
	/**
	 * Update marital status
	 * @author luca
	 * @category standard update REST method
	 * @param maritalStatus
	 * @param request
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("tmTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<MaritalStatusDTO> update(@RequestBody MaritalStatusDTO maritalStatus, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTOIdd(maritalStatus, entity_name);

		// set updater params
		updater.setSourceDto(maritalStatus);
		updater.setUpdatedUser(getLoggedUser(request));
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, maritalStatus.getIdd().toString()));

		// return response
		return updater.process();
	}
	
	/**
	 * Delete marital status
	 * @author luca
	 * @category standard delete REST method
	 * @param maritalStatus
	 * @param request
	 * @since 1.2.0
	 * @return
	 */
	@Transactional("tmTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<MaritalStatusDTO> delete(@RequestBody MaritalStatusDTO maritalStatus, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
						
		// validate
		validator.validateDTOIdd(maritalStatus, entity_name);

		// set updater params
		updater.setSourceDto(maritalStatus);
		updater.setUpdatedUser(getLoggedUser(request));
				
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, maritalStatus.getIdd().toString()));

		// return response
		return updater.enable(false);
	}

	@Override
	protected CreateRequestProcessor<?, ?, ?> getCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected UpdateRequestProcessor<?, ?, ?> getUpdater() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected CriteriaRequestProcessor<?, ?, ?> getProcessor() {
		// TODO Auto-generated method stub
		return null;
	}
}
