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
import it.clevercom.echo.rd.model.dto.WorkStatusDTO;
import it.clevercom.echo.rd.model.entity.WorkStatus;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationQueryHelper;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationsBuilder;
import it.clevercom.echo.rd.repository.IWorkStatus_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/workstatus")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

public class WorkStatus_rd_Controller {
	@Autowired
	private Environment env;
	
	@Autowired
	private IWorkStatus_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Value("${jwt.token.header}")
	private String tokenHeader;
	
	@Autowired
	private JwtTokenUtils tokenUtils;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	private static String entity = "WorkStatus";
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws EchoException
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody WorkStatusDTO get(@PathVariable Long id) throws Exception {
		WorkStatus entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(WorkStatus_rd_Controller.entity, id.toString());
		return rdDozerMapper.map(entity, WorkStatusDTO.class);
	}
	
	/**
	 * @param criteria
	 * @param page
	 * @param size
	 * @param sort
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<WorkStatusDTO> getByCriteria (	@RequestParam(defaultValue="null", required=false) String criteria, 
																	@RequestParam(defaultValue="1", required=false) int page, 
																	@RequestParam(defaultValue="1000", required=false) int size, 
																	@RequestParam(defaultValue="asc", required=false) String sort, 
																	@RequestParam(defaultValue="code", required=false) String field) throws Exception {
		// create paged request
		PageRequest request = null;
		
		if (sort.equals("asc")) {
			 request = new PageRequest(page-1, size, Direction.ASC, field);
		} else if (sort.equals("desc")) {
			request = new PageRequest(page-1, size, Direction.DESC, field);
		} else {
			throw new BadRequestException(env.getProperty("echo.api.exception.search.sort.wrongsortparam"));
		}
		
		// create predicate if criteria is not null
		Page<WorkStatus> rs = null;
		
		if (!criteria.equals("null")) {
	        SpecificationsBuilder<WorkStatus, SpecificationQueryHelper<WorkStatus>> builder = new SpecificationsBuilder<WorkStatus, SpecificationQueryHelper<WorkStatus>>();
	        Pattern pattern = Pattern.compile("(\\w+)(:|<|>)(\\w+)");
	        Matcher matcher = pattern.matcher(criteria + ",");
	        while (matcher.find()) {
	            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
	        }
	        Specification<WorkStatus> spec = builder.build();
	        
	        // obtain records
	        rs = repo.findAll(spec, request);
		} else {
			rs = repo.findAll(request);
		}
		
		int totalPages = rs.getTotalPages();
        long totalElements = rs.getTotalElements();
		List<WorkStatus> entity = rs.getContent();
		
		if (entity.size() == 0) throw new PageNotFoundException(WorkStatus_rd_Controller.entity, page);
		
		// map list
		List<WorkStatusDTO> workStatusDTOList = new ArrayList<WorkStatusDTO>();
		for (WorkStatus s: entity) {
			workStatusDTOList.add(rdDozerMapper.map(s, WorkStatusDTO.class));
		}
		
		// assembly dto
		PagedDTO<WorkStatusDTO> dto = new PagedDTO<WorkStatusDTO>();
		dto.setElements(workStatusDTOList);
		dto.setPageSize(size);
		dto.setCurrentPage(page);
		dto.setTotalPages(totalPages);
		dto.setTotalElements(totalElements);
		return dto;
	}
	
	/**
	 * 
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<WorkStatusDTO> add(@RequestBody WorkStatusDTO burnrobot, HttpServletRequest request) throws Exception {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		
		// map
		WorkStatus entity = rdDozerMapper.map(burnrobot, WorkStatus.class);
		
		// add technical field
		entity.setUserupdate(username);
		
		// save and map to out dto
		entity = repo.saveAndFlush(entity);
		burnrobot = rdDozerMapper.map(entity, WorkStatusDTO.class);
		
		// create standard response
		CreateResponseDTO<WorkStatusDTO> response = new CreateResponseDTO<WorkStatusDTO>();
		response.setEntityName(WorkStatus_rd_Controller.entity);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), WorkStatus_rd_Controller.entity));
		List<WorkStatusDTO> workStatusDTOs = new ArrayList<WorkStatusDTO>();
		workStatusDTOs.add(burnrobot);
		response.setNewValue(workStatusDTOs);
		
		// return standard response
		return response;
	}
	
	/**
	 * 
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<WorkStatusDTO> update(@RequestBody WorkStatusDTO workStatus, HttpServletRequest request) throws Exception {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		
		// if an id is not present throw bad request
		if(workStatus.getIdworkstatus()==null) throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.missing.id"), WorkStatus_rd_Controller.entity));
		
		// find entity to update (oldValue)
		WorkStatus oldValueEntity = repo.findOne(workStatus.getIdworkstatus()); 
		// if an entity with given id is not found in DB throw record not found
		if (oldValueEntity==null) throw new RecordNotFoundException(WorkStatus_rd_Controller.entity, workStatus.getIdworkstatus().toString());
		// map old value to a dto
		WorkStatusDTO oldValueDTO = rdDozerMapper.map(oldValueEntity, WorkStatusDTO.class);

		// begin update of oldValue
		rdDozerMapper.map(workStatus, oldValueEntity);
		
		// add technical field
		oldValueEntity.setUserupdate(username);
		oldValueEntity.setUpdated(new Date());
		
		// save and map to out dto
		WorkStatus newValueEntity = repo.saveAndFlush(oldValueEntity);
		WorkStatusDTO newValueDTO = rdDozerMapper.map(newValueEntity, WorkStatusDTO.class);
				
		// create standard response
		UpdateResponseDTO<WorkStatusDTO> response = new UpdateResponseDTO<WorkStatusDTO>();
		response.setEntityName(WorkStatus_rd_Controller.entity);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), WorkStatus_rd_Controller.entity));
		// add new dtos values
		List<WorkStatusDTO> newWorkStatusDTOs = new ArrayList<WorkStatusDTO>();
		newWorkStatusDTOs.add(newValueDTO);
		response.setNewValue(newWorkStatusDTOs);
		// add old dtos values
		List<WorkStatusDTO> oldWorkStatusDTOs = new ArrayList<WorkStatusDTO>();
		oldWorkStatusDTOs.add(oldValueDTO);
		response.setOldValue(oldWorkStatusDTOs);
		
		// return response
		return response;
	}
	
	/**
	 * 
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody String delete(@RequestBody WorkStatusDTO workStatus, HttpServletRequest request) {
		return MessageFormat.format(env.getProperty("echo.api.crud.notsupported"), RequestMethod.DELETE.toString(), WorkStatus_rd_Controller.entity);
	}
}
