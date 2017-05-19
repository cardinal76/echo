package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.domain.Specifications;
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
import it.clevercom.echo.common.jpa.specification.DateIntervalSpecification;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.common.util.DateUtil;
import it.clevercom.echo.common.util.StringUtils;
import it.clevercom.echo.rd.component.Validator;
import it.clevercom.echo.rd.enums.WorkStatusEnum;
import it.clevercom.echo.rd.jpa.specification.ModalitySpecification;
import it.clevercom.echo.rd.jpa.specification.ModalityTypeSpecification;
import it.clevercom.echo.rd.jpa.specification.WorkStatusSpecification;
import it.clevercom.echo.rd.model.dto.WorkTaskDTO;
import it.clevercom.echo.rd.model.entity.Modality;
import it.clevercom.echo.rd.model.entity.WorkTask;
import it.clevercom.echo.rd.repository.IWorkStatus_rd_Repository;
import it.clevercom.echo.rd.repository.IWorkTask_rd_Repository;
import it.clevercom.echo.rd.util.WorkStatusDateFieldDecoder;

@Controller
@RestController
@RequestMapping("rd/assets/worktask")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Work Task Controller
 * @author luca
 */
public class WorkTask_rd_Controller extends EchoController {
	@Autowired
	private Environment env;
	
	@Autowired
	private IWorkTask_rd_Repository repo;
	
	@Autowired
	private IWorkStatus_rd_Repository repo_ws;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	@PersistenceContext(unitName="rdPU")
	protected EntityManager em;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind entity name and id in exception message
	public static final String entity_name = "WorkTask";
	public static final String entity_id = "idworktask";
	
	/**
	 * Get a work task by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody WorkTaskDTO get(@PathVariable Long id) throws Exception {
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting"), entity_name, entity_id, id.toString()));
		
		// find entity
		WorkTask entity = repo.findOne(id);
		
		// check if entity has been found
		if (entity == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.returning.response"), entity_name, entity_id, id.toString()));
		return rdDozerMapper.map(entity, WorkTaskDTO.class);
	}
	
	/**
	 * Get a work task list by criteria with pagination
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
	public @ResponseBody PagedDTO<WorkTaskDTO> getByCriteria (
			@RequestParam(defaultValue="current_week_start", required=false) Long from,
			@RequestParam(defaultValue="current_week_end", required=false) Long to,
			@RequestParam(defaultValue = "*", required=false) String status,
			@RequestParam(defaultValue="0", required=false) Long idmodalitytype,
			@RequestParam(defaultValue="0", required=false) Long idmodality,
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="15", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue=entity_id, required=false) String field) throws Exception {
		
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateSort(sort);
		validator.validateSortField(field, WorkTask.class, entity_name);
		
		// set processor params
		CriteriaRequestProcessor<IWorkTask_rd_Repository, WorkTask, WorkTaskDTO> processor = getProcessor();
		processor.setCriteria(criteria);
		processor.setPageCriteria(sort, field, page, size);
		
		// get dates
		final Date t1 = DateUtil.getStartOfDay(new Date(from));
		final Date t2 = DateUtil.getEndOfDay(new Date(to));
		
		// if there's a selected status, create and set status specification
		if (!status.equals("*")) {
			// start decoding status
			String[] statusItems = StringUtils.split(status, "\\|");
			// iterate status item and create the right specification
			for (int i = 0; i < statusItems.length; i++) {
				Specifications<WorkTask> current = null;
				// create status specification
				WorkStatusSpecification<WorkTask> st = new WorkStatusSpecification<WorkTask>(repo_ws.findByCode(WorkStatusEnum.getInstanceFromCodeValue(statusItems[i]).code()).getIdworkstatus());
				// create interval specification based on right date field
				DateIntervalSpecification<WorkTask, WorkTask> interval = new DateIntervalSpecification<WorkTask, WorkTask>(null, t1, t2, WorkStatusDateFieldDecoder.decodeDateFieldFromWorkStatus(WorkStatusEnum.getInstanceFromCodeValue(statusItems[i])));
				current = Specifications.where(current).and(st).and(interval);
				if (i==0) {
					processor.addAndSpecification(Specifications.where(current));
				} else {
					processor.addOrSpecification(Specifications.where(current));
				}
			}
		} else {
			// create standard specification based on date interval and standard field name
			// parse long parameter to Date Object
			DateIntervalSpecification<WorkTask, WorkTask> interval = new DateIntervalSpecification<WorkTask, WorkTask>(null, t1, t2, WorkStatusDateFieldDecoder.decodeDateFieldFromWorkStatus(WorkStatusEnum.SCHEDULED));
			processor.addAndSpecification(interval);
		}
		
		// if there's a selected patient name, create and set patient name specification
		if ((!idmodality.equals(Long.valueOf(0)))) {
			ModalitySpecification<WorkTask, WorkTask> n = new ModalitySpecification<WorkTask, WorkTask>(null, null, idmodality); 
			processor.addAndSpecification(n);
		} else if ((!idmodalitytype.equals(Long.valueOf(0)))) {
			ModalityTypeSpecification<WorkTask, Modality> n = new ModalityTypeSpecification<WorkTask, Modality>("modality", null, idmodalitytype); 
			processor.addAndSpecification(n);
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
		
		// process data request
		return processor.process();	
	}
	
	/**
	 * Add a work task
	 * @param workTask
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<WorkTaskDTO> add(@RequestBody WorkTaskDTO workTask, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTONullIdd(workTask, entity_id);

		// invoke order creator
		CreateRequestProcessor<IWorkTask_rd_Repository, WorkTask, WorkTaskDTO> creator = getCreator();
		creator.setCreatedUser(getLoggedUser(request));
		creator.setDto(workTask);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
		
		// process
		return creator.process();
	}
	
	/**
	 * Update a work task
	 * @param workTask
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<WorkTaskDTO> update(@RequestBody WorkTaskDTO workTask, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTOIdd(workTask, entity_name);

		// set updater params
		UpdateRequestProcessor<IWorkTask_rd_Repository, WorkTask, WorkTaskDTO> updater = getUpdater();
		updater.setSourceDto(workTask);
		updater.setUpdatedUser(getLoggedUser(request));
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, workTask.getIdd().toString()));

		// return response
		return updater.process();
	}
	
	/**
	 * Delete a work task 
	 * @param workTask
	 * @param request
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<WorkTaskDTO> delete(@RequestBody WorkTaskDTO workTask, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
				
		// validate that username can perform the requested operation on appSetting
		validator.validateDTOIdd(workTask, entity_name);

		// set updater params
		UpdateRequestProcessor<IWorkTask_rd_Repository, WorkTask, WorkTaskDTO> updater = getUpdater();
		updater.setSourceDto(workTask);
		updater.setUpdatedUser(getLoggedUser(request));
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, workTask.getIdd().toString()));

		// return response
		return updater.enable(false);
	}

	@Override
	protected CreateRequestProcessor<IWorkTask_rd_Repository, WorkTask, WorkTaskDTO> getCreator() {
		// TODO Auto-generated method stub
		return new CreateRequestProcessor<IWorkTask_rd_Repository, WorkTask, WorkTaskDTO>(repo, rdDozerMapper, WorkTask.class, entity_name, env, em);
	}

	@Override
	protected UpdateRequestProcessor<IWorkTask_rd_Repository, WorkTask, WorkTaskDTO> getUpdater() {
		// TODO Auto-generated method stub
		return new UpdateRequestProcessor<IWorkTask_rd_Repository, WorkTask, WorkTaskDTO>(repo, rdDozerMapper, entity_name, entity_id, env, em);
	}

	@Override
	protected CriteriaRequestProcessor<IWorkTask_rd_Repository, WorkTask, WorkTaskDTO> getProcessor() {
		// TODO Auto-generated method stub
		return new CriteriaRequestProcessor<IWorkTask_rd_Repository, WorkTask, WorkTaskDTO>(repo, rdDozerMapper, WorkTaskDTO.class, entity_name, env);
	}
}
