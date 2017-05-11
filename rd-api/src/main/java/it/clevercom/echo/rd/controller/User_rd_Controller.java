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
import it.clevercom.echo.rd.model.dto.ServiceDTO;
import it.clevercom.echo.rd.model.dto.UserDTO;
import it.clevercom.echo.rd.model.entity.Service;
import it.clevercom.echo.rd.model.entity.User;
import it.clevercom.echo.rd.repository.IService_rd_Repository;
import it.clevercom.echo.rd.repository.IUser_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/assets/user")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * User controller
 * @author luca
 */

public class User_rd_Controller extends EchoController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private IUser_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	@PersistenceContext(unitName="rdPU")
	protected EntityManager em;

	// crud processors
	private CriteriaRequestProcessor<IUser_rd_Repository, User, UserDTO> processor;
	private CreateRequestProcessor<IUser_rd_Repository, User, UserDTO> creator;
	private UpdateRequestProcessor<IUser_rd_Repository, User, UserDTO> updater;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	public static final String entity_name = "User";
	public static final String entity_id = "username";

	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		// construct creator
		creator = new CreateRequestProcessor<IUser_rd_Repository, User, UserDTO>(repo, rdDozerMapper, User.class, entity_name, env, em);
		// construct updater
		updater = new UpdateRequestProcessor<IUser_rd_Repository, User, UserDTO>(repo, rdDozerMapper, entity_name, entity_id, env, em);
		// costruct processor
		processor = new CriteriaRequestProcessor<IUser_rd_Repository, User, UserDTO>(repo, rdDozerMapper, UserDTO.class, entity_name, env);
	}
	
	/**
	 * Get user by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{username}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UserDTO get(@PathVariable String username) throws Exception {
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting"), entity_name, entity_id, username));
				
		// find entity
		User entity = repo.findOne(username);
		
		// check if entity has been found
		if (entity == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, username));
			throw new RecordNotFoundException(entity_name, entity_id, username);
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.returning.response"), entity_name, entity_id, username));
		return rdDozerMapper.map(entity, UserDTO.class);
	}
	
	/**
	 * Get user by criteria with pagination
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
	public @ResponseBody PagedDTO<UserDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="15", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue="username", required=false) String field) throws Exception {
		
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateSort(sort);
		validator.validateSortField(field, User.class, entity_name);
		
		// set processor params
		processor.setCriteria(criteria);
		processor.setPageCriteria(sort, field, page, size);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
		
		// process data request
		return processor.process();
	}
	
	/**
	 * Add user 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<UserDTO> add(@RequestBody UserDTO user, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTONullIdd(user, entity_id);
				
		// invoke order creator
		creator.setCreatedUser(getLoggedUser(request));
		creator.setDto(user);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
		
		// process
		return creator.process();
	}
	
	/**
	 * Update a user
	 * @param phraseBook
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<UserDTO> update(@RequestBody UserDTO user, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate that username can perform the requested operation on appSetting
		validator.validateDTOIdd(user, entity_name);

		// set updater params
		updater.setDto(user);
		updater.setUpdatedUser(getLoggedUser(request));
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, user.getIdd().toString()));

		// return response
		return updater.process();
	}
	
	/**
	 * Delete a user
	 * @param phraseBook
	 * @param request
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<UserDTO> delete(@RequestBody UserDTO user, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
				
		// validate that username can perform the requested operation on appSetting
		validator.validateDTOIdd(user, entity_name);

		// set updater params
		updater.setDto(user);
		updater.setUpdatedUser(getLoggedUser(request));
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, user.getIdd().toString()));

		// return response
		return updater.enable(false);
	}
}
