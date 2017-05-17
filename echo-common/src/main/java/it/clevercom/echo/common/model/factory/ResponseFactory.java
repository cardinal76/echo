package it.clevercom.echo.common.model.factory;

import java.util.ArrayList;
import java.util.List;

import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;

public class ResponseFactory {
	/**
	 * 
	 * @param dtoList
	 * @param size
	 * @param page
	 * @param totalPages
	 * @param totalElements
	 * @return
	 */
	public static <T> PagedDTO<T> getPagedDTO(List<T> dtoList, int size, int page, int totalPages, long totalElements) {
		PagedDTO<T> dto = new PagedDTO<T>();
		dto.setCurrentPage(page);
		dto.setElements(dtoList);
		dto.setPageSize(size);
		dto.setTotalElements(totalElements);
		dto.setTotalPages(totalPages);
		return dto;
	}
	
	/**
	 * 
	 * @param dto
	 * @param entity_name
	 * @param message
	 * @return
	 */
	public static <T> CreateResponseDTO<T> getCreateResponseDTO(T dto, String entity_name, String message) {	
		// create standard response
		CreateResponseDTO<T> response = new CreateResponseDTO<T>();
		response.setEntityName(entity_name);
		response.setMessage(message);
		List<T> dtoS = new ArrayList<T>();
		dtoS.add(dto);
		response.setNewValue(dtoS);
		return response;
	}
	
	/**
	 * 
	 * @return
	 */
	public static <T> UpdateResponseDTO<T> getUpdateResponseDTO(T oldValueDTO, T newValueDTO, String entityName, String message) {
		// create standard response
		UpdateResponseDTO<T> response = new UpdateResponseDTO<T>();
		
		response.setMessage(message);
		response.setEntityName(entityName);
		
		// add new dtos values
		List<T> newDTOs = new ArrayList<T>();
		newDTOs.add(newValueDTO);
		response.setNewValue(newDTOs);
		
		// add old dtos values
		List<T> oldDTOs = new ArrayList<T>();
		oldDTOs.add(oldValueDTO);
		response.setOldValue(oldDTOs);
		
		return response;
	}
}
