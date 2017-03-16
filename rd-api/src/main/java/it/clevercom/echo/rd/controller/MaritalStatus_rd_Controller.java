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
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.common.util.JwtTokenUtils;
import it.clevercom.echo.rd.model.dto.MaritalstatusDTO;
import it.clevercom.echo.rd.model.dto.PagedDTO;
import it.clevercom.echo.rd.model.entity.Maritalstatus;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationQueryHelper;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationsBuilder;
import it.clevercom.echo.rd.repository.IMaritalStatus_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/maritalstatus")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

public class MaritalStatus_rd_Controller {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private IMaritalStatus_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Value("${jwt.token.header}")
	private String tokenHeader;
	
	@Autowired
	private JwtTokenUtils tokenUtils;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	private static String entity = "Maritalstatus";
	
	/**
	 * @param username
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody MaritalstatusDTO get(@PathVariable Long id) throws Exception {
		Maritalstatus entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(MaritalStatus_rd_Controller.entity, id.toString());
		return rdDozerMapper.map(entity, MaritalstatusDTO.class);
	}
	
	/**
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
	public @ResponseBody PagedDTO<MaritalstatusDTO> getByCriteria (	@RequestParam(defaultValue="null", required=false) String criteria, 
																	@RequestParam(defaultValue="1", required=false) int page, 
																	@RequestParam(defaultValue="20", required=false) int size, 
																	@RequestParam(defaultValue="asc", required=false) String sort, 
																	@RequestParam(defaultValue="idmaritalstatus", required=false) String field) throws Exception {
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
		Page<Maritalstatus> rs = null;
		
		if (!criteria.equals("null")) {
	        SpecificationsBuilder<Maritalstatus, SpecificationQueryHelper<Maritalstatus>> builder = new SpecificationsBuilder<Maritalstatus, SpecificationQueryHelper<Maritalstatus>>();
	        Pattern pattern = Pattern.compile("(\\w+)(:|<|>)(\\w+)");
	        Matcher matcher = pattern.matcher(criteria + ",");
	        while (matcher.find()) {
	            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
	        }
	        Specification<Maritalstatus> spec = builder.build();
	        
	        // obtain records
	        rs = repo.findAll(spec, request);
		} else {
			rs = repo.findAll(request);
		}
		
		int totalPages = rs.getTotalPages();
        long totalElements = rs.getTotalElements();
		List<Maritalstatus> entity = rs.getContent();
		
		if (entity.size() == 0) throw new PageNotFoundException(MaritalStatus_rd_Controller.entity, page);
		
		// map list
		List<MaritalstatusDTO> maritalStatusDTOList = new ArrayList<MaritalstatusDTO>();
		for (Maritalstatus s: entity) {
			maritalStatusDTOList.add(rdDozerMapper.map(s, MaritalstatusDTO.class));
		}
		
		// assembly dto
		PagedDTO<MaritalstatusDTO> dto = new PagedDTO<MaritalstatusDTO>();
		dto.setElements(maritalStatusDTOList);
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
	public @ResponseBody CreateResponseDTO<MaritalstatusDTO> add(@RequestBody MaritalstatusDTO maritalStatus, HttpServletRequest request) throws Exception {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		
		// map
		Maritalstatus entity = rdDozerMapper.map(maritalStatus, Maritalstatus.class);
		
		// add technical field
		entity.setUserupdate(username);
		
		// save and map to out dto
		entity = repo.saveAndFlush(entity);
		maritalStatus = rdDozerMapper.map(entity, MaritalstatusDTO.class);
		
		// create standard response
		CreateResponseDTO<MaritalstatusDTO> response = new CreateResponseDTO<MaritalstatusDTO>();
		response.setEntityName(MaritalStatus_rd_Controller.entity);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), MaritalStatus_rd_Controller.entity));
		List<MaritalstatusDTO> maritalStatusDTOs = new ArrayList<MaritalstatusDTO>();
		maritalStatusDTOs.add(maritalStatus);
		response.setNewValue(maritalStatusDTOs);
		
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
	public @ResponseBody UpdateResponseDTO<MaritalstatusDTO> update(@RequestBody MaritalstatusDTO maritalStatus, HttpServletRequest request) throws Exception {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		
		// if an id is not present throw bad request
		if(maritalStatus.getIdmaritalstatus()==null) throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.missing.id"), MaritalStatus_rd_Controller.entity));
		
		// find entity to update (oldValue)
		Maritalstatus oldValueEntity = repo.findOne(maritalStatus.getIdmaritalstatus()); 
		// if an entity with given id is not found in DB throw record not found
		if (oldValueEntity==null) throw new RecordNotFoundException(MaritalStatus_rd_Controller.entity, maritalStatus.getIdmaritalstatus().toString());
		// map old value to a dto
		MaritalstatusDTO oldValueDTO = rdDozerMapper.map(oldValueEntity, MaritalstatusDTO.class);

		// begin update of oldValue
		rdDozerMapper.map(maritalStatus, oldValueEntity);
		
		// add technical field
		oldValueEntity.setUserupdate(username);
		oldValueEntity.setUpdated(new Date());
		
		// save and map to out dto
		Maritalstatus newValueEntity = repo.saveAndFlush(oldValueEntity);
		MaritalstatusDTO newValueDTO = rdDozerMapper.map(newValueEntity, MaritalstatusDTO.class);
				
		// create standard response
		UpdateResponseDTO<MaritalstatusDTO> response = new UpdateResponseDTO<MaritalstatusDTO>();
		response.setEntityName(MaritalStatus_rd_Controller.entity);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), MaritalStatus_rd_Controller.entity));
		// add new dtos values
		List<MaritalstatusDTO> newMaritalstatusDTOs = new ArrayList<MaritalstatusDTO>();
		newMaritalstatusDTOs.add(newValueDTO);
		response.setNewValue(newMaritalstatusDTOs);
		// add old dtos values
		List<MaritalstatusDTO> oldMaritalstatusDTOs = new ArrayList<MaritalstatusDTO>();
		oldMaritalstatusDTOs.add(oldValueDTO);
		response.setOldValue(oldMaritalstatusDTOs);
		
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
	public @ResponseBody String delete(@RequestBody MaritalstatusDTO maritalStatus, HttpServletRequest request) {
		return MessageFormat.format(env.getProperty("echo.api.crud.notsupported"), RequestMethod.DELETE.toString(), MaritalStatus_rd_Controller.entity);
	}
}