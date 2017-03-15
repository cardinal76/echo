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
import it.clevercom.echo.rd.model.dto.CitizenshipDTO;
import it.clevercom.echo.rd.model.dto.PagedDTO;
import it.clevercom.echo.rd.model.entity.Citizenship;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationQueryHelper;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationsBuilder;
import it.clevercom.echo.rd.repository.ICitizenship_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/citizenship")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

public class Citizenship_rd_Controller {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ICitizenship_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper dozerMapper;
	
	@Value("${jwt.token.header}")
	private String tokenHeader;
	
	@Autowired
	private JwtTokenUtils tokenUtils;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	private static String entity = "Citizenship";
	
	/**
	 * @param username
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CitizenshipDTO get(@PathVariable Long id) throws Exception {
		Citizenship entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(Citizenship_rd_Controller.entity, id.toString());
		return dozerMapper.map(entity, CitizenshipDTO.class);
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
	public @ResponseBody PagedDTO<CitizenshipDTO> getByCriteria (	@RequestParam(defaultValue="null", required=false) String criteria, 
																	@RequestParam(defaultValue="1", required=false) int page, 
																	@RequestParam(defaultValue="20", required=false) int size, 
																	@RequestParam(defaultValue="asc", required=false) String sort, 
																	@RequestParam(defaultValue="idcitizenship", required=false) String field) throws Exception {
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
		Page<Citizenship> rs = null;
		
		if (!criteria.equals("null")) {
	        SpecificationsBuilder<Citizenship, SpecificationQueryHelper<Citizenship>> builder = new SpecificationsBuilder<Citizenship, SpecificationQueryHelper<Citizenship>>();
	        Pattern pattern = Pattern.compile("(\\w+)(:|<|>)(\\w+)");
	        Matcher matcher = pattern.matcher(criteria + ",");
	        while (matcher.find()) {
	            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
	        }
	        Specification<Citizenship> spec = builder.build();
	        
	        // obtain records
	        rs = repo.findAll(spec, request);
		} else {
			rs = repo.findAll(request);
		}
		
		int totalPages = rs.getTotalPages();
        long totalElements = rs.getTotalElements();
		List<Citizenship> entity = rs.getContent();
		
		if (entity.size() == 0) throw new PageNotFoundException(Citizenship_rd_Controller.entity, page);
		
		// map list
		List<CitizenshipDTO> citizenshipDTOList = new ArrayList<CitizenshipDTO>();
		for (Citizenship s: entity) {
			citizenshipDTOList.add(dozerMapper.map(s, CitizenshipDTO.class));
		}
		
		// assembly dto
		PagedDTO<CitizenshipDTO> dto = new PagedDTO<CitizenshipDTO>();
		dto.setElements(citizenshipDTOList);
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
	public @ResponseBody CreateResponseDTO<CitizenshipDTO> add(@RequestBody CitizenshipDTO citizenship, HttpServletRequest request) throws Exception {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		
		// map
		Citizenship entity = dozerMapper.map(citizenship, Citizenship.class);
		
		// add technical field
		entity.setUserupdate(username);
		
		// save and map to out dto
		entity = repo.saveAndFlush(entity);
		citizenship = dozerMapper.map(entity, CitizenshipDTO.class);
		
		// create standard response
		CreateResponseDTO<CitizenshipDTO> response = new CreateResponseDTO<CitizenshipDTO>();
		response.setEntityName(Citizenship_rd_Controller.entity);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), Citizenship_rd_Controller.entity));
		List<CitizenshipDTO> citizenshipDTOs = new ArrayList<CitizenshipDTO>();
		citizenshipDTOs.add(citizenship);
		response.setNewValue(citizenshipDTOs);
		
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
	public @ResponseBody UpdateResponseDTO<CitizenshipDTO> update(@RequestBody CitizenshipDTO citizenship, HttpServletRequest request) throws Exception {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		
		// if an id is not present throw bad request
		if(citizenship.getIdcitizenship()==null) throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.missing.id"), Citizenship_rd_Controller.entity));
		
		// find entity to update (oldValue)
		Citizenship oldValueEntity = repo.findOne(citizenship.getIdcitizenship()); 
		// if an entity with given id is not found in DB throw record not found
		if (oldValueEntity==null) throw new RecordNotFoundException(Citizenship_rd_Controller.entity, citizenship.getIdcitizenship().toString());
		// map old value to a dto
		CitizenshipDTO oldValueDTO = dozerMapper.map(oldValueEntity, CitizenshipDTO.class);

		// begin update of oldValue
		dozerMapper.map(citizenship, oldValueEntity);
		
		// add technical field
		oldValueEntity.setUserupdate(username);
		oldValueEntity.setUpdated(new Date());
		
		// save and map to out dto
		Citizenship newValueEntity = repo.saveAndFlush(oldValueEntity);
		CitizenshipDTO newValueDTO = dozerMapper.map(newValueEntity, CitizenshipDTO.class);
				
		// create standard response
		UpdateResponseDTO<CitizenshipDTO> response = new UpdateResponseDTO<CitizenshipDTO>();
		response.setEntityName(Citizenship_rd_Controller.entity);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), Citizenship_rd_Controller.entity));
		// add new dtos values
		List<CitizenshipDTO> newCitizenshipDTOs = new ArrayList<CitizenshipDTO>();
		newCitizenshipDTOs.add(newValueDTO);
		response.setNewValue(newCitizenshipDTOs);
		// add old dtos values
		List<CitizenshipDTO> oldCitizenshipDTOs = new ArrayList<CitizenshipDTO>();
		oldCitizenshipDTOs.add(oldValueDTO);
		response.setOldValue(oldCitizenshipDTOs);
		
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
	public @ResponseBody String delete(@RequestBody CitizenshipDTO citizenship, HttpServletRequest request) {
		return MessageFormat.format(env.getProperty("echo.api.crud.notsupported"), RequestMethod.DELETE.toString(), Citizenship_rd_Controller.entity);
	}
}
