package it.clevercom.echo.rd.controller;

import java.sql.Timestamp;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import it.clevercom.echo.rd.component.Validator;
import it.clevercom.echo.rd.model.dto.UserDTO;
import it.clevercom.echo.rd.model.entity.User;
import it.clevercom.echo.rd.repository.IUser_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/assets/user")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * User controller
 * @author luca
 */

public class User_rd_Controller extends EchoController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private IUser_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	private static String entity_name = "User";
	private static String entity_id = "iduser";

	
	/**
	 * Get user by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{username}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UserDTO get(@PathVariable String username) throws Exception {
		User entity = repo.findOne(username);
		if (entity == null) throw new RecordNotFoundException(entity_name, entity_id, username);
		return rdDozerMapper.map(entity, UserDTO.class);
	}
	
	/**
	 * Get user by criteria with pagination
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
	public @ResponseBody PagedDTO<UserDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="15", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue="username", required=false) String field) throws Exception {
		
		// check enum string params
		validator.validateSort(sort);
		
		CriteriaRequestProcessor<IUser_rd_Repository, User, UserDTO> rp = 
				new CriteriaRequestProcessor<IUser_rd_Repository, User, UserDTO>(repo, 
						rdDozerMapper, 
						UserDTO.class, 
						entity_name, 
						criteria, 
						sort, 
						field, 
						page, 
						size);
		
		// process data request
		return rp.process();
	}
	
	/**
	 * Add user 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<UserDTO> add(@RequestBody UserDTO user) throws Exception {
		if (user == null) throw new BadRequestException("Impossible to store a null");
		User entity = rdDozerMapper.map(user, User.class);
		entity.setActive(true);
		entity.setUserupdate("pippobaudo");
		entity.setUpdated(new Timestamp(System.currentTimeMillis()));
		entity.setCreated(new Timestamp(System.currentTimeMillis()));
		User saved = repo.saveAndFlush(entity);
		
		// create standard response
		CreateResponseDTO<UserDTO> response = new CreateResponseDTO<UserDTO>();
		HashMap<String,String> ids = new HashMap<String,String>();
		ids.put("iduser", String.valueOf(saved.getUsername()));
		response.setEntityName(entity_name);
		response.setMessage(null);
		response.setNewValue(null);
		
		// return standard response
		return response;
	}
}
