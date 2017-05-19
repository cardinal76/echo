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
import it.clevercom.echo.rd.model.dto.Icd9PatologyGroupDTO;
import it.clevercom.echo.rd.model.entity.Icd9PatologyGroup;
import it.clevercom.echo.rd.repository.IICD9PatologyGroup_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/icd9patologygroup")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

public class ICD9PatologyGroup_rd_Controller extends EchoController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private IICD9PatologyGroup_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	@PersistenceContext(unitName="rdPU")
	protected EntityManager em;
	
	// crud processors
	private CriteriaRequestProcessor<IICD9PatologyGroup_rd_Repository, Icd9PatologyGroup, Icd9PatologyGroupDTO> processor;
	private CreateRequestProcessor<IICD9PatologyGroup_rd_Repository, Icd9PatologyGroup, Icd9PatologyGroupDTO> creator;
	private UpdateRequestProcessor<IICD9PatologyGroup_rd_Repository, Icd9PatologyGroup, Icd9PatologyGroupDTO> updater;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind entity name and id in exception message
	private static String entity_name = "Icd9PatologyGroup";
	private static String entity_id = "idicd9patologygroup";

	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		// construct creator
		creator = new CreateRequestProcessor<IICD9PatologyGroup_rd_Repository, Icd9PatologyGroup, Icd9PatologyGroupDTO>(repo, rdDozerMapper, Icd9PatologyGroup.class, entity_name, env, em);
		// construct updater
		updater = new UpdateRequestProcessor<IICD9PatologyGroup_rd_Repository, Icd9PatologyGroup, Icd9PatologyGroupDTO>(repo, rdDozerMapper, entity_name, entity_id, env, em);
		// costruct processor
		processor = new CriteriaRequestProcessor<IICD9PatologyGroup_rd_Repository, Icd9PatologyGroup, Icd9PatologyGroupDTO>(repo, rdDozerMapper, Icd9PatologyGroupDTO.class, entity_name, env);
	}
	
	/**
	 * Get a body apparatus by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody Icd9PatologyGroupDTO get(@PathVariable Long id) throws Exception {
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting"), entity_name, entity_id, id.toString()));
		
		// find entity
		Icd9PatologyGroup entity = repo.findOne(id);
		
		// check if entity has been found
		if (entity == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.returning.response"), entity_name, entity_id, id.toString()));
		return rdDozerMapper.map(entity, Icd9PatologyGroupDTO.class);
	}
	
	/**
	 * Get a body apparatus list by criteria with pagination
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
	public @ResponseBody PagedDTO<Icd9PatologyGroupDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="1000", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue="code", required=false) String field) throws Exception {
		
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
				
		// check enum string params
		validator.validateSort(sort);
		
//		CriteriaRequestProcessor<IIcd9PatologyGroup_rd_Repository, Icd9PatologyGroup, Icd9PatologyGroupDTO> rp = 
//				new CriteriaRequestProcessor<IIcd9PatologyGroup_rd_Repository, Icd9PatologyGroup, Icd9PatologyGroupDTO>(repo, 
//						rdDozerMapper, 
//						Icd9PatologyGroupDTO.class, 
//						entity_name, 
//						criteria, 
//						sort, 
//						field, 
//						page, 
//						size,
//						env);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
		
		// process data request
		return processor.process();	
	}
	
	/**
	 * Add a body apparatus
	 * @param bodyapparatus
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<Icd9PatologyGroupDTO> add(@RequestBody Icd9PatologyGroupDTO bodyapparatus, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
				
//		// create the processor
//		CreateRequestProcessor<IIcd9PatologyGroup_rd_Repository, Icd9PatologyGroup, Icd9PatologyGroupDTO> rp = 
//				new CreateRequestProcessor<IIcd9PatologyGroup_rd_Repository, Icd9PatologyGroup, Icd9PatologyGroupDTO>(repo, 
//						rdDozerMapper, 
//						Icd9PatologyGroup.class, 
//						entity_name, 
//						getLoggedUser(request), 
//						bodyapparatus,
//						env);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name, entity_id, bodyapparatus.getIdd().toString()));
		
		// process
		return creator.process();
	}
	
	/**
	 * Update a body apparatus
	 * @param icd9group
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<Icd9PatologyGroupDTO> update(@RequestBody Icd9PatologyGroupDTO icd9group, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate that username can perform the requested operation on appSetting
		validator.validateDTOIdd(icd9group, entity_name);

//		// create processor
//		UpdateRequestProcessor<IIcd9PatologyGroup_rd_Repository, Icd9PatologyGroup, Icd9PatologyGroupDTO> rp = 
//				new UpdateRequestProcessor<IIcd9PatologyGroup_rd_Repository, Icd9PatologyGroup, Icd9PatologyGroupDTO>(repo, 
//						rdDozerMapper,
//						entity_name,
//						entity_id,
//						getLoggedUser(request), 
//						icd9group, 
//						env);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, icd9group.getIdd().toString()));

		// return response
		return updater.process();
	}
	
	/**
	 * Delete a body apparatus 
	 * @param icd9group
	 * @param request
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<Icd9PatologyGroupDTO> delete(@RequestBody Icd9PatologyGroupDTO icd9group, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
				
		// validate that username can perform the requested operation on appSetting
		validator.validateDTOIdd(icd9group, entity_name);

//		// create processor
//		UpdateRequestProcessor<IIcd9PatologyGroup_rd_Repository, Icd9PatologyGroup, Icd9PatologyGroupDTO> rp = 
//				new UpdateRequestProcessor<IIcd9PatologyGroup_rd_Repository, Icd9PatologyGroup, Icd9PatologyGroupDTO>(repo, 
//						rdDozerMapper,
//						entity_name,
//						entity_id,
//						getLoggedUser(request), 
//						icd9group, 
//						env);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, icd9group.getIdd().toString()));

		// return response
		return updater.enable(false);
	}

	@Override
	protected CreateRequestProcessor<?, ?, ?> getCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected UpdateRequestProcessor<?, ?, ?> getUpdater() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected CriteriaRequestProcessor<?, ?, ?> getProcessor() {
		// TODO Auto-generated method stub
		return null;
	}
}
