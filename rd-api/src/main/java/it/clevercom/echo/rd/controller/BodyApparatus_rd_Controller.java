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
import it.clevercom.echo.rd.model.dto.BodyApparatusDTO;
import it.clevercom.echo.rd.model.entity.BodyApparatus;
import it.clevercom.echo.rd.repository.IBodyApparatus_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/bodyapparatus")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Body Apparatus Controller
 * @author luca
 */

public class BodyApparatus_rd_Controller extends EchoController {

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
	
	@Autowired
	private Validator validator;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	private static String entity_name = "BodyApparatus";
	private static String entity_id = "idbodyapparatus";

	/**
	 * Get a body apparatus by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody BodyApparatusDTO get(@PathVariable Long id) throws Exception {
		BodyApparatus entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		return rdDozerMapper.map(entity, BodyApparatusDTO.class);
	}
	
	/**
	 * Get a body apparatus list by criteria with pagination
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
	public @ResponseBody PagedDTO<BodyApparatusDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="1000", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue="code", required=false) String field) throws Exception {
		
		// check enum string params
		validator.validateSort(sort);
		
		CriteriaRequestProcessor<IBodyApparatus_rd_Repository, BodyApparatus, BodyApparatusDTO> rp = 
				new CriteriaRequestProcessor<IBodyApparatus_rd_Repository, BodyApparatus, BodyApparatusDTO>(repo, 
						rdDozerMapper, 
						BodyApparatusDTO.class, 
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
	 * Add a body apparatus
	 * @param bodyapparatus
	 * @param request
	 * @return
	 * @throws Exception
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
		response.setEntityName(entity_name);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), entity_name));
		List<BodyApparatusDTO> bodyApparatusDTOs = new ArrayList<BodyApparatusDTO>();
		bodyApparatusDTOs.add(bodyapparatus);
		response.setNewValue(bodyApparatusDTOs);
		
		// return standard response
		return response;
	}
	
	/**
	 * Update a body apparatus
	 * @param bodyApparatus
	 * @param request
	 * @return
	 * @throws Exception
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
		if(bodyApparatus.getIdbodyapparatus()==null) throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.missing.id"), entity_name));
		
		// find entity to update (oldValue)
		BodyApparatus oldValueEntity = repo.findOne(bodyApparatus.getIdbodyapparatus()); 
		// if an entity with given id is not found in DB throw record not found
		if (oldValueEntity==null) throw new RecordNotFoundException(entity_name, entity_id, bodyApparatus.getIdbodyapparatus().toString());
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
		response.setEntityName(entity_name);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), entity_name));
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
	 * Delete a body apparatus 
	 * @param bodyApparatus
	 * @param request
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody String delete(@RequestBody BodyApparatusDTO bodyApparatus, HttpServletRequest request) {
		return MessageFormat.format(env.getProperty("echo.api.crud.notsupported"), RequestMethod.DELETE.toString(), entity_name);
	}
}
