package it.clevercom.echo.rd.controller;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.jpa.CriteriaRequestProcessor;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.rd.component.Validator;
import it.clevercom.echo.rd.model.dto.ProvinceDTO;
import it.clevercom.echo.rd.model.entity.PhraseBook;
import it.clevercom.echo.rd.model.entity.Province;
import it.clevercom.echo.rd.repository.IMunicipality_rd_Repository;
import it.clevercom.echo.rd.repository.IProvince_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/province")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Province Controller
 * @author luca
 */

public class Province_rd_Controller extends EchoController {

	@Autowired
	private Environment env;
	
	@Autowired
	private IProvince_rd_Repository repo;
	
	@Autowired
	private IMunicipality_rd_Repository repo_m;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	public static final String entity_name = "Province";
	public static final String entity_id = "idprovince";
	
	/**
	 * Get province by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody ProvinceDTO get(@PathVariable Long id) throws Exception {
		Province entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		return rdDozerMapper.map(entity, ProvinceDTO.class);
	}
	
	/**
	 * Get province list by criteria with pagination
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
	public @ResponseBody PagedDTO<ProvinceDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="500", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue=entity_id, required=false) String field) throws Exception {
		
		// check enum string params
		validator.validateSort(sort);
		validator.validateSortField(field, Province.class, entity_name);
		
		CriteriaRequestProcessor<IProvince_rd_Repository, Province, ProvinceDTO> rp = 
				new CriteriaRequestProcessor<IProvince_rd_Repository, Province, ProvinceDTO>(repo, 
						rdDozerMapper, 
						ProvinceDTO.class, 
						entity_name, 
						criteria, 
						sort, 
						field, 
						page, 
						size,
						env);
		
		// process data request
		return rp.process();
	}
}
