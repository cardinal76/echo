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
import it.clevercom.echo.rd.model.dto.PhraseBookDTO;
import it.clevercom.echo.rd.model.entity.PhraseBook;
import it.clevercom.echo.rd.repository.IPhraseBook_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/assets/phrasebook")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

public class PhraseBook_rd_Controller extends EchoController {
	@Autowired
	private Environment env;
	
	@Autowired
	private IPhraseBook_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	@PersistenceContext(unitName="rdPU")
	protected EntityManager em;

	// crud processors
	private CriteriaRequestProcessor<IPhraseBook_rd_Repository, PhraseBook, PhraseBookDTO> processor;
	private CreateRequestProcessor<IPhraseBook_rd_Repository, PhraseBook, PhraseBookDTO> creator;
	private UpdateRequestProcessor<IPhraseBook_rd_Repository, PhraseBook, PhraseBookDTO> updater;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind entity name and id in exception message
	public static final String entity_name = "PhraseBook";
	public static final String entity_id = "idphrasebook";
	
	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		// construct creator
		creator = new CreateRequestProcessor<IPhraseBook_rd_Repository, PhraseBook, PhraseBookDTO>(repo, rdDozerMapper, PhraseBook.class, entity_name, env, em);
		// construct updater
		updater = new UpdateRequestProcessor<IPhraseBook_rd_Repository, PhraseBook, PhraseBookDTO>(repo, rdDozerMapper, entity_name, entity_id, env, em);
		// costruct processor
		processor = new CriteriaRequestProcessor<IPhraseBook_rd_Repository, PhraseBook, PhraseBookDTO>(repo, rdDozerMapper, PhraseBookDTO.class, entity_name, env);
	}
	
	/**
	 * Get a phrase book by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PhraseBookDTO get(@PathVariable Long id) throws Exception {
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting"), entity_name, entity_id, id.toString()));
		
		// find entity
		PhraseBook entity = repo.findOne(id);
		
		// check if entity has been found
		if (entity == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.returning.response"), entity_name, entity_id, id.toString()));
		return rdDozerMapper.map(entity, PhraseBookDTO.class);
	}
	
	/**
	 * Get a phrase book list by criteria with pagination
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
	public @ResponseBody PagedDTO<PhraseBookDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="15", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue=entity_id, required=false) String field) throws Exception {
		
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
				
		// validate
		validator.validateSort(sort);
		validator.validateSortField(field, PhraseBook.class, entity_name);
		
		// set processor params
		processor.setCriteria(criteria);
		processor.setPageCriteria(sort, field, page, size);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
		
		// process data request
		return processor.process();	
	}
	
	/**
	 * Add a phrase book
	 * @param phraseBook
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<PhraseBookDTO> add(@RequestBody PhraseBookDTO phraseBook, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTONullIdd(phraseBook, entity_id);
				
		// invoke order creator
		creator.setCreatedUser(getLoggedUser(request));
		creator.setDto(phraseBook);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
		
		// process
		return creator.process();
	}
	
	/**
	 * Update a phrase book
	 * @param phraseBook
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<PhraseBookDTO> update(@RequestBody PhraseBookDTO phraseBook, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate that username can perform the requested operation on appSetting
		validator.validateDTOIdd(phraseBook, entity_name);

		// set updater params
		updater.setDto(phraseBook);
		updater.setUpdatedUser(getLoggedUser(request));
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, phraseBook.getIdd().toString()));

		// return response
		return updater.process();
	}
	
	/**
	 * Delete a phrase book 
	 * @param phraseBook
	 * @param request
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<PhraseBookDTO> delete(@RequestBody PhraseBookDTO phraseBook, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
				
		// validate that username can perform the requested operation on appSetting
		validator.validateDTOIdd(phraseBook, entity_name);

		// set updater params
		updater.setDto(phraseBook);
		updater.setUpdatedUser(getLoggedUser(request));
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, phraseBook.getIdd().toString()));

		// return response
		return updater.enable(false);
	}
}
