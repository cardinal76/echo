package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.clevercom.echo.common.dto.AbstractEchoDTO;

@JsonIgnoreProperties({"created","updated","userupdate","active","idd"})
public class MaritalStatusDTO extends AbstractEchoDTO implements Serializable {
	private static final long serialVersionUID = 6465294662072987645L;

	private Long idmaritalstatus;
    private String hl7code;
    private String description;
	
	// transient attributes
	private Date created;
	private Date updated;
	private String userupdate;
	private Boolean active;

	public MaritalStatusDTO() {
	}

	public MaritalStatusDTO(String hl7code, String description, Date created, Date updated, String userupdate,	boolean active) {
		this.hl7code = hl7code;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
	}

	/**
	 * @return the idmaritalstatus
	 */
	public Long getIdmaritalstatus() {
		return idmaritalstatus;
	}

	/**
	 * @param idmaritalstatus the idmaritalstatus to set
	 */
	public void setIdmaritalstatus(Long idmaritalstatus) {
		this.idmaritalstatus = idmaritalstatus;
	}

	/**
	 * @return the hl7code
	 */
	public String getHl7code() {
		return hl7code;
	}

	/**
	 * @param hl7code the hl7code to set
	 */
	public void setHl7code(String hl7code) {
		this.hl7code = hl7code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
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
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public Object getIdd() {
		return getIdmaritalstatus();
	}
}
