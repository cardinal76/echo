package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

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
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.jpa.CreateRequestProcessor;
import it.clevercom.echo.common.jpa.CriteriaRequestProcessor;
import it.clevercom.echo.common.jpa.UpdateRequestProcessor;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.rd.component.Validator;
import it.clevercom.echo.rd.model.dto.MunicipalityDTO;
import it.clevercom.echo.rd.model.entity.Municipality;
import it.clevercom.echo.rd.repository.IMunicipality_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/municipality")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Municipality Controller
 * @author luca
 */

public class Municipality_rd_Controller extends EchoController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private IMunicipality_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	@PersistenceContext(unitName="rdPU")
	protected EntityManager em;

	// crud processors
	private CriteriaRequestProcessor<IMunicipality_rd_Repository, Municipality, MunicipalityDTO> processor;
	private CreateRequestProcessor<IMunicipality_rd_Repository, Municipality, MunicipalityDTO> creator;
	private UpdateRequestProcessor<IMunicipality_rd_Repository, Municipality, MunicipalityDTO> updater;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	public static final String entity_name = "Municipality";
	public static final String entity_id = "idmunicipality";
	
	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		// construct creator
		creator = new CreateRequestProcessor<IMunicipality_rd_Repository, Municipality, MunicipalityDTO>(repo, rdDozerMapper, Municipality.class, entity_name, env, em);
		// construct updater
		updater = new UpdateRequestProcessor<IMunicipality_rd_Repository, Municipality, MunicipalityDTO>(repo, rdDozerMapper, entity_name, entity_id, env, em);
		// costruct processor
		processor = new CriteriaRequestProcessor<IMunicipality_rd_Repository, Municipality, MunicipalityDTO>(repo, rdDozerMapper, MunicipalityDTO.class, entity_name, env);
	}
	
	/**
	 * Get municipality by id
	 * @author luca
	 * @category standard get by id REST method
	 * @param id
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody MunicipalityDTO get(@PathVariable Long id) throws Exception {
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting"), entity_name, entity_id, id.toString()));
		
		// find entity
		Municipality entity = repo.findOne(id);
		
		// check if entity has been found
		if (entity == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.returning.response"), entity_name, entity_id, id.toString()));
		return rdDozerMapper.map(entity, MunicipalityDTO.class);
	}
	
	/**
	 * Get municipality list by criteria with pagination
	 * @author luca
	 * @category standard get by criteria REST method
	 * @param criteria
	 * @param page
	 * @param size
	 * @param sort
	 * @param field
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<MunicipalityDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="20000", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue=entity_id, required=false) String field) throws Exception {
		
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
				
		// validate
		validator.validateSort(sort);
		validator.validateSortField(field, Municipality.class, entity_name);

		// create processor
		CriteriaRequestProcessor<IMunicipality_rd_Repository, Municipality, MunicipalityDTO> rp = 
				new CriteriaRequestProcessor<IMunicipality_rd_Repository, Municipality, MunicipalityDTO>(repo, 
						rdDozerMapper, 
						MunicipalityDTO.class, 
						entity_name, 
						criteria, 
						sort, 
						field, 
						page, 
						size,
						env);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
		
		// process data request
		return rp.process();
	}
	
	/**
	 * Add a municipality
	 * @author luca
	 * @category standard create REST method
	 * @param modality
	 * @param request
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<MunicipalityDTO> add(@RequestBody MunicipalityDTO municipality, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTONullIdd(municipality, entity_id);
				
		// create the processor
		CreateRequestProcessor<IMunicipality_rd_Repository, Municipality, MunicipalityDTO> rp = 
				new CreateRequestProcessor<IMunicipality_rd_Repository, Municipality, MunicipalityDTO>(repo,
						rdDozerMapper,
						Municipality.class,
						entity_name,
						getLoggedUser(request),
						municipality,
						env);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
		
		// process
		return rp.process();
	}
	
	/**
	 * Update a municipality
	 * @author luca
	 * @category standard update REST method
	 * @param modality
	 * @param request
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<MunicipalityDTO> update(@RequestBody MunicipalityDTO municipality, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTOIdd(municipality, entity_name);

		// create processor
		UpdateRequestProcessor<IMunicipality_rd_Repository, Municipality, MunicipalityDTO> rp = 
				new UpdateRequestProcessor<IMunicipality_rd_Repository, Municipality, MunicipalityDTO>(repo, 
						rdDozerMapper,
						entity_name,
						entity_id,
						getLoggedUser(request), 
						municipality, 
						env);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, municipality.getIdd().toString()));

		// return response
		return rp.process();
	}
	
	/**
	 * Delete a municipality 
	 * @author luca
	 * @category standard delete REST method
	 * @param modality
	 * @param request
	 * @since 1.2.0
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<MunicipalityDTO> delete(@RequestBody MunicipalityDTO municipality, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTOIdd(municipality, entity_name);

		// create processor
		UpdateRequestProcessor<IMunicipality_rd_Repository, Municipality, MunicipalityDTO> rp = 
				new UpdateRequestProcessor<IMunicipality_rd_Repository, Municipality, MunicipalityDTO>(repo, 
						rdDozerMapper,
						entity_name,
						entity_id,
						getLoggedUser(request), 
						municipality, 
						env);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, municipality.getIdd().toString()));

		// return response
		return rp.enable(false);
	}
}
