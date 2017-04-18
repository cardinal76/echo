package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import it.clevercom.echo.common.exception.model.BadRequestException;
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.jpa.CriteriaRequestProcessor;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.common.util.JwtTokenUtils;
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
	
	@Value("${jwt.token.header}")
	private String tokenHeader;
	
	@Autowired
	private JwtTokenUtils tokenUtils;
	
	@Autowired
	private Validator validator;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	private static String entity_name = "AppSetting";
	private static String entity_id = "idappsetting";
	private static String entity_uqkey1 = "username";
	
	/**
	 * Get application setting list by Username
	 * @param username
	 * @return
	 * @throws Exception.
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody AppSettingDTO get(@PathVariable Long id) throws Exception {
		AppSetting entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		return rdDozerMapper.map(entity, AppSettingDTO.class);
	}
	
	/**
	 * Get application setting list by criteria with pagination
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
	public @ResponseBody PagedDTO<AppSettingDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria,
			@RequestParam(defaultValue="*", required=false) String username,
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="1000", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue="idappsetting", required=false) String field) throws Exception {
		
		// check enum string params
		validator.validateSort(sort);
		
		CriteriaRequestProcessor<IAppSetting_rd_Repository, AppSetting, AppSettingDTO> rp = 
				new CriteriaRequestProcessor<IAppSetting_rd_Repository, AppSetting, AppSettingDTO>(repo, 
						rdDozerMapper, 
						AppSettingDTO.class, 
						entity_name, 
						criteria, 
						sort, 
						field, 
						page, 
						size);
		
		// check 
		if (!username.equals("*")) {
			UserSpecification<AppSetting> u = new UserSpecification<AppSetting>(null, username);
			rp.addAndSpecification(u);
		}
		
		// process data request
		return rp.process();		
	}
	
	/**
	 * Add application setting
	 * @param appSetting
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<AppSettingDTO> add(@RequestBody AppSettingDTO appSetting, HttpServletRequest request) throws Exception {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		appSetting.setUser(username);
		
		// map
		AppSetting entity = rdDozerMapper.map(appSetting, AppSetting.class);
		
		// add technical field
		entity.setUserupdate(username);
		
		// save and map to out dto
		entity = repo.saveAndFlush(entity);
		appSetting = rdDozerMapper.map(entity, AppSettingDTO.class);
		
		// create standard response
		CreateResponseDTO<AppSettingDTO> response = new CreateResponseDTO<AppSettingDTO>();
		response.setEntityName(AppSetting_rd_Controller.entity_name);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), AppSetting_rd_Controller.entity_name));
		List<AppSettingDTO> appSettingDTOs = new ArrayList<AppSettingDTO>();
		appSettingDTOs.add(appSetting);
		response.setNewValue(appSettingDTOs);
		
		// return standard response
		return response;
	}
	
	/**
	 * Update an application setting
	 * @param appSetting
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<AppSettingDTO> update(@RequestBody AppSettingDTO appSetting, HttpServletRequest request) throws Exception {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		appSetting.setUser(username);
		
		// if an id is not present throw bad request
		if(appSetting.getIdappsetting()==null) throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.missing.id"), AppSetting_rd_Controller.entity_name));
		
		// find entity to update (oldValue)
		AppSetting oldValueEntity = repo.findOne(appSetting.getIdappsetting()); 
		// if an entity with given id is not found in DB throw record not found
		if (oldValueEntity==null) throw new RecordNotFoundException(AppSetting_rd_Controller.entity_name, entity_id, appSetting.getIdappsetting().toString());
		// get created date
		Date created = oldValueEntity.getCreated();
		// map old value to a dto
		AppSettingDTO oldValueDTO = rdDozerMapper.map(oldValueEntity, AppSettingDTO.class);

		// begin update of oldValue
		rdDozerMapper.map(appSetting, oldValueEntity);
		
		// add technical field
		oldValueEntity.setUserupdate(username);
		oldValueEntity.setUpdated(new Date());
		oldValueEntity.setCreated(created);
		
		// save and map to out dto
		AppSetting newValueEntity = repo.saveAndFlush(oldValueEntity);
		AppSettingDTO newValueDTO = rdDozerMapper.map(newValueEntity, AppSettingDTO.class);
				
		// create standard response
		UpdateResponseDTO<AppSettingDTO> response = new UpdateResponseDTO<AppSettingDTO>();
		response.setEntityName(AppSetting_rd_Controller.entity_name);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), AppSetting_rd_Controller.entity_name));
		// add new dtos values
		List<AppSettingDTO> newAppSettingDTOs = new ArrayList<AppSettingDTO>();
		newAppSettingDTOs.add(newValueDTO);
		response.setNewValue(newAppSettingDTOs);
		// add old dtos values
		List<AppSettingDTO> oldAppSettingDTOs = new ArrayList<AppSettingDTO>();
		oldAppSettingDTOs.add(oldValueDTO);
		response.setOldValue(oldAppSettingDTOs);
		
		// return response
		return response;
	}
	
	/**
	 * Delete an application setting
	 * @param appSetting
	 * @param request
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody String delete(@RequestBody AppSettingDTO appSetting, HttpServletRequest request) {
		return MessageFormat.format(env.getProperty("echo.api.crud.notsupported"), RequestMethod.DELETE.toString(), AppSetting_rd_Controller.entity_name);
	}
}
