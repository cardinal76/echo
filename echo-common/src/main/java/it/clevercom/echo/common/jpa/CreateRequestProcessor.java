package it.clevercom.echo.common.jpa;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.common.jpa.entity.AbstractJpaEchoEntity;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;

public class CreateRequestProcessor<I extends JpaRepository<E, ?>, E extends AbstractJpaEchoEntity, D> {
	
	private I repository;
	private D dto;
	private DozerBeanMapper mapper;
	private Class<D> dtoClazz;
	private Class<E> entityClazz;
	private String entity_name;
	private String createdUser;
	private Environment env;
	private final Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 
	 * @param repository
	 * @param mapper
	 * @param entityClazz
	 * @param entity_name
	 * @param createdUser
	 * @param dto
	 * @param env
	 */
	public CreateRequestProcessor(
			// repository that performs create operation
			I repository, 
			// mapper that performs conversion
			DozerBeanMapper mapper,
			// entity class
			Class<E> entityClazz,
			// entity friendly name
			String entity_name,
			 // user that create record
			String createdUser,
			// dto to persist
			D dto,
			// environment
			Environment env) {  
		
		super();
		// repository
		this.repository = repository;
		// mapper
		this.mapper = mapper;
		// entity name
		this.entity_name = entity_name;
		// created user
		this.createdUser = createdUser;
		// set dto
		this.dto = dto;
		// set env
		this.env = env;
		// clazzez
		this.dtoClazz = (Class<D>) dto.getClass();
		this.entityClazz = entityClazz;
	}
	
	/**
	 * 
	 * @return
	 */
	public CreateResponseDTO<D> process () {
		// map
		E entity = mapper.map(dto, entityClazz);
		
		// add technical field
		entity.setUserupdate(createdUser);
		entity.setActive(true);

		// save and map to out dto
		entity = repository.saveAndFlush(entity);
		dto = mapper.map(entity, dtoClazz);

		// create standard response
		CreateResponseDTO<D> response = new CreateResponseDTO<D>();
		response.setEntityName(this.entity_name);
		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), entity_name));
		List<D> dTOs = new ArrayList<D>();
		dTOs.add(dto);
		response.setNewValue(dTOs);
		
		// return standard response
		return response;
	}
	
	/**
	 * 
	 * @return
	 */
	public E create() {
		// map
		E entity = mapper.map(dto, entityClazz);
		
		// add technical field
		entity.setUserupdate(createdUser);
		entity.setActive(true);

		// save and map to out dto
		entity = repository.saveAndFlush(entity);
		
		return entity;
	}
}