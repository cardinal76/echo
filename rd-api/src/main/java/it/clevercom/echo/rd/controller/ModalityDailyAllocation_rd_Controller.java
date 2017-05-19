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
import it.clevercom.echo.rd.model.dto.ModalityDailyAllocationDTO;
import it.clevercom.echo.rd.model.entity.ModalityDailyAllocation;
import it.clevercom.echo.rd.repository.IModalityDailyAllocation_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/assets/modallocation")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

public class ModalityDailyAllocation_rd_Controller extends EchoController {
	@Autowired
	private Environment env;
	
	@Autowired
	private IModalityDailyAllocation_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	@PersistenceContext(unitName="rdPU")
	protected EntityManager em;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind entity name and id in exception message
	public static final String entity_name = "ModalityDailyAllocation";
	public static final String entity_id = "idmodalitydailyallocation";
	
	/**
	 * Get an allocation by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody ModalityDailyAllocationDTO get(@PathVariable Long id) throws Exception {
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting"), entity_name, entity_id, id.toString()));
		
		// find entity
		ModalityDailyAllocation entity = repo.findOne(id);
		
		// check if entity has been found
		if (entity == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.returning.response"), entity_name, entity_id, id.toString()));
		return rdDozerMapper.map(entity, ModalityDailyAllocationDTO.class);
	}
	
	/**
	 * Get an allocation list by criteria with pagination
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
	public @ResponseBody PagedDTO<ModalityDailyAllocationDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="15", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue=entity_id, required=false) String field) throws Exception {
		
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
				
		// validate
		validator.validateSort(sort);
		validator.validateSortField(field, ModalityDailyAllocation.class, entity_name);
		
		// set processor params
		CriteriaRequestProcessor<IModalityDailyAllocation_rd_Repository, ModalityDailyAllocation, ModalityDailyAllocationDTO> processor = getProcessor();
		processor.setCriteria(criteria);
		processor.setPageCriteria(sort, field, page, size);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
		
		// process data request
		return processor.process();	
	}
	
	/**
	 * Add an allocation 
	 * @param allocation
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<ModalityDailyAllocationDTO> add(@RequestBody ModalityDailyAllocationDTO allocation, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTONullIdd(allocation, entity_id);
		
		// invoke order creator
		CreateRequestProcessor<IModalityDailyAllocation_rd_Repository, ModalityDailyAllocation, ModalityDailyAllocationDTO> creator = getCreator();
		creator.setCreatedUser(getLoggedUser(request));
		creator.setDto(allocation);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
		
		// process
		return creator.process();
	}
	
	/**
	 * Update an allocation 
	 * @param allocation
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<ModalityDailyAllocationDTO> update(@RequestBody ModalityDailyAllocationDTO allocation, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate that username can perform the requested operation on appSetting
		validator.validateDTOIdd(allocation, entity_name);

		// set updater params
		UpdateRequestProcessor<IModalityDailyAllocation_rd_Repository, ModalityDailyAllocation, ModalityDailyAllocationDTO> updater = getUpdater();
		updater.setSourceDto(allocation);
		updater.setUpdatedUser(getLoggedUser(request));
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, allocation.getIdd().toString()));

		// return response
		return updater.process();
	}
	
	/**
	 * Delete an allocation 
	 * @param allocation
	 * @param request
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<ModalityDailyAllocationDTO> delete(@RequestBody ModalityDailyAllocationDTO allocation, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
				
		// validate that username can perform the requested operation on appSetting
		validator.validateDTOIdd(allocation, entity_name);

		// set updater params
		UpdateRequestProcessor<IModalityDailyAllocation_rd_Repository, ModalityDailyAllocation, ModalityDailyAllocationDTO> updater = getUpdater();
		updater.setSourceDto(allocation);
		updater.setUpdatedUser(getLoggedUser(request));
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, allocation.getIdd().toString()));

		// return response
		return updater.enable(false);
	}

	@Override
	protected CreateRequestProcessor<IModalityDailyAllocation_rd_Repository, ModalityDailyAllocation, ModalityDailyAllocationDTO> getCreator() {
		// construct creator
		return new CreateRequestProcessor<IModalityDailyAllocation_rd_Repository, ModalityDailyAllocation, ModalityDailyAllocationDTO>(repo, rdDozerMapper, ModalityDailyAllocation.class, entity_name, env, em);		
	}

	@Override
	protected UpdateRequestProcessor<IModalityDailyAllocation_rd_Repository, ModalityDailyAllocation, ModalityDailyAllocationDTO> getUpdater() {
		// construct updater
		return new UpdateRequestProcessor<IModalityDailyAllocation_rd_Repository, ModalityDailyAllocation, ModalityDailyAllocationDTO>(repo, rdDozerMapper, entity_name, entity_id, env, em);
				
	}

	@Override
	protected CriteriaRequestProcessor<IModalityDailyAllocation_rd_Repository, ModalityDailyAllocation, ModalityDailyAllocationDTO> getProcessor() {
		// costruct processor
		return new CriteriaRequestProcessor<IModalityDailyAllocation_rd_Repository, ModalityDailyAllocation, ModalityDailyAllocationDTO>(repo, rdDozerMapper, ModalityDailyAllocationDTO.class, entity_name, env);
		
	}
}
