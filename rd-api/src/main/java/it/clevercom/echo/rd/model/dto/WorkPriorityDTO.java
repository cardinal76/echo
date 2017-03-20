package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;

public class WorkPriorityDTO implements Serializable {
	private static final long serialVersionUID = 1525861965899783971L;

	private Long idworkpriority;
	private String code;
	private String description;
	private Date created;
	private Date updated;
	private String userupdate;
	private Boolean active;

	public WorkPriorityDTO() {
	}

	public WorkPriorityDTO(String code, Date created, Date updated, String userupdate, Boolean active) {
		this.code = code;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
	}

	/**
	 * @return the idworkpriority
	 */
	public Long getIdworkpriority() {
		return idworkpriority;
	}

	/**
	 * @param idworkpriority
	 *            the idworkpriority to set
	 */
	public void setIdworkpriority(Long idworkpriority) {
		this.idworkpriority = idworkpriority;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created
	 *            the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the updated
	 */
	public Date getUpdated() {
		return updated;
	}

	/**
	 * @param updated
	 *            the updated to set
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	/**
	 * @return the userupdate
	 */
	public String getUserupdate() {
		return userupdate;
	}

	/**
	 * @param userupdate
	 *            the userupdate to set
	 */
	public void setUserupdate(String userupdate) {
		this.userupdate = userupdate;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}
}
