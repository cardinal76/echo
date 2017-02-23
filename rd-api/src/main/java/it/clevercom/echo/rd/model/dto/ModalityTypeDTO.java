package it.clevercom.echo.rd.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"created","updated","userupdate","active"})
public class ModalityTypeDTO implements java.io.Serializable {
	private static final long serialVersionUID = 3406464325790754778L;

	private long idmodalitytype;
	private String type;
	
	// transient attributes
	private Date created;
	private Date updated;
	private String userupdate;
	private boolean active;

	public ModalityTypeDTO() {
	}

	public ModalityTypeDTO(long idmodalitytype, String type, Date created, Date updated, String userupdate,
			boolean active) {
		this.idmodalitytype = idmodalitytype;
		this.type = type;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
	}

	/**
	 * @return the idmodalitytype
	 */
	public long getIdmodalitytype() {
		return idmodalitytype;
	}

	/**
	 * @param idmodalitytype the idmodalitytype to set
	 */
	public void setIdmodalitytype(long idmodalitytype) {
		this.idmodalitytype = idmodalitytype;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
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
	 * @param updated the updated to set
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
	 * @param userupdate the userupdate to set
	 */
	public void setUserupdate(String userupdate) {
		this.userupdate = userupdate;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
