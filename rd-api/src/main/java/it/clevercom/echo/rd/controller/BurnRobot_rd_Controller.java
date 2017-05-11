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
import it.clevercom.echo.rd.model.dto.BurnRobotDTO;
import it.clevercom.echo.rd.model.entity.BurnRobot;
import it.clevercom.echo.rd.repository.IBurnRobot_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/assets/burnrobot")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Burn Robot Controller
 * @author luca
 */

public class BurnRobot_rd_Controller extends EchoController {

	@Autowired
	private Environment env;
	
	@Autowired
	private IBurnRobot_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	@PersistenceContext(unitName="rdPU")
	protected EntityManager em;

	// crud processors
	private CriteriaRequestProcessor<IBurnRobot_rd_Repository, BurnRobot, BurnRobotDTO> processor;
	private CreateRequestProcessor<IBurnRobot_rd_Repository, BurnRobot, BurnRobotDTO> creator;
	private UpdateRequestProcessor<IBurnRobot_rd_Repository, BurnRobot, BurnRobotDTO> updater;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	public static final String entity_name = "BurnRobot";
	public static final String entity_id = "idburnrobot";
	
	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		// construct creator
		creator = new CreateRequestProcessor<IBurnRobot_rd_Repository, BurnRobot, BurnRobotDTO>(repo, rdDozerMapper, BurnRobot.class, entity_name, env, em);
		// construct updater
		updater = new UpdateRequestProcessor<IBurnRobot_rd_Repository, BurnRobot, BurnRobotDTO>(repo, rdDozerMapper, entity_name, entity_id, env, em);
		// costruct processor
		processor = new CriteriaRequestProcessor<IBurnRobot_rd_Repository, BurnRobot, BurnRobotDTO>(repo, rdDozerMapper, BurnRobotDTO.class, entity_name, env);
	}
	
	/**
	 * Get a burn robot by id
	 * @author luca
	 * @category standard get by id REST method
	 * @param id
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody BurnRobotDTO get(@PathVariable Long id) throws Exception {
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting"), entity_name, entity_id, id.toString()));
		
		// validate
		validator.validateId(id, entity_name);
		
		// find entity
		BurnRobot entity = repo.findOne(id);
		
		// check if entity has been found
		if (entity == null) { 
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.returning.response"), entity_name, entity_id, id.toString()));
		return rdDozerMapper.map(entity, BurnRobotDTO.class);
	}
	
	/**
	 * Get a burn robot list by criteria with pagination
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
	@Transactional("rdTm")
	@RequestMapping(value="", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<BurnRobotDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="1000", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue=entity_id, required=false) String field) throws Exception {
		
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateSort(sort);
		validator.validateSortField(field, BurnRobot.class, entity_name);
		
		// set processor params
		processor.setCriteria(criteria);
		processor.setPageCriteria(sort, field, page, size);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
		
		// process data request
		return processor.process();
	}
	
	/**
	 * Add a burn robot
	 * @author luca
	 * @category standard create REST method
	 * @param burnrobot
	 * @param request
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<BurnRobotDTO> add(@RequestBody BurnRobotDTO burnRobot, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTONullIdd(burnRobot, entity_id);
		
		// invoke order creator
		creator.setCreatedUser(getLoggedUser(request));
		creator.setDto(burnRobot);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
		
		// process
		return creator.process();
	}
	
	/**
	 * Update a burn robot
	 * @author luca
	 * @category standard update REST method
	 * @param burnRobot
	 * @param request
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<BurnRobotDTO> update(@RequestBody BurnRobotDTO burnRobot, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTOIdd(burnRobot, entity_name);

		// set updater params
		updater.setDto(burnRobot);
		updater.setUpdatedUser(getLoggedUser(request));
				
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, burnRobot.getIdd().toString()));

		// return response
		return updater.process();
	}
	
	/**
	 * Delete a burn robot
	 * @author luca
	 * @category standard delete REST method
	 * @param burnRobot
	 * @param request
	 * @return
	 * @since 1.2.0
	 * @throws Exception 
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<BurnRobotDTO> delete(@RequestBody BurnRobotDTO burnRobot, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
				
		// validate
		validator.validateDTOIdd(burnRobot, entity_name);

		// set updater params
		updater.setDto(burnRobot);
		updater.setUpdatedUser(getLoggedUser(request));
				
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, burnRobot.getIdd().toString()));

		// return response
		return updater.enable(false);
	}
}
