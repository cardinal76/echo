package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
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
import it.clevercom.echo.common.jpa.factory.PageRequestFactory;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.common.model.factory.ResponseFactory;
import it.clevercom.echo.rd.component.Validator;
import it.clevercom.echo.rd.model.dto.BaseObjectDTO;
import it.clevercom.echo.rd.model.dto.PatientCodingActorDTO;
import it.clevercom.echo.rd.model.dto.PatientDTO;
import it.clevercom.echo.rd.model.entity.CodingActor;
import it.clevercom.echo.rd.model.entity.Patient;
import it.clevercom.echo.rd.model.entity.PatientCodingActor;
import it.clevercom.echo.rd.repository.IPatientCodingActor_rd_Repository;
import it.clevercom.echo.rd.repository.IPatient_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/assets/patient")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Patient Controller
 * @author luca
 */

public class Patient_rd_Controller extends EchoController {

	@Autowired
	private Environment env;

	@Autowired
	private IPatient_rd_Repository repo;

	@Autowired
	private IPatientCodingActor_rd_Repository repo_pc;

	@Autowired
	private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	@PersistenceContext(unitName="rdPU")
	protected EntityManager em;

	private final Logger logger = Logger.getLogger(this.getClass());

	// used to bind it in exception message
	public static final String entity_name = "Patient";
	public static final String entity_id = "idpatient";
	public static final String entity_pc_id = "idpatientcodingactor";

	/**
	 * Get patient by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PatientDTO get(@PathVariable Long id) throws Exception {
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting"), entity_name, entity_id, id.toString()));
		
		// find entity
		Patient entity = repo.findOne(id);
		
		// check if entity has been found
		if (entity == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.returning.response"), entity_name, entity_id, id.toString()));
		return rdDozerMapper.map(entity, PatientDTO.class);
	}

	/**
	 * Get patient by external code with pagination
	 * @param extcode
	 * @param page
	 * @param size
	 * @param sort
	 * @param field
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value = "/extcode/{extcode}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<PatientCodingActorDTO> getByExternalCode(
			@PathVariable String extcode,
			@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam(defaultValue = "15", required = false) int size,
			@RequestParam(defaultValue = "asc", required = false) String sort,
			@RequestParam(defaultValue = entity_pc_id, required = false) String field) throws Exception {
		
		// create paged request
		PageRequest request = PageRequestFactory.getPageRequest(sort, field, page, size);
		
		// find with custom method
		List<PatientCodingActor> patientCodingActorList = repo_pc.findByExternalcode(extcode, request);

		// check if entity has been found
		if (patientCodingActorList.size() == 0) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_pc_id, extcode.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, extcode);
		}

		// map output
		List<PatientCodingActorDTO> patientCodingActorDTOList = new ArrayList<PatientCodingActorDTO>();
		for (PatientCodingActor patientCodingActor : patientCodingActorList) {
			Patient patient = patientCodingActor.getPatient();
			CodingActor codingactor = patientCodingActor.getCodingActor();
			
			patientCodingActorDTOList.add(new PatientCodingActorDTO(
												rdDozerMapper.map(patient, PatientDTO.class), 
												rdDozerMapper.map(codingactor, BaseObjectDTO.class)));
		}

		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.returning.response"), entity_name, entity_pc_id, extcode.toString()));
		
		// assembly dto
		long totalCount = repo_pc.countByExternalcode(extcode);
		return ResponseFactory.getPagedDTO(patientCodingActorDTOList, size, page, (int)Math.ceil(((double) totalCount) / ((double) size)), totalCount);
	}

	/**
	 * Get patient by criteria with pagination
	 * @param criteria
	 * @param page
	 * @param size
	 * @param sort
	 * @param field
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value = "", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<PatientDTO> getByCriteria(
			@RequestParam(defaultValue = "null", required = false) String criteria,
			@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam(defaultValue = "15", required = false) int size,
			@RequestParam(defaultValue = "asc", required = false) String sort,
			@RequestParam(defaultValue = entity_id, required = false) String field) throws Exception {
		
		// check enum string params
		validator.validateSort(sort);
		validator.validateSortField(field, Patient.class, entity_name);

		// set processor params
		CriteriaRequestProcessor<IPatient_rd_Repository, Patient, PatientDTO> processor = getProcessor();
		processor.setCriteria(criteria);
		processor.setPageCriteria(sort, field, page, size);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
		
		// process data request
		return processor.process();
	}

	/**
	 * Add patient 
	 * @param patient
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<PatientDTO> add(@RequestBody PatientDTO patient, HttpServletRequest request)	throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTONullIdd(patient, entity_id);
				
		// invoke order creator
		CreateRequestProcessor<IPatient_rd_Repository, Patient, PatientDTO> creator = getCreator();
		creator.setCreatedUser(getLoggedUser(request));
		creator.setDto(patient);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
		
		// process
		return creator.process();
	}

	/**
	 * Update patient
	 * @param patient
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<PatientDTO> update(@RequestBody PatientDTO patient, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTOIdd(patient, entity_name);

		// set updater params
		UpdateRequestProcessor<IPatient_rd_Repository, Patient, PatientDTO> updater = getUpdater();
		updater.setSourceDto(patient);
		updater.setUpdatedUser(getLoggedUser(request));
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, patient.getIdd().toString()));

		// return response
		return updater.process();
	}

	/**
	 * Delete patient
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<PatientDTO> delete(@RequestBody PatientDTO patient, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTOIdd(patient, entity_name);
	
		// set updater params
		UpdateRequestProcessor<IPatient_rd_Repository, Patient, PatientDTO> updater = getUpdater();
		updater.setSourceDto(patient);
		updater.setUpdatedUser(getLoggedUser(request));
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, patient.getIdd().toString()));
	
		// return response
		return updater.enable(false);
	}

	@Override
	protected CreateRequestProcessor<IPatient_rd_Repository, Patient, PatientDTO> getCreator() {
		// TODO Auto-generated method stub
		return new CreateRequestProcessor<IPatient_rd_Repository, Patient, PatientDTO>(repo, rdDozerMapper, Patient.class, entity_name, env, em);
	}

	@Override
	protected UpdateRequestProcessor<IPatient_rd_Repository, Patient, PatientDTO> getUpdater() {
		// TODO Auto-generated method stub
		return new UpdateRequestProcessor<IPatient_rd_Repository, Patient, PatientDTO>(repo, rdDozerMapper, entity_name, entity_id, env, em);
	}

	@Override
	protected CriteriaRequestProcessor<IPatient_rd_Repository, Patient, PatientDTO> getProcessor() {
		// TODO Auto-generated method stub
		return new CriteriaRequestProcessor<IPatient_rd_Repository, Patient, PatientDTO>(repo, rdDozerMapper, PatientDTO.class, entity_name, env);
	}
}
