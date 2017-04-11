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
import it.clevercom.echo.common.exception.model.EchoException;
import it.clevercom.echo.common.exception.model.PageNotFoundException;
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.common.util.JwtTokenUtils;
import it.clevercom.echo.rd.model.dto.PagedDTO;
import it.clevercom.echo.rd.model.dto.WorkPriorityDTO;
import it.clevercom.echo.rd.model.entity.WorkPriority;
import it.clevercom.echo.common.model.jpa.helper.SearchCriteria;
import it.clevercom.echo.common.model.jpa.helper.SpecificationQueryHelper;
import it.clevercom.echo.common.model.jpa.helper.SpecificationsBuilder;
import it.clevercom.echo.rd.repository.IWorkPriority_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/workpriority")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Work Priority Controller
 * @author luca
 */

public class WorkPriority_rd_Controller {
	@Autowired
	private Environment env;
	
	@Autowired
	private IWorkPriority_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Value("${jwt.token.header}")
	private String tokenHeader;
	
	@Autowired
	private JwtTokenUtils tokenUtils;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	private static String entity_name = "WorkPriority";
	private static String entity_id = "idworkpriority";
	
	/**
	 * Get work priority by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody WorkPriorityDTO get(@PathVariable Long id) throws Exception {
		WorkPriority entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		return rdDozerMapper.map(entity, WorkPriorityDTO.class);
	}
	
	/**
	 * Get work priority by criteria with pagination
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
	public @ResponseBody PagedDTO<WorkPriorityDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="4", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue="code", required=false) String field) throws Exception {
		
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
		Page<WorkPriority> rs = null;
		
		if (!criteria.equals("null")) {
	        SpecificationsBuilder<WorkPriority, SpecificationQueryHelper<WorkPriority>> builder = new SpecificationsBuilder<WorkPriority, SpecificationQueryHelper<WorkPriority>>();
	        Pattern pattern = Pattern.compile(SearchCriteria.pattern);
	        Matcher matcher = pattern.matcher(criteria + ",");
	        while (matcher.find()) {
	            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
	        }
	        Specification<WorkPriority> spec = builder.build();
	        
	        // obtain records
	        rs = repo.findAll(spec, request);
		} else {
			rs = repo.findAll(request);
		}
		
		int totalPages = rs.getTotalPages();
        long totalElements = rs.getTotalElements();
		List<WorkPriority> entity = rs.getContent();
		
		if (entity.size() == 0) throw new PageNotFoundException(WorkPriority_rd_Controller.entity_name, page);
		
		// map list
		List<WorkPriorityDTO> workPriorityDTOList = new ArrayList<WorkPriorityDTO>();
		for (WorkPriority s: entity) {
			workPriorityDTOList.add(rdDozerMapper.map(s, WorkPriorityDTO.class));
		}
		
		// assembly dto
		PagedDTO<WorkPriorityDTO> dto = new PagedDTO<WorkPriorityDTO>();
		dto.setElements(workPriorityDTOList);
		dto.setPageSize(size);
		dto.setCurrentPage(page);
		dto.setTotalPages(totalPages);
		dto.setTotalElements(totalElements);
		return dto;
	}
	
	/**
	 * Add work priority
	 * @param workpriority
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<WorkPriorityDTO> add(@RequestBody WorkPriorityDTO workpriority, HttpServletRequest request) throws Exception {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		
		// map
		WorkPriority entity = rdDozerMapper.map(workpriority, WorkPriority.class);
		
		// add technical field
		entity.setUserupdate(username);
		
		// save and map to out dto
		entity = repo.saveAndFlush(entity);
		workpriority = rdDozerMapper.map(entity, WorkPriorityDTO.class);
		
		// create standard response
		CreateResponseDTO<WorkPriorityDTO> response = new CreateResponseDTO<WorkPriorityDTO>();
		response.setEntityName(WorkPriority_rd_Controller.entity_name);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), WorkPriority_rd_Controller.entity_name));
		List<WorkPriorityDTO> workPriorityDTOs = new ArrayList<WorkPriorityDTO>();
		workPriorityDTOs.add(workpriority);
		response.setNewValue(workPriorityDTOs);
		
		// return standard response
		return response;
	}
	
	/**
	 * Update work priority
	 * @param workpriority
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<WorkPriorityDTO> update(@RequestBody WorkPriorityDTO workpriority, HttpServletRequest request) throws Exception {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		
		// if an id is not present throw bad request
		if(workpriority.getIdworkpriority()==null) throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.missing.id"), WorkPriority_rd_Controller.entity_name));
		
		// find entity to update (oldValue)
		WorkPriority oldValueEntity = repo.findOne(workpriority.getIdworkpriority()); 
		// if an entity with given id is not found in DB throw record not found
		if (oldValueEntity==null) throw new RecordNotFoundException(entity_name, entity_id, workpriority.getIdworkpriority().toString());
		// get created date
		Date created = oldValueEntity.getCreated();		
		// map old value to a dto
		WorkPriorityDTO oldValueDTO = rdDozerMapper.map(oldValueEntity, WorkPriorityDTO.class);

		// begin update of oldValue
		rdDozerMapper.map(workpriority, oldValueEntity);
		
		// add technical field
		oldValueEntity.setUserupdate(username);
		oldValueEntity.setUpdated(new Date());
		oldValueEntity.setCreated(created);
		
		// save and map to out dto
		WorkPriority newValueEntity = repo.saveAndFlush(oldValueEntity);
		WorkPriorityDTO newValueDTO = rdDozerMapper.map(newValueEntity, WorkPriorityDTO.class);
				
		// create standard response
		UpdateResponseDTO<WorkPriorityDTO> response = new UpdateResponseDTO<WorkPriorityDTO>();
		response.setEntityName(WorkPriority_rd_Controller.entity_name);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), WorkPriority_rd_Controller.entity_name));
		// add new dtos values
		List<WorkPriorityDTO> newWorkPriorityDTOs = new ArrayList<WorkPriorityDTO>();
		newWorkPriorityDTOs.add(newValueDTO);
		response.setNewValue(newWorkPriorityDTOs);
		// add old dtos values
		List<WorkPriorityDTO> oldWorkPriorityDTOs = new ArrayList<WorkPriorityDTO>();
		oldWorkPriorityDTOs.add(oldValueDTO);
		response.setOldValue(oldWorkPriorityDTOs);
		
		// return response
		return response;
	}
	
	/**
	 * Delete work priority
	 * @param workpriority
	 * @param request
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody String delete(@RequestBody WorkPriorityDTO workpriority, HttpServletRequest request) {
		return MessageFormat.format(env.getProperty("echo.api.crud.notsupported"), RequestMethod.DELETE.toString(), WorkPriority_rd_Controller.entity_name);
	}	
}
