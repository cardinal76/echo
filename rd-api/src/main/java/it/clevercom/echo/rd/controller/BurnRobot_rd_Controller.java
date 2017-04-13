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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import it.clevercom.echo.common.jpa.factory.CriteriaSpecificationFactory;
import it.clevercom.echo.common.jpa.factory.PageRequestFactory;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.common.model.factory.PagedDTOFactory;
import it.clevercom.echo.common.util.JwtTokenUtils;
import it.clevercom.echo.rd.component.Validator;
import it.clevercom.echo.rd.model.dto.BurnRobotDTO;
import it.clevercom.echo.rd.model.entity.BurnRobot;
import it.clevercom.echo.rd.repository.IBurnRobot_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/assets/burnrobot")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Burn Robot Controller
 * @author luca
 */

public class BurnRobot_rd_Controller extends EchoController {

	@Autowired
	private Environment env;
	
	@Autowired
	private IBurnRobot_rd_Repository repo;
	
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
	private static String entity_name = "BurnRobot";
	private static String entity_id = "BurnRobot";
	
	/**
	 * Get a burn robot by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody BurnRobotDTO get(@PathVariable Long id) throws Exception {
		BurnRobot entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		return rdDozerMapper.map(entity, BurnRobotDTO.class);
	}
	
	/**
	 * Get a burn robot list by criteria with pagination
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
	public @ResponseBody PagedDTO<BurnRobotDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="1000", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue="idburnrobot", required=false) String field) throws Exception {
		
		// check enum string params
		validator.validateSort(sort);
		
		// create paged request
		PageRequest request = PageRequestFactory.getPageRequest(sort, field, page, size);
		
		// create and set specification with criteria param
		Specification<BurnRobot> spec = CriteriaSpecificationFactory.getCriteriaSpecification(criteria);
		
		// find with specification and pagination
		Page<BurnRobot> rs = repo.findAll(spec, request);
		
		// get content
		List<BurnRobot> entity = rs.getContent();
		
		// throw exception if no content
		if (entity.size() == 0) 
			throw new PageNotFoundException(entity_name, page);
		
		// create list
		List<BurnRobotDTO> burnRobotDTOList = new ArrayList<BurnRobotDTO>();
		for (BurnRobot s: entity) {
			burnRobotDTOList.add(rdDozerMapper.map(s, BurnRobotDTO.class));
		}
		
		// assembly dto
		return PagedDTOFactory.getPagedDTO(burnRobotDTOList, size, page, rs.getTotalPages(), rs.getTotalElements());
	}
	
	/**
	 * Add a burn robot
	 * @param burnrobot
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<BurnRobotDTO> add(@RequestBody BurnRobotDTO burnrobot, HttpServletRequest request) throws Exception {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		
		// map
		BurnRobot entity = rdDozerMapper.map(burnrobot, BurnRobot.class);
		
		// add technical field
		entity.setUserupdate(username);
		
		// save and map to out dto
		entity = repo.saveAndFlush(entity);
		burnrobot = rdDozerMapper.map(entity, BurnRobotDTO.class);
		
		// create standard response
		CreateResponseDTO<BurnRobotDTO> response = new CreateResponseDTO<BurnRobotDTO>();
		response.setEntityName(entity_name);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), entity_name));
		List<BurnRobotDTO> burnRobotDTOs = new ArrayList<BurnRobotDTO>();
		burnRobotDTOs.add(burnrobot);
		response.setNewValue(burnRobotDTOs);
		
		// return standard response
		return response;
	}
	
	/**
	 * Update a burn robot
	 * @param burnRobot
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<BurnRobotDTO> update(@RequestBody BurnRobotDTO burnRobot, HttpServletRequest request) throws Exception {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		
		// if an id is not present throw bad request
		if(burnRobot.getIdburnrobot()==null) throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.missing.id"), entity_name));
		
		// find entity to update (oldValue)
		BurnRobot oldValueEntity = repo.findOne(burnRobot.getIdburnrobot()); 
		// if an entity with given id is not found in DB throw record not found
		if (oldValueEntity==null) throw new RecordNotFoundException(entity_name, entity_id, burnRobot.getIdburnrobot().toString());
		// get created date
		Date created = oldValueEntity.getCreated();
		// map old value to a dto
		BurnRobotDTO oldValueDTO = rdDozerMapper.map(oldValueEntity, BurnRobotDTO.class);

		// begin update of oldValue
		rdDozerMapper.map(burnRobot, oldValueEntity);
		
		// add technical field
		oldValueEntity.setUserupdate(username);
		oldValueEntity.setUpdated(new Date());
		oldValueEntity.setCreated(created);
		
		// save and map to out dto
		BurnRobot newValueEntity = repo.saveAndFlush(oldValueEntity);
		BurnRobotDTO newValueDTO = rdDozerMapper.map(newValueEntity, BurnRobotDTO.class);
				
		// create standard response
		UpdateResponseDTO<BurnRobotDTO> response = new UpdateResponseDTO<BurnRobotDTO>();
		response.setEntityName(entity_name);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), entity_name));
		// add new dtos values
		List<BurnRobotDTO> newBurnRobotDTOs = new ArrayList<BurnRobotDTO>();
		newBurnRobotDTOs.add(newValueDTO);
		response.setNewValue(newBurnRobotDTOs);
		// add old dtos values
		List<BurnRobotDTO> oldBurnRobotDTOs = new ArrayList<BurnRobotDTO>();
		oldBurnRobotDTOs.add(oldValueDTO);
		response.setOldValue(oldBurnRobotDTOs);
		
		// return response
		return response;
	}
	
	/**
	 * Delete a burn robot
	 * @param burnRobot
	 * @param request
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody String delete(@RequestBody BurnRobotDTO burnRobot, HttpServletRequest request) {
		return MessageFormat.format(env.getProperty("echo.api.crud.notsupported"), RequestMethod.DELETE.toString(), entity_name);
	}
}
