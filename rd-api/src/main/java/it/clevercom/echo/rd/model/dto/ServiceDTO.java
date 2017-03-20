package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"created","updated","userupdate","active"})
public class ServiceDTO implements Serializable {
	private static final long serialVersionUID = -1545199145120818140L;
	
	private Long idservice;
	private String description;
	private Long prepcode;
	private Long duration;
	private Boolean schedulable;
	
	// objects
	private BodyApparatusDTO bodyApparatus;
	private ModalityTypeDTO modalityType;
	
	// transient attributes
	private Date created;
	private Date updated;
	private String userupdate;
	private Boolean active;
	
	public ServiceDTO() {
		super();
	}

	public ServiceDTO(Long idservice, Date created, Date updated, String userupdate, boolean active) {
		this.idservice = idservice;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
	}

	/**
	 * @return the idservice
	 */
	public long getIdservice() {
		return idservice;
	}

	/**
	 * @param idservice the idservice to set
	 */
	public void setIdservice(Long idservice) {
		this.idservice = idservice;
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
	 * @return the prepcode
	 */
	public Long getPrepcode() {
		return prepcode;
	}

	/**
	 * @param prepcode the prepcode to set
	 */
	public void setPrepcode(Long prepcode) {
		this.prepcode = prepcode;
	}

	/**
	 * @return the duration
	 */
	public Long getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Long duration) {
		this.duration = duration;
	}

	/**
	 * @return the schedulable
	 */
	public Boolean getSchedulable() {
		return schedulable;
	}

	/**
	 * @param schedulable the schedulable to set
	 */
	public void setSchedulable(Boolean schedulable) {
		this.schedulable = schedulable;
	}

	/**
	 * @return the bodyApparatus
	 */
	public BodyApparatusDTO getBodyApparatus() {
		return bodyApparatus;
	}

	/**
	 * @param bodyApparatus the bodyApparatus to set
	 */
	public void setBodyApparatus(BodyApparatusDTO bodyApparatus) {
		this.bodyApparatus = bodyApparatus;
	}

	/**
	 * @return the modalityType
	 */
	public ModalityTypeDTO getModalityType() {
		return modalityType;
	}

	/**
	 * @param modalityType the modalityType to set
	 */
	public void setModalityType(ModalityTypeDTO modalityType) {
		this.modalityType = modalityType;
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
	public Boolean isActive() {
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
}
