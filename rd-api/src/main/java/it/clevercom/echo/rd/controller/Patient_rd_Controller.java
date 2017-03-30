package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
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

import it.clevercom.echo.common.exception.model.BadRequestException;
import it.clevercom.echo.common.exception.model.PageNotFoundException;
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.common.util.JwtTokenUtils;
import it.clevercom.echo.rd.model.dto.BaseObjectDTO;
import it.clevercom.echo.rd.model.dto.PagedDTO;
import it.clevercom.echo.rd.model.dto.PatientCodingActorDTO;
import it.clevercom.echo.rd.model.dto.PatientDTO;
import it.clevercom.echo.rd.model.entity.CodingActor;
import it.clevercom.echo.rd.model.entity.Patient;
import it.clevercom.echo.rd.model.entity.PatientCodingActor;
import it.clevercom.echo.rd.model.jpa.helper.SearchCriteria;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationQueryHelper;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationsBuilder;
import it.clevercom.echo.rd.repository.IPatientCodingActor_rd_Repository;
import it.clevercom.echo.rd.repository.IPatient_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/assets/patient")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

public class Patient_rd_Controller {

	@Autowired
	private Environment env;

	@Autowired
	private IPatient_rd_Repository repo;

	@Autowired
	private IPatientCodingActor_rd_Repository repo_pc;

	@Autowired
	private DozerBeanMapper rdDozerMapper;

	@Value("${jwt.token.header}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtils tokenUtils;

	private final Logger logger = Logger.getLogger(this.getClass());

	// used to bind it in exception message
	private static String entity = "Patient";

	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PatientDTO get(@PathVariable Long id) throws Exception {
		Patient entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(Patient_rd_Controller.entity, id.toString());
		return rdDozerMapper.map(entity, PatientDTO.class);
	}

	/**
	 * @param extcode
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value = "/extcode/{extcode}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<PatientCodingActorDTO> getByExternalCode(@PathVariable String extcode,
																		   @RequestParam(defaultValue = "1", required = false) int page,
																		   @RequestParam(defaultValue = "15", required = false) int size,
																		   @RequestParam(defaultValue = "asc", required = false) String sort,
																		   @RequestParam(defaultValue = "idpatientcodingactor", required = false) String field) throws Exception {
		
		// create paged request
		PageRequest request = null;

		if (sort.equals("asc")) {
			request = new PageRequest(page - 1, size, Direction.ASC, field);
		} else if (sort.equals("desc")) {
			request = new PageRequest(page - 1, size, Direction.DESC, field);
		} else {
			throw new BadRequestException(env.getProperty("echo.api.exception.search.sort.wrongsortparam"));
		}
		
		List<PatientCodingActor> patientCodingActorList = repo_pc.findByExternalcode(extcode, request);

		if (patientCodingActorList.size() == 0)
			throw new RecordNotFoundException(Patient_rd_Controller.entity, extcode);

		List<PatientCodingActorDTO> patientCodingActorDTOList = new ArrayList<PatientCodingActorDTO>();
		for (PatientCodingActor patientCodingActor : patientCodingActorList) {
			Patient patient = patientCodingActor.getPatient();
			CodingActor codingactor = patientCodingActor.getCodingActor();
			
			patientCodingActorDTOList.add(new PatientCodingActorDTO(
												rdDozerMapper.map(patient, PatientDTO.class), 
												rdDozerMapper.map(codingactor, BaseObjectDTO.class)));
		}

		// assembly dto
		PagedDTO<PatientCodingActorDTO> dto = new PagedDTO<PatientCodingActorDTO>();
		dto.setElements(patientCodingActorDTOList);
		dto.setPageSize(size);
		dto.setCurrentPage(page);
		// get total count
		long totalCount = repo_pc.countByExternalcode(extcode);
		dto.setTotalPages((int)Math.ceil(((double) totalCount) / ((double) size)));
		dto.setTotalElements(totalCount);

		return dto;
	}

	/**
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
			@RequestParam(defaultValue = "idpatient", required = false) String field) throws Exception {
		
		// create paged request
		PageRequest request = null;

		if (sort.equals("asc")) {
			request = new PageRequest(page - 1, size, Direction.ASC, field);
		} else if (sort.equals("desc")) {
			request = new PageRequest(page - 1, size, Direction.DESC, field);
		} else {
			throw new BadRequestException(env.getProperty("echo.api.exception.search.sort.wrongsortparam"));
		}

		// create predicate if criteria is not null
		Page<Patient> rs = null;

		if (!criteria.equals("null")) {
			SpecificationsBuilder<Patient, SpecificationQueryHelper<Patient>> builder = new SpecificationsBuilder<Patient, SpecificationQueryHelper<Patient>>();
			Pattern pattern = Pattern.compile(SearchCriteria.pattern);
			Matcher matcher = pattern.matcher(criteria + ",");
			while (matcher.find()) {
				builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
			}
			Specification<Patient> spec = builder.build();

			// obtain records
			rs = repo.findAll(spec, request);
		} else {
			rs = repo.findAll(request);
		}

		int totalPages = rs.getTotalPages();
		long totalElements = rs.getTotalElements();
		List<Patient> entities = rs.getContent();

		if (entities.size() == 0)
			throw new PageNotFoundException(Patient_rd_Controller.entity, page);

		// map list
		List<PatientDTO> patientDTOList = new ArrayList<PatientDTO>();
		for (Patient s : entities) {
			patientDTOList.add(rdDozerMapper.map(s, PatientDTO.class));
		}

		// assembly dto
		PagedDTO<PatientDTO> dto = new PagedDTO<PatientDTO>();
		dto.setElements(patientDTOList);
		dto.setPageSize(size);
		dto.setCurrentPage(page);
		dto.setTotalPages(totalPages);
		dto.setTotalElements(totalElements);
		return dto;
	}

	/**
	 * @param patient
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<PatientDTO> add(@RequestBody PatientDTO patient, HttpServletRequest request)
			throws Exception {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);

		// map
		Patient entity = rdDozerMapper.map(patient, Patient.class);

		// add technical field
		entity.setUserupdate(username);

		// save and map to out dto
		entity = repo.saveAndFlush(entity);
		// TODO map entity instead of set ID
		// patient = rdDozerMapper.map(entity, PatientDTO.class);
		patient.setIdPatient(entity.getIdpatient());

		// create standard response
		CreateResponseDTO<PatientDTO> response = new CreateResponseDTO<PatientDTO>();
		response.setEntityName(Patient_rd_Controller.entity);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), Patient_rd_Controller.entity));
		List<PatientDTO> patientDTOs = new ArrayList<PatientDTO>();
		patientDTOs.add(patient.buildExtendedObject());
		response.setNewValue(patientDTOs);

		// return standard response
		return response;
	}

	/**
	 * @param patient
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<PatientDTO> update(@RequestBody PatientDTO patient,
			HttpServletRequest request) throws Exception {
		// get user info
		String authToken = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(authToken);

		// if an id is not present throw bad request
		if (patient.getIdPatient() == null)
			throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.missing.id"),
					Patient_rd_Controller.entity));

		// find entity to update (oldValue)
		Patient oldValueEntity = repo.findOne(patient.getIdPatient());
		// if an entity with given id is not found in DB throw record not found
		if (oldValueEntity == null)
			throw new RecordNotFoundException(Patient_rd_Controller.entity, patient.getIdPatient().toString());
		// get created date
		Date created = oldValueEntity.getCreated();
		// map old value to a dto
		PatientDTO oldValueDTO = rdDozerMapper.map(oldValueEntity, PatientDTO.class);

		// begin update of oldValue
		rdDozerMapper.map(patient, oldValueEntity);

		// add technical field
		oldValueEntity.setUserupdate(username);
		oldValueEntity.setUpdated(new Date());
		oldValueEntity.setCreated(created);

		// save and map to out dto
		Patient newValueEntity = repo.saveAndFlush(oldValueEntity);
		// TODO map newValueDTO instead of using input patient
		// PatientDTO newValueDTO = rdDozerMapper.map(newValueEntity, PatientDTO.class);

		// create standard response
		UpdateResponseDTO<PatientDTO> response = new UpdateResponseDTO<PatientDTO>();
		response.setEntityName(Patient_rd_Controller.entity);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), Patient_rd_Controller.entity));
		// add new dtos values
		List<PatientDTO> newPatientDTOs = new ArrayList<PatientDTO>();
		newPatientDTOs.add(patient);
		// newPatientDTOs.add(newValueDTO);
		response.setNewValue(newPatientDTOs);
		// add old dtos values
		List<PatientDTO> oldAppSettingDTOs = new ArrayList<PatientDTO>();
		oldAppSettingDTOs.add(oldValueDTO);
		response.setOldValue(oldAppSettingDTOs);

		// return response
		return response;
	}

	/**
	 * @return
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody String delete() {
		return "patient";
	}
}
