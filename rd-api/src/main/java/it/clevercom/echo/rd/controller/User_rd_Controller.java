package it.clevercom.echo.rd.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import it.clevercom.echo.rd.model.dto.PagedDTO;
import it.clevercom.echo.rd.model.dto.UserDTO;
import it.clevercom.echo.rd.model.entity.User;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationQueryHelper;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationsBuilder;
import it.clevercom.echo.rd.repository.IUser_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/assets/user")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

public class User_rd_Controller {
	@Autowired
	private Environment env;
	
	@Autowired
	private IUser_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper dozerMapper;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	private static String entity = "User";
	
	/**
	 * @param username
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UserDTO get(@PathVariable Long id) throws Exception {
		User entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(User_rd_Controller.entity, id.toString());
		return dozerMapper.map(entity, UserDTO.class);
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
	public @ResponseBody PagedDTO<UserDTO> getByCriteria (	@RequestParam(defaultValue="null", required=false) String criteria, 
															@RequestParam int page, 
															@RequestParam int size, 
															@RequestParam(defaultValue="asc", required=false) String sort, 
															@RequestParam(defaultValue="iduser", required=false) String field) throws Exception {
		// create paged request
		PageRequest request = null;
		
		if (sort.equals("asc")) {
			 request = new PageRequest(page-1, size, Direction.ASC, field);
		} else if (sort.equals("desc")) {
			request = new PageRequest(page-1, size, Direction.DESC, field);
		} else {
			throw new BadRequestException(env.getProperty("echo.api.crud.search.sort.wrongsortparam"));
		}
		
		// create predicate if criteria is not null
		Page<User> rs = null;
		
		if (!criteria.equals("null")) {
	        SpecificationsBuilder<User, SpecificationQueryHelper<User>> builder = new SpecificationsBuilder<User, SpecificationQueryHelper<User>>();
	        Pattern pattern = Pattern.compile("(\\w+)(:|<|>)(\\w+)");
	        Matcher matcher = pattern.matcher(criteria + ",");
	        while (matcher.find()) {
	            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
	        }
	        Specification<User> spec = builder.build();
	        
	        // obtain records
	        rs = repo.findAll(spec, request);
		} else {
			rs = repo.findAll(request);
		}
		
		int totalPages = rs.getTotalPages();
        long totalElements = rs.getTotalElements();
		List<User> entity = rs.getContent();
		
		if (entity.size() == 0) throw new PageNotFoundException(User_rd_Controller.entity, page);
		
		// map list
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		for (User s: entity) {
			userDTOList.add(dozerMapper.map(s, UserDTO.class));
		}
		
		// assembly dto
		PagedDTO<UserDTO> dto = new PagedDTO<UserDTO>();
		dto.setElements(userDTOList);
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
	public @ResponseBody CreateResponseDTO add(@RequestBody UserDTO user) throws Exception {
		if (user == null) throw new BadRequestException("Impossible to store a null");
		User entity = dozerMapper.map(user, User.class);
		entity.setActive(true);
		entity.setUserupdate("pippobaudo");
		entity.setUpdated(new Timestamp(System.currentTimeMillis()));
		entity.setCreated(new Timestamp(System.currentTimeMillis()));
		User saved = repo.saveAndFlush(entity);
		
		// create standard response
		CreateResponseDTO response = new CreateResponseDTO();
		HashMap<String,String> ids = new HashMap<String,String>();
		ids.put("iduser", String.valueOf(saved.getIduser()));
		response.setIds(ids);
		response.setEntityName(User_rd_Controller.entity);
		response.setMessage(null);
		response.setNewValue(null);
		response.setStatusCode("0");
		
		// return standard response
		return response;
	}
}
