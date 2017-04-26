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
	 * @return the repository
	 */
	public I getRepository() {
		return repository;
	}

	/**
	 * @param repository the repository to set
	 */
	public void setRepository(I repository) {
		this.repository = repository;
	}

	/**
	 * @return the dto
	 */
	public D getDto() {
		return dto;
	}

	/**
	 * @param dto the dto to set
	 */
	public void setDto(D dto) {
		this.dto = dto;
	}

	/**
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * @param mapper the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * @return the dtoClazz
	 */
	public Class<D> getDtoClazz() {
		return dtoClazz;
	}

	/**
	 * @param dtoClazz the dtoClazz to set
	 */
	public void setDtoClazz(Class<D> dtoClazz) {
		this.dtoClazz = dtoClazz;
	}

	/**
	 * @return the entityClazz
	 */
	public Class<E> getEntityClazz() {
		return entityClazz;
	}

	/**
	 * @param entityClazz the entityClazz to set
	 */
	public void setEntityClazz(Class<E> entityClazz) {
		this.entityClazz = entityClazz;
	}

	/**
	 * @return the entity_name
	 */
	public String getEntity_name() {
		return entity_name;
	}

	/**
	 * @param entity_name the entity_name to set
	 */
	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}

	/**
	 * @return the createdUser
	 */
	public String getCreatedUser() {
		return createdUser;
	}

	/**
	 * @param createdUser the createdUser to set
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
}