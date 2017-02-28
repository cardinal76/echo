package it.clevercom.echo.rd.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"created","updated","userupdate","active"})
public class PagedDTO <T> {
	private List<T> elements;
	private int pageSize;
	private int currentPage;
	private int totalPages;
	private long totalElements;
	
	/**
	 * @return the elements
	 */
	public List<T> getElements() {
		return elements;
	}
	
	/**
	 * @param elements the elements to set
	 */
	public void setElements(List<T> elements) {
		this.elements = elements;
	}
	
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return totalPages;
	}
	
	/**
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return the totalElements
	 */
	public long getTotalElements() {
		return totalElements;
	}

	/**
	 * @param totalElements the totalElements to set
	 */
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
}
