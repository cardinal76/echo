package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
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

import it.clevercom.echo.common.exception.model.BadRequestException;
import it.clevercom.echo.common.exception.model.PageNotFoundException;
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.jpa.helper.SearchCriteria;
import it.clevercom.echo.common.jpa.helper.SpecificationQueryHelper;
import it.clevercom.echo.common.jpa.helper.SpecificationsBuilder;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.common.util.JwtTokenUtils;
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

public class AppSetting_rd_Controller {
	
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
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	private static String entity_name = "AppSetting";
	private static String entity_id = "idappsetting";
	private static String entity_uqkey1 = "username";
	
	/**
	 * Get application setting list by Username
	 * @param username
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{username}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<AppSettingDTO> get(@PathVariable String username) throws Exception {
		PagedDTO<AppSettingDTO> dto = getByCriteria("username:" + username, 1, 1000, "asc", "idsetting");
		if (entity_name == null) throw new RecordNotFoundException(AppSetting_rd_Controller.entity_name, entity_id, entity_uqkey1);
		return dto;
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
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="1000", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue="idappsetting", required=false) String field) throws Exception {
		
		// create paged request
		PageRequest request = null;
		
		if (sort.equalsIgnoreCase("asc")) {
			 request = new PageRequest(page-1, size, Direction.ASC, field);
		} else if (sort.equalsIgnoreCase("desc")) {
			request = new PageRequest(page-1, size, Direction.DESC, field);
		} else {
			throw new BadRequestException(env.getProperty("echo.api.exception.search.sort.wrongsortparam"));
		}
		
		// create predicate if criteria is not null
		Page<AppSetting> rs = null;
		
		if (!criteria.equals("null")) {
	        SpecificationsBuilder<AppSetting, SpecificationQueryHelper<AppSetting>> builder = new SpecificationsBuilder<AppSetting, SpecificationQueryHelper<AppSetting>>();
	        Pattern pattern = Pattern.compile(SearchCriteria.pattern);
	        Matcher matcher = pattern.matcher(criteria + ",");
	        while (matcher.find()) {
	            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
	        }
	        Specification<AppSetting> spec = builder.build();
	        
	        // obtain records
	        rs = repo.findAll(spec, request);
		} else {
			rs = repo.findAll(request);
		}
		
		int totalPages = rs.getTotalPages();
        long totalElements = rs.getTotalElements();
		List<AppSetting> entity = rs.getContent();
		
		if (entity.size() == 0) throw new PageNotFoundException(AppSetting_rd_Controller.entity_name, page);
		
		// map list
		List<AppSettingDTO> appSettingDTOList = new ArrayList<AppSettingDTO>();
		for (AppSetting s: entity) {
			appSettingDTOList.add(rdDozerMapper.map(s, AppSettingDTO.class));
		}
		
		// assembly dto
		PagedDTO<AppSettingDTO> dto = new PagedDTO<AppSettingDTO>();
		dto.setElements(appSettingDTOList);
		dto.setPageSize(size);
		dto.setCurrentPage(page);
		dto.setTotalPages(totalPages);
		dto.setTotalElements(totalElements);
		return dto;
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
		appSetting.setUsername(username);
		
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
		appSetting.setUsername(username);
		
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
