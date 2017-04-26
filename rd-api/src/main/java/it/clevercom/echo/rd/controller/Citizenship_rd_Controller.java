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
import it.clevercom.echo.rd.model.dto.CitizenshipDTO;
import it.clevercom.echo.rd.model.entity.Citizenship;
import it.clevercom.echo.rd.repository.ICitizenship_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/citizenship")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Citizenship Controller
 * @author luca
 */

public class Citizenship_rd_Controller extends EchoController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ICitizenship_rd_Repository repo;
	
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
	private static String entity_name = "Citizenship";
	private static String entity_id = "idcitizenship";
	
	/**
	 * Get Citizenship by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CitizenshipDTO get(@PathVariable Long id) throws Exception {
		Citizenship entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		return rdDozerMapper.map(entity, CitizenshipDTO.class);
	}
	
	/**
	 * Get Citizenship by criteria with pagination
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
	public @ResponseBody PagedDTO<CitizenshipDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="20", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue="idcitizenship", required=false) String field) throws Exception {
		
		// check enum string params
		validator.validateSort(sort);
		
		CriteriaRequestProcessor<ICitizenship_rd_Repository, Citizenship, CitizenshipDTO> rp = 
				new CriteriaRequestProcessor<ICitizenship_rd_Repository, Citizenship, CitizenshipDTO>(repo, 
						rdDozerMapper, 
						CitizenshipDTO.class, 
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
	 * Add citizenship
	 * @param citizenship
	 * @param request
	 * @return
	 * @throws Exception
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
		Citizenship entity = rdDozerMapper.map(citizenship, Citizenship.class);
		
		// add technical field
		entity.setUserupdate(username);
		
		// save and map to out dto
		entity = repo.saveAndFlush(entity);
		citizenship = rdDozerMapper.map(entity, CitizenshipDTO.class);
		
		// create standard response
		CreateResponseDTO<CitizenshipDTO> response = new CreateResponseDTO<CitizenshipDTO>();
		response.setEntityName(entity_name);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), entity_name));
		List<CitizenshipDTO> citizenshipDTOs = new ArrayList<CitizenshipDTO>();
		citizenshipDTOs.add(citizenship);
		response.setNewValue(citizenshipDTOs);
		
		// return standard response
		return response;
	}
	
	/**
	 * Update citizenship
	 * @param citizenship
	 * @param request
	 * @return
	 * @throws Exception
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
		if(citizenship.getIdcitizenship()==null) throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.missing.id"), entity_name));
		
		// find entity to update (oldValue)
		Citizenship oldValueEntity = repo.findOne(citizenship.getIdcitizenship()); 
		// if an entity with given id is not found in DB throw record not found
		if (oldValueEntity==null) throw new RecordNotFoundException(entity_name, entity_id, citizenship.getIdcitizenship().toString());
		// get created date
		Date created = oldValueEntity.getCreated();
		// map old value to a dto
		CitizenshipDTO oldValueDTO = rdDozerMapper.map(oldValueEntity, CitizenshipDTO.class);

		// begin update of oldValue
		rdDozerMapper.map(citizenship, oldValueEntity);
		
		// add technical field
		oldValueEntity.setUserupdate(username);
		oldValueEntity.setUpdated(new Date());
		oldValueEntity.setCreated(created);
		
		// save and map to out dto
		Citizenship newValueEntity = repo.saveAndFlush(oldValueEntity);
		CitizenshipDTO newValueDTO = rdDozerMapper.map(newValueEntity, CitizenshipDTO.class);
				
		// create standard response
		UpdateResponseDTO<CitizenshipDTO> response = new UpdateResponseDTO<CitizenshipDTO>();
		response.setEntityName(entity_name);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), entity_name));
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
	 * Delete citizenship
	 * @param citizenship
	 * @param request
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody String delete(@RequestBody CitizenshipDTO citizenship, HttpServletRequest request) {
		return MessageFormat.format(env.getProperty("echo.api.crud.notsupported"), RequestMethod.DELETE.toString(), entity_name);
	}
}
