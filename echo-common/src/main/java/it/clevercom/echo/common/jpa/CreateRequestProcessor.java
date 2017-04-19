package it.clevercom.echo.common.jpa;

import org.dozer.DozerBeanMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;

public class CreateRequestProcessor<I extends JpaRepository<E, ?>, E, D> {
	
	private I repository;
	private E entity; // maybe not in use
	private D dto; // maybe not in use
	private DozerBeanMapper mapper;
	private Class<D> dtoClazz;
	private Class<E> entityClazz;
	private String entity_name;
	private String createdUser;
	
	public CreateRequestProcessor(
			// repository that performs create operation
			I repository, 
			// mapper that performs conversion
			DozerBeanMapper mapper,
			// dto class
			Class<D> dtoClazz, 
			// entity class
			Class<E> entityClazz,
			// entity friendly name
			String entity_name,
			 // user that create record
			String createdUser,
			// dto to persist
			D dto) {  
		
		super();
		// repository
		this.repository = repository;
		// mapper
		this.mapper = mapper;
		// clazzez
		this.dtoClazz = dtoClazz;
		this.entityClazz = entityClazz;
		// entity name
		this.entity_name = entity_name;
		// created user
		this.createdUser = createdUser;
		
	}
	
	public CreateResponseDTO<D> process () {
		// map
		E entity = mapper.map(dto, entityClazz);
		
//		// add technical field
//		entity.setUserupdate(createdUser);
//		
//		// save and map to out dto
//		entity = repo.saveAndFlush(entity);
//		appSetting = rdDozerMapper.map(entity, AppSettingDTO.class);
//		
//		// create standard response
//		CreateResponseDTO<AppSettingDTO> response = new CreateResponseDTO<AppSettingDTO>();
//		response.setEntityName(AppSetting_rd_Controller.entity_name);
//		response.setMessage(MessageFormat.format(env.getProperty("echo.api.crud.saved"), AppSetting_rd_Controller.entity_name));
//		List<AppSettingDTO> appSettingDTOs = new ArrayList<AppSettingDTO>();
//		appSettingDTOs.add(appSetting);
//		response.setNewValue(appSettingDTOs);
//		
//		// return standard response
//		return response;
		return null;
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
	 * @return the entity
	 */
	public E getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(E entity) {
		this.entity = entity;
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