package it.clevercom.echo.common.model.factory;

import java.util.ArrayList;
import java.util.List;

import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.PagedDTO;

public class ResponseFactory {

	public static <T> PagedDTO<T> getPagedDTO(List<T> dtoList, int size, int page, int totalPages, long totalElements) {
		PagedDTO<T> dto = new PagedDTO<T>();
		dto.setCurrentPage(page);
		dto.setElements(dtoList);
		dto.setPageSize(size);
		dto.setTotalElements(totalElements);
		dto.setTotalPages(totalPages);
		return dto;
	}
	
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
}
