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
import it.clevercom.echo.rd.model.dto.ProvinceDTO;
import it.clevercom.echo.rd.model.dto.PagedDTO;
import it.clevercom.echo.rd.model.entity.Province;
import it.clevercom.echo.rd.model.jpa.helper.SearchCriteria;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationQueryHelper;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationsBuilder;
import it.clevercom.echo.rd.repository.IMunicipality_rd_Repository;
import it.clevercom.echo.rd.repository.IProvince_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/province")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

public class Province_rd_Controller {

	@Autowired
	private Environment env;
	
	@Autowired
	private IProvince_rd_Repository repo;
	
	@Autowired
	private IMunicipality_rd_Repository repo_m;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	private static String entity = "Province";
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws EchoException
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody ProvinceDTO get(@PathVariable Long id) throws Exception {
		Province entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(Province_rd_Controller.entity, id.toString());
		return rdDozerMapper.map(entity, ProvinceDTO.class);
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
	public @ResponseBody PagedDTO<ProvinceDTO> getByCriteria (	@RequestParam(defaultValue="null", required=false) String criteria, 
																@RequestParam(defaultValue="1", required=false) int page, 
																@RequestParam(defaultValue="500", required=false) int size, 
																@RequestParam(defaultValue="asc", required=false) String sort, 
																@RequestParam(defaultValue="idprovince", required=false) String field) throws Exception {
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
		Page<Province> rs = null;
		
		if (!criteria.equals("null")) {
	        SpecificationsBuilder<Province, SpecificationQueryHelper<Province>> builder = new SpecificationsBuilder<Province, SpecificationQueryHelper<Province>>();
	        Pattern pattern = Pattern.compile(SearchCriteria.pattern);
	        Matcher matcher = pattern.matcher(criteria + ",");
	        while (matcher.find()) {
	            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
	        }
	        Specification<Province> spec = builder.build();
	        
	        // obtain records
	        rs = repo.findAll(spec, request);
		} else {
			rs = repo.findAll(request);
		}
		
		int totalPages = rs.getTotalPages();
        long totalElements = rs.getTotalElements();
		List<Province> entity = rs.getContent();
		
		if (entity.size() == 0) throw new PageNotFoundException(Province_rd_Controller.entity, page);
		
		// map list
		List<ProvinceDTO> provinceDTOList = new ArrayList<ProvinceDTO>();
		for (Province s: entity) {
			provinceDTOList.add(rdDozerMapper.map(s, ProvinceDTO.class));
		}
		
		// assembly dto
		PagedDTO<ProvinceDTO> dto = new PagedDTO<ProvinceDTO>();
		dto.setElements(provinceDTOList);
		dto.setPageSize(size);
		dto.setCurrentPage(page);
		dto.setTotalPages(totalPages);
		dto.setTotalElements(totalElements);
		return dto;
	}
}
