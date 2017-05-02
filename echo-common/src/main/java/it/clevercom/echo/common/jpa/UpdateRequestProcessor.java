package it.clevercom.echo.common.jpa;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.common.dto.AbstractEchoDTO;
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.jpa.entity.AbstractJpaEchoEntity;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;

public class UpdateRequestProcessor<I extends JpaRepository<E, ?>, E extends AbstractJpaEchoEntity, D extends AbstractEchoDTO> {
	
	private I repository;
	private D dto; // maybe not in use
	private E oldValueEntity;
	private DozerBeanMapper mapper;
	private Class<D> dtoClazz;
	private Class<I> repoClazz;
	private String entity_name;
	private String entity_id;
	private String updatedUser;
	private Environment env;
	private Class<?> idClazz;
	private final Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @param repository
	 * @param mapper
	 * @param entity_name
	 * @param entity_id
	 * @param updatedUser
	 * @param dto
	 * @param env
	 */
	public UpdateRequestProcessor(I repository, DozerBeanMapper mapper, String entity_name, String entity_id, String updatedUser, D dto, Environment env) throws Exception {
		super();
		// repository
		this.repository = repository;
		// mapper
		this.mapper = mapper;
		// entity name
		this.entity_name = entity_name;
		// entity name
		this.entity_id = entity_id;
		// created user
		this.updatedUser = updatedUser;
		// set dto
		this.dto = dto;
		// set env
		this.env = env;
		
		// clazzez
		this.dtoClazz = (Class<D>) dto.getClass();
		this.repoClazz = (Class<I>) repository.getClass();
		this.idClazz = dto.getIdd().getClass();
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public UpdateResponseDTO<D> process() throws Exception {
		// get method with reflection
	    Method method = repoClazz.getMethod("findOne", Serializable.class);
	    
	    // cast id to serializable
	    Serializable id = (Serializable) idClazz.cast(dto.getIdd());
	    
	    // invoke method with reflection and cast result to E
	    this.oldValueEntity = (E) method.invoke(repository, id);
	    
	    // if an entity with given id is not found in DB throw record not found
	    if (oldValueEntity==null) {
	    	logger.error(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
	    	throw new RecordNotFoundException(entity_name, entity_id, dto.getIdd().toString());
	    }
	    
		// map old value to a dto
		D oldValueDTO = mapper.map(oldValueEntity, dtoClazz);
		
		// begin update of oldValue
		mapper.map(dto, oldValueEntity);

		// add technical field
		oldValueEntity.setUserupdate(updatedUser);

		// save and map to out dto
		E newValueEntity = repository.saveAndFlush(oldValueEntity);
		// D newValueDTO = mapper.map(newValueEntity, dtoClazz);
		D newValueDTO = dto;
		
		// create standard response
		UpdateResponseDTO<D> response = new UpdateResponseDTO<D>();
		response.setEntityName(entity_name);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), entity_name));
		
		// add new dtos values
		List<D> newDTOs = new ArrayList<D>();
		newDTOs.add(newValueDTO);
		response.setNewValue(newDTOs);
		
		// add old dtos values
		List<D> oldDTOs = new ArrayList<D>();
		oldDTOs.add(oldValueDTO);
		response.setOldValue(oldDTOs);

		return response;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public E update() throws Exception {
		// get method with reflection
	    Method method = repoClazz.getMethod("findOne", Serializable.class);
	    
	    // cast id to serializable
	    Serializable id = (Serializable) idClazz.cast(dto.getIdd());
	    
	    // invoke method with reflection and cast result to E
	    E oldValueEntity = (E) method.invoke(repository, id);
	    
	    // if an entity with given id is not found in DB throw record not found
	    if (oldValueEntity==null) {
	    	logger.error(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
	    	throw new RecordNotFoundException(entity_name, entity_id, dto.getIdd().toString());
	    }
		
		// map old value to a dto
		D oldValueDTO = mapper.map(oldValueEntity, dtoClazz);
		
		// begin update of oldValue
		mapper.map(dto, oldValueEntity);

		// add technical field
		oldValueEntity.setUserupdate(updatedUser);
		
		// save and map to out dto
		E newValueEntity = repository.saveAndFlush(oldValueEntity);
		
		return newValueEntity;
	}	
	
	/**
	 * @param enabled
	 * @return
	 * @throws Exception
	 */
	public UpdateResponseDTO<D> enable(boolean enabled) throws Exception {
		// get method with reflection
	    Method method = repoClazz.getMethod("findOne", Serializable.class);
	    
	    // cast id to serializable
	    Serializable id = (Serializable) idClazz.cast(dto.getIdd());
	    
	    // invoke method with reflection and cast result to E
	    E oldValueEntity = (E) method.invoke(repository, id);
	    
	    // if an entity with given id is not found in DB throw record not found
	    if (oldValueEntity==null) {
	    	logger.error(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
	    	throw new RecordNotFoundException(entity_name, entity_id, dto.getIdd().toString());
	    }
		
		// map old value to a dto
		D oldValueDTO = mapper.map(oldValueEntity, dtoClazz);
		
		// begin update of oldValue
		mapper.map(dto, oldValueEntity);
		
		// add technical field
		oldValueEntity.setUserupdate(updatedUser);
		oldValueEntity.setActive(enabled);
		
		// save and map to out dto
		E newValueEntity = repository.saveAndFlush(oldValueEntity);
		D newValueDTO = mapper.map(newValueEntity, dtoClazz);
				
		// create standard response
		UpdateResponseDTO<D> response = new UpdateResponseDTO<D>();
		response.setEntityName(entity_name);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), entity_name));
		
		// add new dtos values
		List<D> newDTOs = new ArrayList<D>();
		newDTOs.add(newValueDTO);
		response.setNewValue(newDTOs);
		
		// add old dtos values
		List<D> oldDTOs = new ArrayList<D>();
		oldDTOs.add(oldValueDTO);
		response.setOldValue(oldDTOs);

		return response;
	}
}