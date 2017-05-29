package it.clevercom.echo.common.jpa;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.MessageFormat;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.common.dto.AbstractEchoDTO;
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.jpa.entity.AbstractJpaEchoEntity;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.common.model.factory.ResponseFactory;

public class UpdateRequestProcessor<I extends JpaRepository<E, ?>, E extends AbstractJpaEchoEntity, D extends AbstractEchoDTO> {
	// update operation objects
	private I repository;
	private D sourceDTO;
	
	// entities
	private E newValueEntity;
	private E oldValueEntity;
	
	// dtos
	private D originDTO;
	private D modifiedDTO;
	
	// log strings
	private String entity_name;
	private String entity_id;
	
	// update user
	private String updatedUser;
	
	// generic types
	private Class<?> idClazz;
	private Class<D> dtoClazz;
	private Class<I> repoClazz;
	
	// system objects
	private Environment env;
	private EntityManager em;
	private final Logger logger = Logger.getLogger(this.getClass());
	private DozerBeanMapper mapper;
	
	/**
	 * 
	 * @param repository
	 * @param mapper
	 * @param entity_name
	 * @param entity_id
	 * @param updatedUser
	 * @param dto
	 * @param env
	 * @deprecated
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
		this.sourceDTO = dto;
		// set env
		this.env = env;
		
		// clazzez
		this.dtoClazz = (Class<D>) dto.getClass();
		this.repoClazz = (Class<I>) repository.getClass();
		this.idClazz = dto.getIdd().getClass();
	}
	
	/**
	 * 
	 * @param repository
	 * @param mapper
	 * @param entity_name
	 * @param entity_id
	 * @param updatedUser
	 * @param sourceDTO
	 * @param env
	 */
	public UpdateRequestProcessor(I repository, DozerBeanMapper mapper, String entity_name, String entity_id, Environment env, EntityManager em) {
		super();
		
		// set repository
		this.repository = repository;
		
		// set entity info
		this.entity_name = entity_name;
		this.entity_id = entity_id;

		// set system objects
		this.mapper = mapper;
		this.env = env;
		this.em = em;
		
		// clazzez
		this.repoClazz = (Class<I>) repository.getClass();
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public UpdateResponseDTO<D> process() throws Exception {
		// save entity
		newValueEntity = update();
		
		// legacy code (should be removed after refactoring)
		if (em!=null) {
			em.refresh(newValueEntity);		
		}
		
		// get new value DTO
		modifiedDTO = mapper.map(newValueEntity, dtoClazz);

		// create standard response
		return ResponseFactory.getUpdateResponseDTO(originDTO, modifiedDTO, entity_name, MessageFormat.format(env.getProperty("echo.api.crud.saved"), entity_name));
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public E update() throws Exception {
		// get method findOne with reflection
	    Method method = repoClazz.getMethod("findOne", Serializable.class);
	    
	    // cast id to serializable
	    Serializable id = (Serializable) idClazz.cast(sourceDTO.getIdd());
	    
	    // invoke method with reflection and cast result to E entity
	    oldValueEntity = (E) method.invoke(repository, id);
	    
	    // if an entity with given id is not found in DB throw record not found
	    if (oldValueEntity==null) {
	    	logger.error(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
	    	throw new RecordNotFoundException(entity_name, entity_id, sourceDTO.getIdd().toString());
	    }
		
		// if original DTO is null map old value entity to originDTO
	    if (originDTO == null) originDTO = mapper.map(oldValueEntity, dtoClazz);
		
		// begin update of oldValueEntity
		mapper.map(sourceDTO, oldValueEntity);

		// add technical field
		oldValueEntity.setUserupdate(updatedUser);
		
		// save and map to out dto
		newValueEntity = repository.saveAndFlush(oldValueEntity);
				
		return newValueEntity;
	}

	/**
	 * @param enabled
	 * @return
	 * @throws Exception
	 */
	public UpdateResponseDTO<D> enable(boolean enabled) throws Exception {
		// get method findOne with reflection
	    Method method = repoClazz.getMethod("findOne", Serializable.class);
	    
	    // cast id to serializable
	    Serializable id = (Serializable) idClazz.cast(sourceDTO.getIdd());
	    
	    // invoke method with reflection and cast result to E entity
	    oldValueEntity = (E) method.invoke(repository, id);
	    
	    // if an entity with given id is not found in DB throw record not found
	    if (oldValueEntity==null) {
	    	logger.error(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
	    	throw new RecordNotFoundException(entity_name, entity_id, sourceDTO.getIdd().toString());
	    }
		
		// if original DTO is null map old value entity to originDTO
	    if (originDTO == null) originDTO = mapper.map(oldValueEntity, dtoClazz);
		
		// begin update of oldValueEntity
		mapper.map(sourceDTO, oldValueEntity);
		
		// add technical field
		oldValueEntity.setUserupdate(updatedUser);
		oldValueEntity.setActive(enabled);
		
		// save and map to out dto
		newValueEntity = repository.saveAndFlush(oldValueEntity);
		modifiedDTO = mapper.map(newValueEntity, dtoClazz);
				
		// create standard response
		return ResponseFactory.getUpdateResponseDTO(originDTO, modifiedDTO, entity_name, MessageFormat.format(env.getProperty("echo.api.crud.saved"), entity_name));
	}

	/**
	 * @return the dto
	 */
	public D getSourceDto() {
		return sourceDTO;
	}

	/**
	 * @param dto the dto to set
	 */
	public void setSourceDto(D dto) {
		this.sourceDTO = dto;
		this.dtoClazz = (Class<D>) dto.getClass();
		this.idClazz = dto.getIdd().getClass();
	}

	/**
	 * @return the updatedUser
	 */
	public String getUpdatedUser() {
		return updatedUser;
	}

	/**
	 * @param updatedUser the updatedUser to set
	 */
	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	/**
	 * @return the originDTO
	 */
	public D getOriginDTO() {
		return originDTO;
	}

	/**
	 * @param originDTO the originDTO to set
	 */
	public void setOriginDTO(D originDTO) {
		this.originDTO = originDTO;
	}
}