package it.clevercom.echo.tm.controller;

import java.text.MessageFormat;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.clevercom.echo.common.component.Validator;
import it.clevercom.echo.common.controller.EchoController;
import it.clevercom.echo.common.dto.PatientDTO;
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.jpa.CreateRequestProcessor;
import it.clevercom.echo.common.jpa.CriteriaRequestProcessor;
import it.clevercom.echo.common.jpa.UpdateRequestProcessor;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.tm.model.entity.Patient;
import it.clevercom.echo.tm.repository.Patient_tm_Repository;

@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.tm.properties")
@RestController
@RequestMapping("tm/patient")
@ComponentScan("it.clevercom.echo.common.component")
public class Patient_tm_Controller extends EchoController{
	
		@Autowired
		private Environment env;

		@Autowired
		private Patient_tm_Repository repo;

//		@Autowired
//		private IPat repo_pc;

		@Autowired
		private DozerBeanMapper rdDozerMapper;
		
		@Autowired
		private Validator validator;
		
		@PersistenceContext(unitName="rdPU")
		protected EntityManager em;

		// crud processors
		private CriteriaRequestProcessor<Patient_tm_Repository, Patient, PatientDTO> processor;
		private CreateRequestProcessor<Patient_tm_Repository, Patient, PatientDTO> creator;
		private UpdateRequestProcessor<Patient_tm_Repository, Patient, PatientDTO> updater;

		private final Logger logger = Logger.getLogger(this.getClass());

		// used to bind it in exception message
		public static final String entity_name = "Patient";
		public static final String entity_id = "idpatient";
		public static final String entity_pc_id = "idpatientcodingactor";
		
		/**
		 * 
		 */
		@PostConstruct
		public void init() {
			// construct creator
			creator = new CreateRequestProcessor<Patient_tm_Repository, Patient, PatientDTO>(repo, rdDozerMapper, Patient.class, entity_name, env, em);
			// construct updater
			updater = new UpdateRequestProcessor<Patient_tm_Repository, Patient, PatientDTO>(repo, rdDozerMapper, entity_name, entity_id, env, em);
			// costruct processor
			processor = new CriteriaRequestProcessor<Patient_tm_Repository, Patient, PatientDTO>(repo, rdDozerMapper, PatientDTO.class, entity_name, env);
		}

		/**
		 * Get patient by id
		 * @param id
		 * @return
		 * @throws Exception
		 */
		@Transactional("tmTm")
		@RequestMapping(value = "/{id}", method = RequestMethod.GET)
		@PreAuthorize("hasAnyRole('ROLE_TM_USER','ROLE_TM_ADMIN')")
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

//		/**
//		 * Get patient by external code with pagination
//		 * @param extcode
//		 * @param page
//		 * @param size
//		 * @param sort
//		 * @param field
//		 * @return
//		 * @throws Exception
//		 */
//		@Transactional("tmTm")
//		@RequestMapping(value = "/extcode/{extcode}", method = RequestMethod.GET)
//		@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
//		@Loggable
//		public @ResponseBody PagedDTO<PatientCodingActorDTO> getByExternalCode(
//				@PathVariable String extcode,
//				@RequestParam(defaultValue = "1", required = false) int page,
//				@RequestParam(defaultValue = "15", required = false) int size,
//				@RequestParam(defaultValue = "asc", required = false) String sort,
//				@RequestParam(defaultValue = entity_pc_id, required = false) String field) throws Exception {
//			
//			// create paged request
//			PageRequest request = PageRequestFactory.getPageRequest(sort, field, page, size);
//			
//			// find with custom method
//			List<PatientCodingActor> patientCodingActorList = repo_pc.findByExternalcode(extcode, request);
//
//			// check if entity has been found
//			if (patientCodingActorList.size() == 0) {
//				logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_pc_id, extcode.toString()));
//				throw new RecordNotFoundException(entity_name, entity_id, extcode);
//			}
//
//			// map output
//			List<PatientCodingActorDTO> patientCodingActorDTOList = new ArrayList<PatientCodingActorDTO>();
//			for (PatientCodingActor patientCodingActor : patientCodingActorList) {
//				Patient patient = patientCodingActor.getPatient();
//				CodingActor codingactor = patientCodingActor.getCodingActor();
//				
//				patientCodingActorDTOList.add(new PatientCodingActorDTO(
//													rdDozerMapper.map(patient, PatientDTO.class), 
//													rdDozerMapper.map(codingactor, BaseObjectDTO.class)));
//			}
//
//			// log info
//			logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.returning.response"), entity_name, entity_pc_id, extcode.toString()));
//			
//			// assembly dto
//			long totalCount = repo_pc.countByExternalcode(extcode);
//			return ResponseFactory.getPagedDTO(patientCodingActorDTOList, size, page, (int)Math.ceil(((double) totalCount) / ((double) size)), totalCount);
//		}

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
		@Transactional("tmTm")
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

			// create processor
			CriteriaRequestProcessor<Patient_tm_Repository, Patient, PatientDTO> rp = new CriteriaRequestProcessor<Patient_tm_Repository, Patient, PatientDTO>(repo, rdDozerMapper, PatientDTO.class,	entity_name, criteria, sort, field,	page, size,	env);
			
			// log info
			logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
			
			// process data request
			return rp.process();
		}

		/**
		 * Add patient 
		 * @param patient
		 * @param request
		 * @return
		 * @throws Exception
		 */
		@Transactional("tmTm")
		@RequestMapping(method = RequestMethod.POST)
		@PreAuthorize("hasAnyRole('ROLE_TM_USER','ROLE_TM_ADMIN')")
		@Loggable
		public @ResponseBody CreateResponseDTO<PatientDTO> add(@RequestBody PatientDTO patient, HttpServletRequest request)	throws Exception {
			// log info
			logger.info(env.getProperty("echo.api.crud.logs.validating"));
			
			// validate
			validator.validateDTONullIdd(patient, entity_id);
					
			// create the processor
			CreateRequestProcessor<Patient_tm_Repository, Patient, PatientDTO> rp = new CreateRequestProcessor<Patient_tm_Repository, Patient, PatientDTO>(repo, rdDozerMapper, Patient.class, entity_name,	getLoggedUser(request),	patient, env);
			
			// log info
			logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
			
			// process
			return rp.process();
		}

		/**
		 * Update patient
		 * @param patient
		 * @param request
		 * @return
		 * @throws Exception
		 */
		@Transactional("tmTm")
		@RequestMapping(method = RequestMethod.PUT)
		@PreAuthorize("hasAnyRole('ROLE_TM_USER','ROLE_TM_ADMIN')")
		@Loggable
		public @ResponseBody UpdateResponseDTO<PatientDTO> update(@RequestBody PatientDTO patient, HttpServletRequest request) throws Exception {
			// log info
			logger.info(env.getProperty("echo.api.crud.logs.validating"));
			
			// validate
			validator.validateDTOIdd(patient, entity_name);

			// create processor
			UpdateRequestProcessor<Patient_tm_Repository, Patient, PatientDTO> rp = new UpdateRequestProcessor<Patient_tm_Repository, Patient, PatientDTO>(repo, rdDozerMapper,	entity_name, entity_id, getLoggedUser(request),	patient, env);
			
			// log info
			logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, patient.getIdd().toString()));

			// return response
			return rp.process();
		}

		/**
		 * Delete patient
		 * @return
		 */
		@Transactional("tmTm")
		@RequestMapping(method = RequestMethod.DELETE)
		@PreAuthorize("hasAnyRole('ROLE_TM_USER','ROLE_TM_ADMIN')")
		@Loggable
		public @ResponseBody UpdateResponseDTO<PatientDTO> delete(@RequestBody PatientDTO patient, HttpServletRequest request) throws Exception {
			// log info
			logger.info(env.getProperty("echo.api.crud.logs.validating"));
			
			// validate
			validator.validateDTOIdd(patient, entity_name);
		
			// create processor
			UpdateRequestProcessor<Patient_tm_Repository, Patient, PatientDTO> rp = new UpdateRequestProcessor<Patient_tm_Repository, Patient, PatientDTO>(repo, rdDozerMapper,	entity_name, entity_id, getLoggedUser(request),	patient, env);
			
			// log info
			logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, patient.getIdd().toString()));
		
			// return response
			return rp.enable(false);
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
