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
import it.clevercom.echo.rd.model.dto.BodyApparatusDTO;
import it.clevercom.echo.rd.model.dto.PagedDTO;
import it.clevercom.echo.rd.model.entity.BodyApparatus;
import it.clevercom.echo.rd.model.jpa.helper.SearchCriteria;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationQueryHelper;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationsBuilder;
import it.clevercom.echo.rd.repository.IBodyApparatus_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/bodyapparatus")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

public class BodyApparatus_rd_Controller {

	@Autowired
	private Environment env;
	
	@Autowired
	private IBodyApparatus_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Value("${jwt.token.header}")
	private String tokenHeader;
	
	@Autowired
	private JwtTokenUtils tokenUtils;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	private static String entity = "BodyApparatus";
	
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
	public @ResponseBody BodyApparatusDTO get(@PathVariable Long id) throws Exception {
		BodyApparatus entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(BodyApparatus_rd_Controller.entity, id.toString());
		return rdDozerMapper.map(entity, BodyApparatusDTO.class);
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
	public @ResponseBody PagedDTO<BodyApparatusDTO> getByCriteria (	@RequestParam(defaultValue="null", required=false) String criteria, 
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
		Page<BodyApparatus> rs = null;
		
		if (!criteria.equals("null")) {
	        SpecificationsBuilder<BodyApparatus, SpecificationQueryHelper<BodyApparatus>> builder = new SpecificationsBuilder<BodyApparatus, SpecificationQueryHelper<BodyApparatus>>();
	        Pattern pattern = Pattern.compile(SearchCriteria.pattern);
	        Matcher matcher = pattern.matcher(criteria + ",");
	        while (matcher.find()) {
	            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
	        }
	        Specification<BodyApparatus> spec = builder.build();
	        
	        // obtain records
	        rs = repo.findAll(spec, request);
		} else {
			rs = repo.findAll(request);
		}
		
		int totalPages = rs.getTotalPages();
        long totalElements = rs.getTotalElements();
		List<BodyApparatus> entity = rs.getContent();
		
		if (entity.size() == 0) throw new PageNotFoundException(BodyApparatus_rd_Controller.entity, page);
		
		// map list
		List<BodyApparatusDTO> bodyApparatusDTOList = new ArrayList<BodyApparatusDTO>();
		for (BodyApparatus s: entity) {
			bodyApparatusDTOList.add(rdDozerMapper.map(s, BodyApparatusDTO.class));
		}
		
		// assembly dto
		PagedDTO<BodyApparatusDTO> dto = new PagedDTO<BodyApparatusDTO>();
		dto.setElements(bodyApparatusDTOList);
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
	public @ResponseBody CreateResponseDTO<BodyApparatusDTO> add(@RequestBody BodyApparatusDTO bodyapparatus, HttpServletRequest request) throws Exception {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		
		// map
		BodyApparatus entity = rdDozerMapper.map(bodyapparatus, BodyApparatus.class);
		
		// add technical field
		entity.setUserupdate(username);
		
		// save and map to out dto
		entity = repo.saveAndFlush(entity);
		bodyapparatus = rdDozerMapper.map(entity, BodyApparatusDTO.class);
		
		// create standard response
		CreateResponseDTO<BodyApparatusDTO> response = new CreateResponseDTO<BodyApparatusDTO>();
		response.setEntityName(BodyApparatus_rd_Controller.entity);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), BodyApparatus_rd_Controller.entity));
		List<BodyApparatusDTO> bodyApparatusDTOs = new ArrayList<BodyApparatusDTO>();
		bodyApparatusDTOs.add(bodyapparatus);
		response.setNewValue(bodyApparatusDTOs);
		
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
	public @ResponseBody UpdateResponseDTO<BodyApparatusDTO> update(@RequestBody BodyApparatusDTO bodyApparatus, HttpServletRequest request) throws Exception {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		
		// if an id is not present throw bad request
		if(bodyApparatus.getIdbodyapparatus()==null) throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.missing.id"), BodyApparatus_rd_Controller.entity));
		
		// find entity to update (oldValue)
		BodyApparatus oldValueEntity = repo.findOne(bodyApparatus.getIdbodyapparatus()); 
		// if an entity with given id is not found in DB throw record not found
		if (oldValueEntity==null) throw new RecordNotFoundException(BodyApparatus_rd_Controller.entity, bodyApparatus.getIdbodyapparatus().toString());
		// get created date
		Date created = oldValueEntity.getCreated();
		// map old value to a dto
		BodyApparatusDTO oldValueDTO = rdDozerMapper.map(oldValueEntity, BodyApparatusDTO.class);

		// begin update of oldValue
		rdDozerMapper.map(bodyApparatus, oldValueEntity);
		
		// add technical field
		oldValueEntity.setUserupdate(username);
		oldValueEntity.setUpdated(new Date());
		oldValueEntity.setCreated(created);
		
		// save and map to out dto
		BodyApparatus newValueEntity = repo.saveAndFlush(oldValueEntity);
		BodyApparatusDTO newValueDTO = rdDozerMapper.map(newValueEntity, BodyApparatusDTO.class);
				
		// create standard response
		UpdateResponseDTO<BodyApparatusDTO> response = new UpdateResponseDTO<BodyApparatusDTO>();
		response.setEntityName(BodyApparatus_rd_Controller.entity);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), BodyApparatus_rd_Controller.entity));
		// add new dtos values
		List<BodyApparatusDTO> newBodyApparatusDTOs = new ArrayList<BodyApparatusDTO>();
		newBodyApparatusDTOs.add(newValueDTO);
		response.setNewValue(newBodyApparatusDTOs);
		// add old dtos values
		List<BodyApparatusDTO> oldBodyApparatusDTOs = new ArrayList<BodyApparatusDTO>();
		oldBodyApparatusDTOs.add(oldValueDTO);
		response.setOldValue(oldBodyApparatusDTOs);
		
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
	public @ResponseBody String delete(@RequestBody BodyApparatusDTO bodyApparatus, HttpServletRequest request) {
		return MessageFormat.format(env.getProperty("echo.api.crud.notsupported"), RequestMethod.DELETE.toString(), BodyApparatus_rd_Controller.entity);
	}
}
