package it.clevercom.echo.common.model.factory;

import java.util.List;

import it.clevercom.echo.common.model.dto.response.PagedDTO;

public class PagedDTOFactory {

	public static <T> PagedDTO<T> getPagedDTO(List<T> dtoList, int size, int page, int totalPages, long totalElements) {
		PagedDTO<T> dto = new PagedDTO<T>();
		dto.setCurrentPage(page);
		dto.setElements(dtoList);
		dto.setPageSize(size);
		dto.setTotalElements(totalElements);
		dto.setTotalPages(totalPages);
		return dto;
	}
}
