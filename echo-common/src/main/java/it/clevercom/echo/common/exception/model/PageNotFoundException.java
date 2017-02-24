package it.clevercom.echo.common.exception.model;

/**
 * 
 * @author luca
 * @version 1.1.0
 * Exception class used to map record not found exception
 * 
 */

public class PageNotFoundException extends EchoException {
	private static final long serialVersionUID = 1L;
	
	private String entityName;
	private int page;
	
	public PageNotFoundException(String message) {
		super(message);
	}

	public PageNotFoundException(String entityName, int page) {
		super("no page has been found.");
		this.entityName = entityName;
		this.page = page;
	}

	/**
	 * @return the entityName
	 */
	public String getEntityName() {
		return entityName;
	}

	/**
	 * @param entityName the entityName to set
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
