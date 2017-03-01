package it.clevercom.echo.rd.controller;

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
import it.clevercom.echo.rd.model.dto.ModalityTypeDTO;
import it.clevercom.echo.rd.model.dto.OrganizationUnitDTO;
import it.clevercom.echo.rd.model.dto.PagedDTO;
import it.clevercom.echo.rd.model.dto.ServiceDTO;
import it.clevercom.echo.rd.model.entity.ModalityType;
import it.clevercom.echo.rd.model.entity.OrganizationUnit;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationQueryHelper;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationsBuilder;
import it.clevercom.echo.rd.repository.IModalityType_rd_Repository;
import it.clevercom.echo.rd.repository.IOrganizationUnit_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/assets/organizationunit")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

public class OrganizationUnit_rd_Controller {

	@Autowired
	private Environment env;
	
	@Autowired
	private IOrganizationUnit_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper dozerMapper;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	private static String entity = "OrganizationUnit";
	
	/**
	 * @param id
	 * @return
	 * @throws EchoException
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody OrganizationUnitDTO get(@PathVariable Long id) throws Exception {
		OrganizationUnit entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(OrganizationUnit_rd_Controller.entity, id.toString());
		return dozerMapper.map(entity, OrganizationUnitDTO.class);
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
	public @ResponseBody PagedDTO<OrganizationUnitDTO> getByCriteria (	@RequestParam(defaultValue="null", required=false) String criteria, 
																		@RequestParam int page, 
																		@RequestParam int size, 
																		@RequestParam(defaultValue="asc", required=false) String sort, 
																		@RequestParam(defaultValue="idorganizationunit", required=false) String field) throws Exception {
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
		Page<OrganizationUnit> rs = null;
		
		if (!criteria.equals("null")) {
	        SpecificationsBuilder<OrganizationUnit, SpecificationQueryHelper<OrganizationUnit>> builder = new SpecificationsBuilder<OrganizationUnit, SpecificationQueryHelper<OrganizationUnit>>();
	        Pattern pattern = Pattern.compile("(\\w+)(:|<|>)(\\w+)");
	        Matcher matcher = pattern.matcher(criteria + ",");
	        while (matcher.find()) {
	            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
	        }
	        Specification<OrganizationUnit> spec = builder.build();
	        
	        // obtain records
	        rs = repo.findAll(spec, request);
		} else {
			rs = repo.findAll(request);
		}
		
		int totalPages = rs.getTotalPages();
        long totalElements = rs.getTotalElements();
		List<OrganizationUnit> entity = rs.getContent();
		
		if (entity.size() == 0) throw new PageNotFoundException(OrganizationUnit_rd_Controller.entity, page);
		
		// map list
		List<OrganizationUnitDTO> modalityTypeDTO = new ArrayList<OrganizationUnitDTO>();
		for (OrganizationUnit s : entity) {
			modalityTypeDTO.add(dozerMapper.map(s, OrganizationUnitDTO.class));
		}
		
		// assembly dto
		PagedDTO<OrganizationUnitDTO> dto = new PagedDTO<OrganizationUnitDTO>();
		dto.setElements(modalityTypeDTO);
		dto.setPageSize(size);
		dto.setCurrentPage(page);
		dto.setTotalPages(totalPages);
		dto.setTotalElements(totalElements);
		return dto;
	}
}
