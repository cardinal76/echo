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

import it.clevercom.echo.common.controller.EchoController;
import it.clevercom.echo.common.exception.model.BadRequestException;
import it.clevercom.echo.common.exception.model.PageNotFoundException;
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.jpa.CriteriaRequestProcessor;
import it.clevercom.echo.common.jpa.helper.SearchCriteria;
import it.clevercom.echo.common.jpa.helper.SpecificationQueryHelper;
import it.clevercom.echo.common.jpa.helper.SpecificationsBuilder;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.rd.component.Validator;
import it.clevercom.echo.rd.model.dto.MaritalStatusDTO;
import it.clevercom.echo.rd.model.dto.ModalityTypeDTO;
import it.clevercom.echo.rd.model.dto.ServiceDTO;
import it.clevercom.echo.rd.model.entity.Maritalstatus;
import it.clevercom.echo.rd.model.entity.ModalityType;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.rd.repository.IMaritalStatus_rd_Repository;
import it.clevercom.echo.rd.repository.IModalityType_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/modalitytype")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Modality Type Controller
 * @author luca
 */
public class ModalityType_rd_Controller extends EchoController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private IModalityType_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	private static String entity_name = "ModalityType";
	private static String entity_id = "idmodalitytype";
	
	/**
	 * Get modality type by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody ServiceDTO get(@PathVariable Long id) throws Exception {
		ModalityType entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		return rdDozerMapper.map(entity, ServiceDTO.class);
	}
	
	/**
	 * Get modality type list by criteria with pagination
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
	public @ResponseBody PagedDTO<ModalityTypeDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="20", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue="idmodalitytype", required=false) String field) throws Exception {
		
		// check enum string params
		validator.validateSort(sort);
		
		CriteriaRequestProcessor<IModalityType_rd_Repository, ModalityType, ModalityTypeDTO> rp = 
				new CriteriaRequestProcessor<IModalityType_rd_Repository, ModalityType, ModalityTypeDTO>(repo, 
						rdDozerMapper, 
						ModalityTypeDTO.class, 
						entity_name, 
						criteria, 
						sort, 
						field, 
						page, 
						size);
		
		return rp.process();
	}
}
