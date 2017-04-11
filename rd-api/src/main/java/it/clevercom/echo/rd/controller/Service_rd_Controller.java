package it.clevercom.echo.rd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
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
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.rd.model.dto.PagedDTO;
import it.clevercom.echo.rd.model.dto.ServiceDTO;
import it.clevercom.echo.rd.model.entity.ModalityType;
import it.clevercom.echo.rd.model.entity.Service;
import it.clevercom.echo.common.model.jpa.helper.SearchCriteria;
import it.clevercom.echo.common.model.jpa.helper.SpecificationQueryHelper;
import it.clevercom.echo.common.model.jpa.helper.SpecificationsBuilder;
import it.clevercom.echo.rd.repository.IService_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/service")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Service controller
 * @author luca
 */

public class Service_rd_Controller {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private IService_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	private static String entity_name = "Service";
	private static String entity_id = "idservice";
	
	/**
	 * Get service by id
	 * @param id
	 * @return
	 * @throws EchoException
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody ServiceDTO get(@PathVariable Long id) throws Exception {
		Service entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		return rdDozerMapper.map(entity, ServiceDTO.class);
	}
	
	/**
	 * Get service list by criteria with pagination
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
	public @ResponseBody PagedDTO<ServiceDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria,
			@RequestParam(defaultValue = "*", required = false) String modalitytype,
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="1000", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue="idservice", required=false) String field) throws Exception {

		// flag which indicates where to use specification
		boolean useSpecification = false;
		
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
		Page<Service> rs = null;
		Specification<Service> spec = null;
		
		if (!criteria.equals("null")) {
	        SpecificationsBuilder<Service, SpecificationQueryHelper<Service>> builder = new SpecificationsBuilder<Service, SpecificationQueryHelper<Service>>();
	        Pattern pattern = Pattern.compile(SearchCriteria.pattern);
	        Matcher matcher = pattern.matcher(criteria + ",");
	        while (matcher.find()) {
	            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
	        }
	        spec = builder.build();
	        useSpecification = true;
		}
		
		// check modalitytype and add it to specification
		if (!modalitytype.equals("*")) {
			Specification<Service> smt = new Specification<Service>() {
				@Override
				public Predicate toPredicate(Root<Service> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.equal(root.<ModalityType>get("modalityType").<Long>get("idmodalitytype"), modalitytype);
				}
			};
			
			// add to specification list
			spec =  Specifications.where(spec).and(smt);
			useSpecification = true;
		}
		
		if (useSpecification) {
			// obtain records
        	rs = repo.findAll(spec, request);
		} else {
			rs = repo.findAll(request);
		}
		
		int totalPages = rs.getTotalPages();
        long totalElements = rs.getTotalElements();
		List<Service> entity = rs.getContent();
		
		if (entity.size() == 0) throw new PageNotFoundException(Service_rd_Controller.entity_name, page);
		
		// map list
		List<ServiceDTO> serviceDTOList = new ArrayList<ServiceDTO>();
		for (Service s: entity) {
			serviceDTOList.add(rdDozerMapper.map(s, ServiceDTO.class));
		}
		
		// assembly dto
		PagedDTO<ServiceDTO> dto = new PagedDTO<ServiceDTO>();
		dto.setElements(serviceDTOList);
		dto.setPageSize(size);
		dto.setCurrentPage(page);
		dto.setTotalPages(totalPages);
		dto.setTotalElements(totalElements);
		return dto;
	}
	
	/**
	 * Add service 
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<ServiceDTO> add() {
		return new CreateResponseDTO<ServiceDTO>();
	}
	
	/**
	 * Update service
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<ServiceDTO> update() {
		return new UpdateResponseDTO<ServiceDTO>();
	}
	
	/**
	 * Delete service
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody String delete() {
		return "patient";
	}
}
