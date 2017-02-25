package it.clevercom.echo.rd.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import it.clevercom.echo.rd.model.entity.Service;
import it.clevercom.echo.rd.model.jpa.helper.SearchCriteria;
import it.clevercom.echo.rd.model.jpa.helper.ServiceSpecificationQueryHelper;
import it.clevercom.echo.rd.repository.IService_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/service")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

public class Service_rd_Controller {
	@Autowired
	private Environment env;
	
	@Autowired
	private IService_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper dozerMapper;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	private static String entity = "Service";
	private static String ca_rel_entity = "CodingActor";
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws EchoException
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody ServiceDTO get(@RequestParam Long id) throws Exception {
		Service entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(Service_rd_Controller.entity, id.toString());
		return dozerMapper.map(entity, ServiceDTO.class);
	}
	
	/**
	 * @param page
	 * @param size
	 * @param sort
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="page", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<ServiceDTO> getList(	@RequestParam int page, 
													@RequestParam int size, 
													@RequestParam(defaultValue="asc", required=false) String sort, 
													@RequestParam(defaultValue="idservice", required=false) String param) throws Exception {
		// create paged request
		PageRequest request = null;
		if (sort.equals("asc")) {
			 request = new PageRequest(page, size, Direction.ASC, param);
		} else if (sort.equals("desc")) {
			request = new PageRequest(page, size, Direction.DESC, param);
		} else {
			throw new BadRequestException("");
		}
		
		// obtain records
        Page<Service> rs = repo.findAll(request);
        int totalPages = rs.getTotalPages();
        List<Service> entity = rs.getContent(); 
        		 
		if (entity.size() == 0) throw new PageNotFoundException(Service_rd_Controller.entity, page);
		
		// map list
		List<ServiceDTO> serviceDTO = new ArrayList<ServiceDTO>();
		for (Service s: entity) {
			serviceDTO.add(dozerMapper.map(s, ServiceDTO.class));
		}
		
		// assembly dto
		PagedDTO<ServiceDTO> dto = new PagedDTO<ServiceDTO>();
		dto.setElements(serviceDTO);
		dto.setPageSize(size);
		dto.setCurrentPage(page);
		dto.setTotalPages(totalPages);
		return dto;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws EchoException
	 */
	@Transactional("rdTm")
	@RequestMapping(value="criteria", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<ServiceDTO> getList(	@RequestParam String description, 
													@RequestParam int page, 
													@RequestParam int size, 
													@RequestParam(defaultValue="asc", required=false) String sort, 
													@RequestParam(defaultValue="idservice", required=false) String param) throws Exception {
		// create paged request
		PageRequest request = null;
		
		if (sort.equals("asc")) {
			 request = new PageRequest(page, size, Direction.ASC, param);
		} else if (sort.equals("desc")) {
			request = new PageRequest(page, size, Direction.DESC, param);
		} else {
			throw new BadRequestException("");
		}
		
		// obtain records
		ServiceSpecificationQueryHelper spec = new ServiceSpecificationQueryHelper(new SearchCriteria("description", ":", description));
		Page<Service> rs = repo.findAll(spec, request);
        int totalPages = rs.getTotalPages();
		List<Service> entity = rs.getContent();
		
		if (entity.size() == 0) throw new PageNotFoundException(Service_rd_Controller.entity, page);
		
		// map list
		List<ServiceDTO> serviceDTO = new ArrayList<ServiceDTO>();
		for (Service s: entity) {
			serviceDTO.add(dozerMapper.map(s, ServiceDTO.class));
		}
		
		// assembly dto
		PagedDTO<ServiceDTO> dto = new PagedDTO<ServiceDTO>();
		dto.setElements(serviceDTO);
		dto.setPageSize(size);
		dto.setCurrentPage(page);
		dto.setTotalPages(totalPages);
		return dto;
	}
	
	/**
	 * 
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	public @ResponseBody CreateResponseDTO add() {
		return new CreateResponseDTO();
	}
	
	/**
	 * 
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	public @ResponseBody UpdateResponseDTO update() {
		return new UpdateResponseDTO();
	}
	
	/**
	 * 
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	public @ResponseBody String delete() {
		return "patient";
	}
}
