package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.rd.component.Validator;
import it.clevercom.echo.rd.model.dto.WorkPriorityDTO;
import it.clevercom.echo.rd.model.entity.WorkPriority;
import it.clevercom.echo.rd.repository.IWorkPriority_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/workpriority")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Work Priority Controller
 * @author luca
 */

public class WorkPriority_rd_Controller extends EchoController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private IWorkPriority_rd_Repository repo;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	@PersistenceContext(unitName="rdPU")
	protected EntityManager em;

	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	public static final String entity_name = "WorkPriority";
	public static final String entity_id = "idworkpriority";
	
	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		
	}
	
	/**
	 * Get work priority by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody WorkPriorityDTO get(@PathVariable Long id) throws Exception {
		WorkPriority entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		return rdDozerMapper.map(entity, WorkPriorityDTO.class);
	}
	
	/**
	 * Get work priority by criteria with pagination
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
	public @ResponseBody PagedDTO<WorkPriorityDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="15", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue=entity_id, required=false) String field) throws Exception {
		
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// check enum string params
		validator.validateSort(sort);
		validator.validateSortField(field, WorkPriority.class, entity_name);
		
		// set processor params
		CriteriaRequestProcessor<IWorkPriority_rd_Repository, WorkPriority, WorkPriorityDTO> processor = getProcessor();
		processor.setCriteria(criteria);
		processor.setPageCriteria(sort, field, page, size);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
		
		// process data request
		return processor.process();
	}
	
	/**
	 * Add work priority
	 * @param workpriority
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody String add(@RequestBody WorkPriorityDTO workpriority) throws Exception {
		return MessageFormat.format(env.getProperty("echo.api.crud.notsupported"), RequestMethod.POST.toString(), entity_name);
	}
	
	/**
	 * Update work priority
	 * @param workpriority
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody String update(@RequestBody WorkPriorityDTO workpriority) throws Exception {
		return MessageFormat.format(env.getProperty("echo.api.crud.notsupported"), RequestMethod.PUT.toString(), entity_name);
	}
	
	/**
	 * Delete work priority
	 * @param workpriority
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody String delete(@RequestBody WorkPriorityDTO workpriority) {
		return MessageFormat.format(env.getProperty("echo.api.crud.notsupported"), RequestMethod.DELETE.toString(), entity_name);
	}

	@Override
	protected CreateRequestProcessor<IWorkPriority_rd_Repository, WorkPriority, WorkPriorityDTO> getCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected UpdateRequestProcessor<IWorkPriority_rd_Repository, WorkPriority, WorkPriorityDTO> getUpdater() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected CriteriaRequestProcessor<IWorkPriority_rd_Repository, WorkPriority, WorkPriorityDTO> getProcessor() {
		// TODO Auto-generated method stub
		return new CriteriaRequestProcessor<IWorkPriority_rd_Repository, WorkPriority, WorkPriorityDTO>(repo, rdDozerMapper, WorkPriorityDTO.class, entity_name, env);
	}
}
