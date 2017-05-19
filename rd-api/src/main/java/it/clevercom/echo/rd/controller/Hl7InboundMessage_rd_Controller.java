package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;

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
import it.clevercom.echo.rd.model.dto.Hl7InboundMessageDTO;
import it.clevercom.echo.rd.model.entity.Hl7InboundMessage;
import it.clevercom.echo.rd.repository.IHl7InboundMessage_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/assets/hl7/inboundmessage")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Hl7InboundMessage Controller
 * @author luca
 */

public class Hl7InboundMessage_rd_Controller extends EchoController {
	@Autowired
	private Environment env;
	
	@Autowired
	private IHl7InboundMessage_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	@PersistenceContext(unitName="rdPU")
	protected EntityManager em;

	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	public static final String entity_name = "Hl7InboundMessage";
	public static final String entity_id = "idhl7inboundmessage";
	
	/**
	 * Get marital status by id
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
	public @ResponseBody Hl7InboundMessageDTO get(@PathVariable Long id) throws Exception {
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting"), entity_name, entity_id, id.toString()));
			
		// validate
		validator.validateId(id, entity_name);
		
		// find entity
		Hl7InboundMessage entity = repo.findOne(id);
		
		// check if entity has been found
		if (entity == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.returning.response"), entity_name, entity_id, id.toString()));
		return rdDozerMapper.map(entity, Hl7InboundMessageDTO.class);
	}
	
	/**
	 * Get marital status by criteria with pagination
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
	public @ResponseBody PagedDTO<Hl7InboundMessageDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="20", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue="idmaritalstatus", required=false) String field) throws Exception {
		
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
				
		// validate
		validator.validateSort(sort);
		validator.validateSortField(field, Hl7InboundMessage.class, entity_name);

		// set processor params
		CriteriaRequestProcessor<IHl7InboundMessage_rd_Repository, Hl7InboundMessage, Hl7InboundMessageDTO> processor = getProcessor();
		processor.setCriteria(criteria);
		processor.setPageCriteria(sort, field, page, size);
				
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));		
		
		// process data request
		return processor.process();
	}
	
	/**
	 * Add marital status
	 * @author luca
	 * @category standard create REST method
	 * @param maritalStatus
	 * @param request
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<Hl7InboundMessageDTO> add(@RequestBody Hl7InboundMessageDTO maritalStatus, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTONullIdd(maritalStatus, entity_id);
		
		// invoke order creator
		CreateRequestProcessor<IHl7InboundMessage_rd_Repository, Hl7InboundMessage, Hl7InboundMessageDTO> creator = getCreator();
		creator.setCreatedUser(getLoggedUser(request));
		creator.setDto(maritalStatus);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
		
		// process
		return creator.process();
	}
	
	/**
	 * Update marital status
	 * @author luca
	 * @category standard update REST method
	 * @param maritalStatus
	 * @param request
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<Hl7InboundMessageDTO> update(@RequestBody Hl7InboundMessageDTO maritalStatus, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTOIdd(maritalStatus, entity_name);

		// set updater params
		UpdateRequestProcessor<IHl7InboundMessage_rd_Repository, Hl7InboundMessage, Hl7InboundMessageDTO> updater = getUpdater();
		updater.setSourceDto(maritalStatus);
		updater.setUpdatedUser(getLoggedUser(request));
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, maritalStatus.getIdd().toString()));

		// return response
		return updater.process();
	}
	
	/**
	 * Delete marital status
	 * @author luca
	 * @category standard delete REST method
	 * @param maritalStatus
	 * @param request
	 * @since 1.2.0
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<Hl7InboundMessageDTO> delete(@RequestBody Hl7InboundMessageDTO maritalStatus, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
						
		// validate
		validator.validateDTOIdd(maritalStatus, entity_name);

		// set updater params
		UpdateRequestProcessor<IHl7InboundMessage_rd_Repository, Hl7InboundMessage, Hl7InboundMessageDTO> updater = getUpdater();
		updater.setSourceDto(maritalStatus);
		updater.setUpdatedUser(getLoggedUser(request));
				
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, maritalStatus.getIdd().toString()));

		// return response
		return updater.enable(false);
	}

	@Override
	protected CreateRequestProcessor<IHl7InboundMessage_rd_Repository, Hl7InboundMessage, Hl7InboundMessageDTO> getCreator() {
		return new CreateRequestProcessor<IHl7InboundMessage_rd_Repository, Hl7InboundMessage, Hl7InboundMessageDTO>(repo, rdDozerMapper, Hl7InboundMessage.class, entity_name, env, em);
	}

	@Override
	protected UpdateRequestProcessor<IHl7InboundMessage_rd_Repository, Hl7InboundMessage, Hl7InboundMessageDTO> getUpdater() {
		return new UpdateRequestProcessor<IHl7InboundMessage_rd_Repository, Hl7InboundMessage, Hl7InboundMessageDTO>(repo, rdDozerMapper, entity_name, entity_id, env, em);
	}

	@Override
	protected CriteriaRequestProcessor<IHl7InboundMessage_rd_Repository, Hl7InboundMessage, Hl7InboundMessageDTO> getProcessor() {
		return new CriteriaRequestProcessor<IHl7InboundMessage_rd_Repository, Hl7InboundMessage, Hl7InboundMessageDTO>(repo, rdDozerMapper, Hl7InboundMessageDTO.class, entity_name, env);
	}
}
