package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"created","updated","userUpdate","active"})
public class OrderDTO implements Serializable {
	private static final long serialVersionUID = 3464811952700440696L;

	private Long idOrder;
	private BaseObjectDTO originOrganizationUnit;
	private BaseObjectDTO targetOrganizationUnit;
    private PatientSmartDTO patient;
	private BaseObjectDTO workPriority;
	private WorkSessionDTO workSession;
	private BaseObjectDTO workStatus;
	private String acquisitionChannel;
	private Long creationDate;
	private Long scheduledDate;
	private Long acceptanceDate;
	private Long duration;
	private String requestingPhysician;
	private String clinicalQuestion;
	private String rejectReason;
	private String anamnesys;
	private String notes;
	private String cancelReason;
	private Date created;
	private Date updated;
	private String userUpdate;
	private Boolean active;
	private Set<BaseObjectDTO> services = new HashSet<BaseObjectDTO>(0);
	private Set<BaseObjectDTO> canceledServices = new HashSet<BaseObjectDTO>();
    private String cancelreason;
    private String identificationdocument;
    
	/**
	 * @return the idOrder
	 */
	public Long getIdOrder() {
		return idOrder;
	}
	
	/**
	 * @param idOrder the idOrder to set
	 */
	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}
	
	/**
	 * @return the originOrganizationUnit
	 */
	public BaseObjectDTO getOriginOrganizationUnit() {
		return originOrganizationUnit;
	}
	
	/**
	 * @param originOrganizationUnit the originOrganizationUnit to set
	 */
	public void setOriginOrganizationUnit(BaseObjectDTO originOrganizationUnit) {
		this.originOrganizationUnit = originOrganizationUnit;
	}
	
	/**
	 * @return the targetOrganizationUnit
	 */
	public BaseObjectDTO getTargetOrganizationUnit() {
		return targetOrganizationUnit;
	}
	
	/**
	 * @param targetOrganizationUnit the targetOrganizationUnit to set
	 */
	public void setTargetOrganizationUnit(BaseObjectDTO targetOrganizationUnit) {
		this.targetOrganizationUnit = targetOrganizationUnit;
	}
	
	/**
	 * @return the patient
	 */
	public PatientSmartDTO getPatient() {
		return patient;
	}
	
	/**
	 * @param patient the patient to set
	 */
	public void setPatient(PatientSmartDTO patient) {
		this.patient = patient;
	}
	
	/**
	 * @return the workPriority
	 */
	public BaseObjectDTO getWorkPriority() {
		return workPriority;
	}
	
	/**
	 * @param workPriority the workPriority to set
	 */
	public void setWorkPriority(BaseObjectDTO workPriority) {
		this.workPriority = workPriority;
	}
	
	/**
	 * @return the workSession
	 */
	public WorkSessionDTO getWorkSession() {
		return workSession;
	}
	
	/**
	 * @param workSession the workSession to set
	 */
	public void setWorkSession(WorkSessionDTO workSession) {
		this.workSession = workSession;
	}
	
	/**
	 * @return the workStatus
	 */
	public BaseObjectDTO getWorkStatus() {
		return workStatus;
	}
	
	/**
	 * @param workStatus the workStatus to set
	 */
	public void setWorkStatus(BaseObjectDTO workStatus) {
		this.workStatus = workStatus;
	}
	
	/**
	 * @return the acquisitionChannel
	 */
	public String getAcquisitionChannel() {
		return acquisitionChannel;
	}
	
	/**
	 * @param acquisitionChannel the acquisitionChannel to set
	 */
	public void setAcquisitionChannel(String acquisitionChannel) {
		this.acquisitionChannel = acquisitionChannel;
	}
	
	/**
	 * @return the creationDate
	 */
	public Long getCreationDate() {
		return creationDate;
	}
	
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}
	
	/**
	 * @return the scheduleDate
	 */
	public Long getScheduledDate() {
		return scheduledDate;
	}
	
	/**
	 * @param scheduleDate the scheduleDate to set
	 */
	public void setScheduledDate(Long scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	
	/**
	 * @return the acceptanceDate
	 */
	public Long getAcceptanceDate() {
		return acceptanceDate;
	}
	
	/**
	 * @param acceptanceDate the acceptanceDate to set
	 */
	public void setAcceptanceDate(Long acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
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
	 * @return the requestingPhysician
	 */
	public String getRequestingPhysician() {
		return requestingPhysician;
	}
	
	/**
	 * @param requestingPhysician the requestingPhysician to set
	 */
	public void setRequestingPhysician(String requestingPhysician) {
		this.requestingPhysician = requestingPhysician;
	}
	
	/**
	 * @return the rejectReason
	 */
	public String getRejectReason() {
		return rejectReason;
	}
	
	/**
	 * @param rejectReason the rejectReason to set
	 */
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	
	/**
	 * @return the clinicalQuestion
	 */
	public String getClinicalQuestion() {
		return clinicalQuestion;
	}

	/**
	 * @param clinicalQuestion the clinicalQuestion to set
	 */
	public void setClinicalQuestion(String clinicalQuestion) {
		this.clinicalQuestion = clinicalQuestion;
	}

	/**
	 * @return the anamnesys
	 */
	public String getAnamnesys() {
		return anamnesys;
	}

	/**
	 * @param anamnesys the anamnesys to set
	 */
	public void setAnamnesys(String anamnesys) {
		this.anamnesys = anamnesys;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}
	
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
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
	 * @return the services
	 */
	public Set<BaseObjectDTO> getServices() {
		return services;
	}
	
	/**
	 * @param services the services to set
	 */
	public void setServices(Set<BaseObjectDTO> services) {
		this.services = services;
	}
	
	/**
	 * @return the canceledServices
	 */
	public Set<BaseObjectDTO> getCanceledServices() {
		return canceledServices;
	}

	/**
	 * @param canceledServices the canceledServices to set
	 */
	public void setCanceledServices(Set<BaseObjectDTO> canceledServices) {
		this.canceledServices = canceledServices;
	}
	
	/**
	 * @return the cancelReason
	 */
	public String getCancelReason() {
		return cancelReason;
	}

	/**
	 * @param cancelReason the cancelReason to set
	 */
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the cancelreason
	 */
	public String getCancelreason() {
		return cancelreason;
	}

	/**
	 * @param cancelreason the cancelreason to set
	 */
	public void setCancelreason(String cancelreason) {
		this.cancelreason = cancelreason;
	}

	/**
	 * @return the identificationdocument
	 */
	public String getIdentificationdocument() {
		return identificationdocument;
	}

	/**
	 * @param identificationdocument the identificationdocument to set
	 */
	public void setIdentificationdocument(String identificationdocument) {
		this.identificationdocument = identificationdocument;
	}
}
