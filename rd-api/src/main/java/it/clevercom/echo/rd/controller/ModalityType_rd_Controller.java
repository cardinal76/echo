package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;

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
import it.clevercom.echo.rd.model.dto.ModalityTypeDTO;
import it.clevercom.echo.rd.model.entity.ModalityType;
import it.clevercom.echo.rd.repository.IModalityType_rd_Repository;

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
    private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	public static final String entity_name = "ModalityType";
	public static final String entity_id = "idmodalitytype";
	
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
		
		// create the processor
		CriteriaRequestProcessor<IModalityType_rd_Repository, ModalityType, ModalityTypeDTO> rp = 
				new CriteriaRequestProcessor<IModalityType_rd_Repository, ModalityType, ModalityTypeDTO>(repo, 
						rdDozerMapper, 
						ModalityTypeDTO.class, 
						entity_name, 
						criteria, 
						sort, 
						field, 
						page, 
						size,
						env);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
				
		// process data request
		return rp.process();
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
	public @ResponseBody CreateResponseDTO<ModalityTypeDTO> add(@RequestBody ModalityTypeDTO modalityTpye, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTONullIdd(modalityTpye, entity_id);
				
		// create the processor
		CreateRequestProcessor<IModalityType_rd_Repository, ModalityType, ModalityTypeDTO> rp = 
				new CreateRequestProcessor<IModalityType_rd_Repository, ModalityType, ModalityTypeDTO>(repo, 
						rdDozerMapper, 
						ModalityType.class, 
						entity_name, 
						getLoggedUser(request), 
						modalityTpye,
						env);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name));
		
		// process
		return rp.process();
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
	public @ResponseBody UpdateResponseDTO<ModalityTypeDTO> update(@RequestBody ModalityTypeDTO modalityTpye, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTOIdd(modalityTpye, entity_name);

		// create processor
		UpdateRequestProcessor<IModalityType_rd_Repository, ModalityType, ModalityTypeDTO> rp = 
				new UpdateRequestProcessor<IModalityType_rd_Repository, ModalityType, ModalityTypeDTO>(repo, 
						rdDozerMapper,
						entity_name,
						entity_id,
						getLoggedUser(request), 
						modalityTpye, 
						env);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, modalityTpye.getIdd().toString()));

		// return response
		return rp.process();
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

		// create processor
		UpdateRequestProcessor<IModalityType_rd_Repository, ModalityType, ModalityTypeDTO> rp = 
				new UpdateRequestProcessor<IModalityType_rd_Repository, ModalityType, ModalityTypeDTO>(repo, 
						rdDozerMapper,
						entity_name,
						entity_id,
						getLoggedUser(request), 
						modalityType, 
						env);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, modalityType.getIdd().toString()));

		// return response
		return rp.enable(false);
	}
}
