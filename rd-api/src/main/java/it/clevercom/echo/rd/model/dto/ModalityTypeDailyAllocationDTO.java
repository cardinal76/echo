package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.clevercom.echo.common.dto.AbstractEchoDTO;

@JsonIgnoreProperties({"created","updated","userupdate","active","idd","id","modalityType"})
public class ModalityTypeDailyAllocationDTO extends AbstractEchoDTO implements Serializable {
	private static final long serialVersionUID = -2971414166225920555L;

	private String id;
    private Long scheduleDate;
	private BaseObjectDTO modalityType;
    private Integer serviceAllocation;
    private Integer serviceExcess;
    private Integer patientAllocation;
    private Integer patientExcess;
    
    // transient attributes
 	private Date created;
 	private Date updated;
 	private String userupdate;
 	private Boolean active;
	
	@Override
	public Object getIdd() {
		return this.getId();
	}

	/**
	 * @return the modality
	 */
	public BaseObjectDTO getModalityType() {
		return modalityType;
	}

	/**
	 * @return the scheduleDate
	 */
	public Long getScheduleDate() {
		return scheduleDate;
	}

	/**
	 * @param scheduleDate the scheduleDate to set
	 */
	public void setScheduleDate(Long scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	/**
	 * @return the serviceAllocation
	 */
	public Integer getServiceAllocation() {
		return serviceAllocation;
	}

	/**
	 * @param serviceAllocation the serviceAllocation to set
	 */
	public void setServiceAllocation(Integer serviceAllocation) {
		this.serviceAllocation = serviceAllocation;
	}

	/**
	 * @return the serviceExcess
	 */
	public Integer getServiceExcess() {
		return serviceExcess;
	}

	/**
	 * @param serviceExcess the serviceExcess to set
	 */
	public void setServiceExcess(Integer serviceExcess) {
		this.serviceExcess = serviceExcess;
	}

	/**
	 * @return the patientAllocation
	 */
	public Integer getPatientAllocation() {
		return patientAllocation;
	}

	/**
	 * @param patientAllocation the patientAllocation to set
	 */
	public void setPatientAllocation(Integer patientAllocation) {
		this.patientAllocation = patientAllocation;
	}

	/**
	 * @return the patientExcess
	 */
	public Integer getPatientExcess() {
		return patientExcess;
	}

	/**
	 * @param patientExcess the patientExcess to set
	 */
	public void setPatientExcess(Integer patientExcess) {
		this.patientExcess = patientExcess;
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

	/**
	 * @param modalityType the modalityType to set
	 */
	public void setModalityType(BaseObjectDTO modalityType) {
		this.modalityType = modalityType;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
}
