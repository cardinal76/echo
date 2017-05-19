package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import it.clevercom.echo.common.jpa.specification.DateIntervalSpecification;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.common.util.DateUtil;
import it.clevercom.echo.rd.component.Validator;
import it.clevercom.echo.rd.model.dto.BaseObjectDTO;
import it.clevercom.echo.rd.model.dto.ModalityDailyAllocationDTO;
import it.clevercom.echo.rd.model.dto.ModalityGroupDTO;
import it.clevercom.echo.rd.model.dto.ModalityTypeDTO;
import it.clevercom.echo.rd.model.dto.ModalityTypeDailyAllocationDTO;
import it.clevercom.echo.rd.model.dto.ModalityTypeIntervalAllocationDTO;
import it.clevercom.echo.rd.model.entity.Modality;
import it.clevercom.echo.rd.model.entity.ModalityDailyAllocation;
import it.clevercom.echo.rd.model.entity.ModalityType;
import it.clevercom.echo.rd.model.entity.VModalityAllocation;
import it.clevercom.echo.rd.model.entity.VModalitytypeAllocation;
import it.clevercom.echo.rd.repository.IModalityType_rd_Repository;
import it.clevercom.echo.rd.repository.IModality_rd_Repository;
import it.clevercom.echo.rd.repository.IVModalityAllocation_rd_Repository;
import it.clevercom.echo.rd.repository.IVModalitytypeAllocation_rd_Repository;

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
	private IModality_rd_Repository repo_m;
	
	@Autowired
	private IVModalitytypeAllocation_rd_Repository repo_mta;
	
	@Autowired
	private IVModalityAllocation_rd_Repository repo_mda;
	
	@Autowired
    private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	@PersistenceContext(unitName="rdPU")
	protected EntityManager em;

	// crud processors
	private CriteriaRequestProcessor<IModalityType_rd_Repository, ModalityType, ModalityTypeDTO> processor;
	private CriteriaRequestProcessor<IModalityType_rd_Repository, ModalityType, ModalityGroupDTO> processor_mg;
	private CriteriaRequestProcessor<IVModalitytypeAllocation_rd_Repository, VModalitytypeAllocation, ModalityTypeDailyAllocationDTO> processor_mta;
	private CreateRequestProcessor<IModalityType_rd_Repository, ModalityType, ModalityTypeDTO> creator;
	private UpdateRequestProcessor<IModalityType_rd_Repository, ModalityType, ModalityTypeDTO> updater;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	public static final String entity_name = "ModalityType";
	public static final String entity_id = "idmodalitytype";
	public static final String entity_allocation_type_name = "ModalityTypeAllocation";
	public static final String entity_allocation_type_id = "id";
	
	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		// construct creator
		creator = new CreateRequestProcessor<IModalityType_rd_Repository, ModalityType, ModalityTypeDTO>(repo, rdDozerMapper, ModalityType.class, entity_name, env, em);
		// construct updater
		updater = new UpdateRequestProcessor<IModalityType_rd_Repository, ModalityType, ModalityTypeDTO>(repo, rdDozerMapper, entity_name, entity_id, env, em);
		// construct processor
		processor = new CriteriaRequestProcessor<IModalityType_rd_Repository, ModalityType, ModalityTypeDTO>(repo, rdDozerMapper, ModalityTypeDTO.class, entity_name, env);
		processor_mg = new CriteriaRequestProcessor<IModalityType_rd_Repository, ModalityType, ModalityGroupDTO>(repo, rdDozerMapper, ModalityGroupDTO.class, entity_name, env);
		processor_mta =new CriteriaRequestProcessor<IVModalitytypeAllocation_rd_Repository, VModalitytypeAllocation, ModalityTypeDailyAllocationDTO>(repo_mta, rdDozerMapper, ModalityTypeDailyAllocationDTO.class, entity_allocation_type_name, env);
	}
	
	/**
	 * Get modality type by id
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
	public @ResponseBody ModalityTypeDTO get(@PathVariable Long id) throws Exception {
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting"), entity_name, entity_id, id.toString()));
		
		// find entity
		ModalityType entity = repo.findOne(id);
		
		// check if entity has been found
		if (entity == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		}

		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.returning.response"), entity_name, entity_id, id.toString()));
		return rdDozerMapper.map(entity, ModalityTypeDTO.class);
	}
	
	/**
	 * Get modality type list by criteria with pagination
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
	public @ResponseBody PagedDTO<ModalityTypeDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="20", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue=entity_id, required=false) String field) throws Exception {
		
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
						
		// validate
		validator.validateSort(sort);
		validator.validateSortField(field, ModalityType.class, entity_name);
		
		// set processor params
		processor.setCriteria(criteria);
		processor.setPageCriteria(sort, field, page, size);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
				
		// process data request
		return processor.process();
	}
	
	/**
	 * Get modality type and modalities allocation
	 * @author luca
	 * @category custom get REST method
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="allocation", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody ModalityTypeIntervalAllocationDTO getAllocation (
			@RequestParam(defaultValue = "current_month_start", required = false) Long from,
			@RequestParam(defaultValue = "current_month_end", required = false) Long to,
			@RequestParam(required = true) Long idmodalitytype) throws Exception {
		
		// set params
		processor_mta.setCriteria(entity_id + "!" + idmodalitytype.toString());
		processor_mta.setPageCriteria("asc", entity_allocation_type_id, 1, 31);

		// create standard specification based on date interval and standard field name
		// get dates
		final Date t1 = DateUtil.getStartOfDay(new Date(from));
		final Date t2 = DateUtil.getEndOfDay(new Date(to));
		DateIntervalSpecification<VModalitytypeAllocation, VModalitytypeAllocation> interval = new DateIntervalSpecification<VModalitytypeAllocation, VModalitytypeAllocation>(null, t1, t2, "scheduledate");
		processor_mta.addAndSpecification(interval);
		
		// find modality and convert to base object dto
		ModalityType modalityType = repo.findOne(idmodalitytype);
		
		// find all modalities belonging to modalityType
		List<Modality> modalityList = repo_m.findByModalityType(modalityType);
		
		// get allocations
		Map<Long, List<ModalityDailyAllocationDTO>> modalityAllocation = new HashMap<Long, List<ModalityDailyAllocationDTO>>();
		for (Modality modality : modalityList) {
			List<VModalityAllocation> modalityDailyAllocation = repo_mda.findByIdmodalityAndScheduledateBetween(modality.getIdmodality(), new Date(from), new Date(to));
			List<ModalityDailyAllocationDTO> allocationDTO = new ArrayList<ModalityDailyAllocationDTO>();
			for (VModalityAllocation allocation : modalityDailyAllocation) {
				allocationDTO.add(rdDozerMapper.map(allocation, ModalityDailyAllocationDTO.class));
			}
			modalityAllocation.put(modality.getIdmodality(), allocationDTO);
		}
		
		// build dto
		ModalityTypeIntervalAllocationDTO dto = new ModalityTypeIntervalAllocationDTO();
		dto.setFrom(from);
		dto.setTo(to);
		dto.setModalityType(rdDozerMapper.map(modalityType, BaseObjectDTO.class));
		dto.setModalityTypeAllocation(processor_mta.process().getElements());
		dto.setModalityAllocation(modalityAllocation );
		return dto;
	}
	
	/**
	 * Get modality type group with modality children
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
	@RequestMapping(value="modality", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<ModalityGroupDTO> getModalityGroupedByModalityType (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="1000", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue=entity_id, required=false) String field) throws Exception {
		
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
						
		// validate
		validator.validateSort(sort);
		validator.validateSortField(field, ModalityType.class, entity_name);
		
		// set processor params
		processor_mg.setCriteria(criteria);
		processor_mg.setPageCriteria(sort, field, page, size);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
				
		// process data request
		return processor_mg.process();
	}
	
	/**
	 * Add a modality
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
	public @ResponseBody CreateResponseDTO<ModalityTypeDTO> add(@RequestBody ModalityTypeDTO modalityType, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTONullIdd(modalityType, entity_id);
				
		// invoke order creator
		creator.setCreatedUser(getLoggedUser(request));
		creator.setDto(modalityType);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
		
		// process
		return creator.process();
	}
	
	/**
	 * Update a modality
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
	public @ResponseBody UpdateResponseDTO<ModalityTypeDTO> update(@RequestBody ModalityTypeDTO modalityType, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTOIdd(modalityType, entity_name);

		// set updater params
		updater.setSourceDto(modalityType);
		updater.setUpdatedUser(getLoggedUser(request));
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, modalityType.getIdd().toString()));

		// return response
		return updater.process();
	}
	
	/**
	 * Delete a modality 
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
	public @ResponseBody UpdateResponseDTO<ModalityTypeDTO> delete(@RequestBody ModalityTypeDTO modalityType, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
				
		// validate
		validator.validateDTOIdd(modalityType, entity_name);

		// set updater params
		updater.setSourceDto(modalityType);
		updater.setUpdatedUser(getLoggedUser(request));
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, modalityType.getIdd().toString()));

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
