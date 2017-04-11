package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
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
import it.clevercom.echo.common.model.jpa.helper.SearchCriteria;
import it.clevercom.echo.common.model.jpa.helper.SpecificationQueryHelper;
import it.clevercom.echo.common.model.jpa.helper.SpecificationsBuilder;
import it.clevercom.echo.rd.model.dto.PagedDTO;
import it.clevercom.echo.rd.model.dto.WorkStatusDTO;
import it.clevercom.echo.rd.model.entity.WorkStatus;
import it.clevercom.echo.rd.repository.IWorkStatus_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/workstatus")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Work Status Controller
 * @author luca
 */

public class WorkStatus_rd_Controller {
	@Autowired
	private Environment env;
	
	@Autowired
	private IWorkStatus_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	private static String entity_name = "WorkStatus";
	private static String entity_id = "idworkstatus";

	/**
	 * Get work status by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody WorkStatusDTO get(@PathVariable Long id) throws Exception {
		WorkStatus entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		return rdDozerMapper.map(entity, WorkStatusDTO.class);
	}
	
	/**
	 * Get work status by criteria with pagination
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
	public @ResponseBody PagedDTO<WorkStatusDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="10", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue="idworkstatus", required=false) String field) throws Exception {
		
		// create paged request
		PageRequest request = null;
		
		if (sort.equalsIgnoreCase("asc")) {
			 request = new PageRequest(page-1, size, Direction.ASC, field);
		} else if (sort.equalsIgnoreCase("desc")) {
			request = new PageRequest(page-1, size, Direction.DESC, field);
		} else {
			throw new BadRequestException(env.getProperty("echo.api.exception.search.sort.wrongsortparam"));
		}
		
		// create predicate if criteria is not null
		Page<WorkStatus> rs = null;
		
		if (!criteria.equals("null")) {
	        SpecificationsBuilder<WorkStatus, SpecificationQueryHelper<WorkStatus>> builder = new SpecificationsBuilder<WorkStatus, SpecificationQueryHelper<WorkStatus>>();
	        Pattern pattern = Pattern.compile(SearchCriteria.pattern);
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
		
		if (entity.size() == 0) throw new PageNotFoundException(entity_name, page);
		
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
	 * Add work status
	 * @param workstatus
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody String add(@RequestBody WorkStatusDTO workstatus) throws Exception {
		return MessageFormat.format(env.getProperty("echo.api.crud.notsupported"), RequestMethod.POST.toString(), entity_name);
	}
	
	/**
	 * Update work status
	 * @param workstatus
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody String update(@RequestBody WorkStatusDTO workstatus) throws Exception {
		return MessageFormat.format(env.getProperty("echo.api.crud.notsupported"), RequestMethod.PUT.toString(), entity_name);
	}
	
	/**
	 * Delete work status
	 * @param workstatus
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody String delete(@RequestBody WorkStatusDTO workstatus) {
		return MessageFormat.format(env.getProperty("echo.api.crud.notsupported"), RequestMethod.DELETE.toString(), entity_name);
	}
}
