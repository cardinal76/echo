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
import it.clevercom.echo.rd.model.dto.WorkPriorityDTO;
import it.clevercom.echo.rd.model.entity.WorkPriority;
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

public class WorkPriority_rd_Controller extends EchoController {
	
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
	
	@Autowired
	private Validator validator;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	public static final String entity_name = "WorkPriority";
	public static final String entity_id = "idworkpriority";
	
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
		
		// check enum string params
		validator.validateSort(sort);
		
		CriteriaRequestProcessor<IWorkPriority_rd_Repository, WorkPriority, WorkPriorityDTO> rp = 
				new CriteriaRequestProcessor<IWorkPriority_rd_Repository, WorkPriority, WorkPriorityDTO>(repo, 
						rdDozerMapper, 
						WorkPriorityDTO.class, 
						entity_name, 
						criteria, 
						sort, 
						field, 
						page, 
						size,
						env);
		
		// process data request
		return rp.process();
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
	@Deprecated
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
