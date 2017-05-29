package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;

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
import it.clevercom.echo.rd.jpa.specification.UserSpecification;
import it.clevercom.echo.rd.model.dto.AppSettingDTO;
import it.clevercom.echo.rd.model.entity.AppSetting;
import it.clevercom.echo.rd.repository.IAppSetting_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/assets/appsetting")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Application Setting Controller
 * @author luca
 */

public class AppSetting_rd_Controller extends EchoController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private IAppSetting_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	@PersistenceContext(unitName="rdPU")
	protected EntityManager em;

	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind entity name and id in exception message
	public static final String entity_name = "AppSetting";
	public static final String entity_id = "idappsetting";
	
	/**
	 * Get application setting by id
	 * @author luca
	 * @category standard get by id REST method 
	 * @param username
	 * @return
	 * @since 1.2.0
	 * @throws Exception.
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody AppSettingDTO get(@PathVariable Long id) throws Exception {
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting"), entity_name, entity_id, id.toString()));
		
		// validate
		validator.validateId(id, entity_name);
		
		// find entity
		AppSetting entity = repo.findOne(id);
		
		// check if entity has been found
		if (entity == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.returning.response"), entity_name, entity_id, id.toString()));
		return rdDozerMapper.map(entity, AppSettingDTO.class);
	}
	
	/**
	 * Get application setting list by criteria with pagination
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
	public @ResponseBody PagedDTO<AppSettingDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria,
			@RequestParam(defaultValue="*", required=false) String username,
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="1000", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue=entity_id, required=false) String field) throws Exception {
		
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// check enum string params
		validator.validateSort(sort);
		validator.validateSortField(field, AppSetting.class, entity_name);
		
		// set processor params
		CriteriaRequestProcessor<IAppSetting_rd_Repository, AppSetting, AppSettingDTO> processor = getProcessor();
		processor.setCriteria(criteria);
		processor.setPageCriteria(sort, field, page, size);
		
		// add username specification
		if (!username.equals("*")) {
			UserSpecification<AppSetting> u = new UserSpecification<AppSetting>(null, username);
			processor.addAndSpecification(u);
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
		
		// process data request
		return processor.process();		
	}
	
	/**
	 * Add application setting
	 * @author luca
	 * @category standard create REST method
	 * @param appSetting
	 * @param request
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<AppSettingDTO> add(@RequestBody AppSettingDTO appSetting, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate that username can perform the requested operation on appSetting
		validator.validateUsername(getLoggedUser(request), appSetting);
		
		// invoke order creator
		CreateRequestProcessor<IAppSetting_rd_Repository, AppSetting, AppSettingDTO> creator = getCreator();
		creator.setCreatedUser(getLoggedUser(request));
		creator.setDto(appSetting);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
		
		// process
		return creator.process();
	}
	
	/**
	 * Update an application setting
	 * @author luca
	 * @category standard update REST method
	 * @param appSetting
	 * @param request
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<AppSettingDTO> update(@RequestBody AppSettingDTO appSetting, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate that username can perform the requested operation on appSetting
		validator.validateUsername(getLoggedUser(request), appSetting);
		validator.validateDTOIdd(appSetting, entity_name);

		// set updater params
		UpdateRequestProcessor<IAppSetting_rd_Repository, AppSetting, AppSettingDTO> updater = getUpdater();
		updater.setSourceDto(appSetting);
		updater.setUpdatedUser(getLoggedUser(request));
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, appSetting.getIdd().toString()));

		// return response
		return updater.process();
	}
	
	/**
	 * Delete an application setting
	 * @author luca
	 * @category standard delete REST method
	 * @param appSetting
	 * @param request
	 * @return
	 * @since 1.2.0
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody String delete(@RequestBody AppSettingDTO appSetting, HttpServletRequest request) {
		return MessageFormat.format(env.getProperty("echo.api.crud.notsupported"), RequestMethod.DELETE.toString(), entity_name);
	}

	/**
	 * 
	 */
	@Override
	protected CreateRequestProcessor<IAppSetting_rd_Repository, AppSetting, AppSettingDTO> getCreator() {
		return new CreateRequestProcessor<IAppSetting_rd_Repository, AppSetting, AppSettingDTO>(repo, rdDozerMapper, AppSetting.class, entity_name, env, em);
	}

	/**
	 * 
	 */
	@Override
	protected UpdateRequestProcessor<IAppSetting_rd_Repository, AppSetting, AppSettingDTO> getUpdater() {
		return new UpdateRequestProcessor<IAppSetting_rd_Repository, AppSetting, AppSettingDTO>(repo, rdDozerMapper, entity_name, entity_id, env, em);
	}

	/**
	 * 
	 */
	@Override
	protected CriteriaRequestProcessor<IAppSetting_rd_Repository, AppSetting, AppSettingDTO> getProcessor() {
		return new CriteriaRequestProcessor<IAppSetting_rd_Repository, AppSetting, AppSettingDTO>(repo, rdDozerMapper, AppSettingDTO.class, entity_name, env);
	}
}
