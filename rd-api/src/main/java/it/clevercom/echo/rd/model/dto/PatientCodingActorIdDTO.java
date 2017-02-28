package it.clevercom.echo.rd.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"created","updated","userupdate","active"})
public class PatientCodingActorIdDTO implements java.io.Serializable {
	private static final long serialVersionUID = -973427458892429114L;

	private Long idpatient;
	private Long idcodingactor;
	private String externalcode;
	private Date created;
	private Date updated;
	private String userupdate;
	private boolean active;

	public PatientCodingActorIdDTO() {
	}

	public PatientCodingActorIdDTO(Date created, Date updated, String userupdate, boolean active) {
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
	}

	/**
	 * @return the idpatient
	 */
	public Long getIdpatient() {
		return idpatient;
	}

	/**
	 * @param idpatient the idpatient to set
	 */
	public void setIdpatient(Long idpatient) {
		this.idpatient = idpatient;
	}

	/**
	 * @return the idcodingactor
	 */
	public Long getIdcodingactor() {
		return idcodingactor;
	}

	/**
	 * @param idcodingactor the idcodingactor to set
	 */
	public void setIdcodingactor(Long idcodingactor) {
		this.idcodingactor = idcodingactor;
	}

	/**
	 * @return the externalcode
	 */
	public String getExternalcode() {
		return externalcode;
	}

	/**
	 * @param externalcode the externalcode to set
	 */
	public void setExternalcode(String externalcode) {
		this.externalcode = externalcode;
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
