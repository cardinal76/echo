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
import it.clevercom.echo.rd.jpa.specification.WorkStatusSpecification;
import it.clevercom.echo.rd.model.dto.WorkSessionDTO;
import it.clevercom.echo.rd.model.entity.WorkSession;
import it.clevercom.echo.rd.model.entity.WorkTask;
import it.clevercom.echo.rd.repository.IWorkSession_rd_Repository;
import it.clevercom.echo.rd.repository.IWorkStatus_rd_Repository;
import it.clevercom.echo.rd.util.WorkStatusDateFieldDecoder;

@Controller
@RestController
@RequestMapping("rd/assets/worksession")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

public class WorkSession_rd_Controller extends EchoController {
	@Autowired
	private Environment env;
	
	@Autowired
	private IWorkSession_rd_Repository repo;
	
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
	public static final String entity_name = "WorkSession";
	public static final String entity_id = "idworksession";
	
	/**
	 * Get a work session by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody WorkSessionDTO get(@PathVariable Long id) throws Exception {
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting"), entity_name, entity_id, id.toString()));
		
		// find entity
		WorkSession entity = repo.findOne(id);
		
		// check if entity has been found
		if (entity == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.returning.response"), entity_name, entity_id, id.toString()));
		return rdDozerMapper.map(entity, WorkSessionDTO.class);
	}
	
	/**
	 * Get a work session list by criteria with pagination
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
	public @ResponseBody PagedDTO<WorkSessionDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria,
			@RequestParam(defaultValue = "today_start", required = false) Long from,
			@RequestParam(defaultValue = "today_end", required = false) Long to,
			@RequestParam(defaultValue = "*", required = false) String status,
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="15", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue="scheduleddate", required=false) String field) throws Exception {
		
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
				
		// validate
		validator.validateSort(sort);
		validator.validateSortField(field, WorkSession.class, entity_name);
		
		// set processor params
		CriteriaRequestProcessor<IWorkSession_rd_Repository, WorkSession, WorkSessionDTO> processor = getProcessor();
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
				Specifications<WorkSession> current = null;
				// create status specification
				WorkStatusSpecification<WorkSession> st = new WorkStatusSpecification<WorkSession>(repo_ws.findByCode(WorkStatusEnum.getInstanceFromCodeValue(statusItems[i]).code()).getIdworkstatus());
				// create interval specification based on right date field
				DateIntervalSpecification<WorkSession, WorkSession> interval = new DateIntervalSpecification<WorkSession, WorkSession>(null, t1, t2, WorkStatusDateFieldDecoder.decodeDateFieldFromWorkStatus(WorkStatusEnum.getInstanceFromCodeValue(statusItems[i])));
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
			DateIntervalSpecification<WorkSession, WorkSession> interval = new DateIntervalSpecification<WorkSession, WorkSession>(null, t1, t2, WorkStatusDateFieldDecoder.decodeDateFieldFromWorkStatus(WorkStatusEnum.SCHEDULED));
			processor.addAndSpecification(interval);
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
		
		// process data request
		return processor.process();	
	}
	
	/**
	 * Add a work session
	 * @param workSession
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<WorkSessionDTO> add(@RequestBody WorkSessionDTO workSession, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTONullIdd(workSession, entity_id);
		
		// invoke order creator
		CreateRequestProcessor<IWorkSession_rd_Repository, WorkSession, WorkSessionDTO> creator = getCreator();
		creator.setCreatedUser(getLoggedUser(request));
		creator.setDto(workSession);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
		
		// process
		return creator.process();
	}
	
	/**
	 * Update a work session
	 * @param workSession
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<WorkSessionDTO> update(@RequestBody WorkSessionDTO workSession, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate that username can perform the requested operation on appSetting
		validator.validateDTOIdd(workSession, entity_name);

		// set updater params
		UpdateRequestProcessor<IWorkSession_rd_Repository, WorkSession, WorkSessionDTO> updater = getUpdater();
		updater.setSourceDto(workSession);
		updater.setUpdatedUser(getLoggedUser(request));
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, workSession.getIdd().toString()));

		// return response
		return updater.process();
	}
	
	/**
	 * Delete a work session 
	 * @param workSession
	 * @param request
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<WorkSessionDTO> delete(@RequestBody WorkSessionDTO workSession, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
				
		// validate that username can perform the requested operation on appSetting
		validator.validateDTOIdd(workSession, entity_name);

		// set updater params
		UpdateRequestProcessor<IWorkSession_rd_Repository, WorkSession, WorkSessionDTO> updater = getUpdater();
		updater.setSourceDto(workSession);
		updater.setUpdatedUser(getLoggedUser(request));
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, workSession.getIdd().toString()));

		// return response
		return updater.enable(false);
	}

	@Override
	protected CreateRequestProcessor<IWorkSession_rd_Repository, WorkSession, WorkSessionDTO> getCreator() {
		// TODO Auto-generated method stub
		return new CreateRequestProcessor<IWorkSession_rd_Repository, WorkSession, WorkSessionDTO>(repo, rdDozerMapper, WorkSession.class, entity_name, env, em);
	}

	@Override
	protected UpdateRequestProcessor<IWorkSession_rd_Repository, WorkSession, WorkSessionDTO> getUpdater() {
		// TODO Auto-generated method stub
		return new UpdateRequestProcessor<IWorkSession_rd_Repository, WorkSession, WorkSessionDTO>(repo, rdDozerMapper, entity_name, entity_id, env, em);
	}

	@Override
	protected CriteriaRequestProcessor<IWorkSession_rd_Repository, WorkSession, WorkSessionDTO> getProcessor() {
		// TODO Auto-generated method stub
		return new CriteriaRequestProcessor<IWorkSession_rd_Repository, WorkSession, WorkSessionDTO>(repo, rdDozerMapper, WorkSessionDTO.class, entity_name, env);
	}
}
