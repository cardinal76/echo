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
import it.clevercom.echo.rd.model.dto.CodingActorDTO;
import it.clevercom.echo.rd.model.entity.CodingActor;
import it.clevercom.echo.rd.repository.ICodingActor_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/codingactor")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Coding Actor Controller
 * @author luca
 */

public class CodingActor_rd_Controller extends EchoController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ICodingActor_rd_Repository repo;
	
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
	private static String entity_name = "CodingActor";
	private static String entity_id = "idcodingactor";
	
	/**
	 * Get coding actor by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CodingActorDTO get(@PathVariable Long id) throws Exception {
		CodingActor entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		return rdDozerMapper.map(entity, CodingActorDTO.class);
	}
	
	/**
	 * Get coding actor list by criteria with pagination
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
	public @ResponseBody PagedDTO<CodingActorDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="1000", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue="idcodingactor", required=false) String field) throws Exception {
		
		// check enum string params
		validator.validateSort(sort);
		
		CriteriaRequestProcessor<ICodingActor_rd_Repository, CodingActor, CodingActorDTO> rp = 
				new CriteriaRequestProcessor<ICodingActor_rd_Repository, CodingActor, CodingActorDTO>(repo, 
						rdDozerMapper, 
						CodingActorDTO.class, 
						entity_name, 
						criteria, 
						sort, 
						field, 
						page, 
						size);
		
		return rp.process();
	}
	
	/**
	 * Add coding actor
	 * @param codingactor
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<CodingActorDTO> add(@RequestBody CodingActorDTO codingactor, HttpServletRequest request) throws Exception {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		
		// map
		CodingActor entity = rdDozerMapper.map(codingactor, CodingActor.class);
		
		// add technical field
		entity.setUserupdate(username);
		
		// save and map to out dto
		entity = repo.saveAndFlush(entity);
		codingactor = rdDozerMapper.map(entity, CodingActorDTO.class);
		
		// create standard response
		CreateResponseDTO<CodingActorDTO> response = new CreateResponseDTO<CodingActorDTO>();
		response.setEntityName(entity_name);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), entity_name));
		List<CodingActorDTO> codingactorDTOs = new ArrayList<CodingActorDTO>();
		codingactorDTOs.add(codingactor);
		response.setNewValue(codingactorDTOs);
		
		// return standard response
		return response;
	}
	
	/**
	 * Update coding actor
	 * @param codingactor
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<CodingActorDTO> update(@RequestBody CodingActorDTO codingactor, HttpServletRequest request) throws Exception {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		
		// if an id is not present throw bad request
		if(codingactor.getIdcodingactor()==null) throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.missing.id"), entity_name));
		
		// find entity to update (oldValue)
		CodingActor oldValueEntity = repo.findOne(codingactor.getIdcodingactor()); 
		// if an entity with given id is not found in DB throw record not found
		if (oldValueEntity==null) throw new RecordNotFoundException(entity_name, entity_id, codingactor.getIdcodingactor().toString());
		// get created date
		Date created = oldValueEntity.getCreated();
		// map old value to a dto
		CodingActorDTO oldValueDTO = rdDozerMapper.map(oldValueEntity, CodingActorDTO.class);

		// begin update of oldValue
		rdDozerMapper.map(codingactor, oldValueEntity);
		
		// add technical field
		oldValueEntity.setUserupdate(username);
		oldValueEntity.setUpdated(new Date());
		oldValueEntity.setCreated(created);
		
		// save and map to out dto
		CodingActor newValueEntity = repo.saveAndFlush(oldValueEntity);
		CodingActorDTO newValueDTO = rdDozerMapper.map(newValueEntity, CodingActorDTO.class);
				
		// create standard response
		UpdateResponseDTO<CodingActorDTO> response = new UpdateResponseDTO<CodingActorDTO>();
		response.setEntityName(entity_name);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), entity_name));
		// add new dtos values
		List<CodingActorDTO> newCodingActorDTOs = new ArrayList<CodingActorDTO>();
		newCodingActorDTOs.add(newValueDTO);
		response.setNewValue(newCodingActorDTOs);
		// add old dtos values
		List<CodingActorDTO> oldCodingActorDTOs = new ArrayList<CodingActorDTO>();
		oldCodingActorDTOs.add(oldValueDTO);
		response.setOldValue(oldCodingActorDTOs);
		
		// return response
		return response;
	}
	
	/**
	 * Delete coding actor
	 * @param codingactor
	 * @param request
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody String delete(@RequestBody CodingActorDTO codingactor, HttpServletRequest request) {
		return MessageFormat.format(env.getProperty("echo.api.crud.notsupported"), RequestMethod.DELETE.toString(), entity_name);
	}
}
