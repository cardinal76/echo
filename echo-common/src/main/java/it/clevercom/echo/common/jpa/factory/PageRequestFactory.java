package it.clevercom.echo.common.jpa.factory;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

public class PageRequestFactory {	
	/**
	 * Create a page request using params
	 * @param sort
	 * @param field
	 * @param page
	 * @param size
	 * @return
	 */
	public static PageRequest getPageRequest(String sort, String field, int page, int size) {
		PageRequest request = null;
		if (sort.equalsIgnoreCase("asc")) {
			request = new PageRequest(page - 1, size, Direction.ASC, field);
		} else if (sort.equalsIgnoreCase("desc")) {
			request = new PageRequest(page - 1, size, Direction.DESC, field);
		}
		return request;
	}
}
