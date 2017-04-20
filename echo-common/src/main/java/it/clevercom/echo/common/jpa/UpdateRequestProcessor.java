package it.clevercom.echo.common.jpa;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.common.exception.model.BadRequestException;
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.jpa.entity.AbstractJpaEchoEntity;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;

public class UpdateRequestProcessor<I extends JpaRepository<E, ?>, E extends AbstractJpaEchoEntity, D> {
	
	private I repository;
	private E entity; // maybe not in use
	private D dto; // maybe not in use
	private DozerBeanMapper mapper;
	private Class<D> dtoClazz;
	private Class<E> entityClazz;
	private String entity_name;
	private String updatedUser;
	private Environment env;

	public UpdateRequestProcessor(
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
			String updatedUser,
			// dto to persist
			D dto,
			Environment env) {  
		
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
		this.updatedUser = updatedUser;
		// set dto
		this.dto = dto;
		// set env
		this.env = env;
	}
	
	public UpdateResponseDTO<D> process () {
		// find entity to update (oldValue)
		E oldValueEntity = repository.findOne(2);
		
		// if an entity with given id is not found in DB throw record not found
		if (oldValueEntity==null) throw new RecordNotFoundException(entity_name, entity_id, citizenship.getIdcitizenship().toString());
		
		// map old value to a dto
		D oldValueDTO = mapper.map(oldValueEntity, dtoClazz);

		// begin update of oldValue
		mapper.map(dto, oldValueEntity);
		
		// add technical field
		oldValueEntity.setUserupdate(updatedUser);
		
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
	}
}