package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.clevercom.echo.common.dto.AbstractEchoDTO;

@JsonIgnoreProperties({"created","updated","userUpdate","active","idd"})
public class ServiceDTO extends AbstractEchoDTO implements Serializable {
	private static final long serialVersionUID = -1545199145120818140L;
	
	private Long idService;
	private String description;
	private Long prepCode;
	private Long duration;
	private Boolean schedulable;
	
	// objects
	private BodyApparatusDTO bodyApparatus;
	private ModalityTypeDTO modalityType;
	
	// transient attributes
	private Date created;
	private Date updated;
	private String userUpdate;
	private Boolean active;
	
	public ServiceDTO() {
		super();
	}

	public ServiceDTO(Long idService, Date created, Date updated, String userUpdate, boolean active) {
		this.idService = idService;
		this.created = created;
		this.updated = updated;
		this.userUpdate = userUpdate;
		this.active = active;
	}

	/**
	 * @return the idService
	 */
	public Long getIdService() {
		return idService;
	}

	/**
	 * @param idService the idService to set
	 */
	public void setIdService(Long idService) {
		this.idService = idService;
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
	 * @return the prepCode
	 */
	public Long getPrepCode() {
		return prepCode;
	}

	/**
	 * @param prepCode the prepCode to set
	 */
	public void setPrepCode(Long prepCode) {
		this.prepCode = prepCode;
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
	 * @return the userUpdate
	 */
	public String getUserUpdate() {
		return userUpdate;
	}

	/**
	 * @param userUpdate the userUpdate to set
	 */
	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
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
		return idService;
	}
}
