package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.clevercom.echo.common.dto.AbstractEchoDTO;

@JsonIgnoreProperties({"created","updated","userUpdate","active","idd"})
public class OrderDTO extends AbstractEchoDTO implements Serializable {
	private static final long serialVersionUID = 3464811952700440696L;

	private Long idOrder;
	private BaseObjectDTO originOrganizationUnit;
	private BaseObjectDTO targetOrganizationUnit;
    private PatientSmartDTO patient;
	private BaseObjectDTO workPriority;
	private WorkSessionDTO workSession;
	private BaseObjectDTO workStatus;
	private BaseObjectDTO masterModalityType;
	private String acquisitionChannel;
	private Long creationDate;
	private Long scheduledDate;
	private Long acceptanceDate;
	private Long duration;
	private ModalityDTO scheduledModality;
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
	private Set<OrderedServiceDTO> services = new HashSet<OrderedServiceDTO>(0);
	private Set<OrderedServiceDTO> canceledServices = new HashSet<OrderedServiceDTO>();
    private String identificationDocument;
    private Long executingDate;
    private Long executedDate;
    private Long reportingDate;
    private Long reportedDate;
    private Long signedDate;
    private Long deliveredDate;
    private Long archivedDate;
    private Long canceledDate;
    
    
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
	public Set<OrderedServiceDTO> getServices() {
		return services;
	}
	
	/**
	 * @param services the services to set
	 */
	public void setServices(Set<OrderedServiceDTO> services) {
		this.services = services;
	}
	
	/**
	 * @return the canceledServices
	 */
	public Set<OrderedServiceDTO> getCanceledServices() {
		return canceledServices;
	}

	/**
	 * @param canceledServices the canceledServices to set
	 */
	public void setCanceledServices(Set<OrderedServiceDTO> canceledServices) {
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
	 * @return the identificationDocument
	 */
	public String getIdentificationDocument() {
		return identificationDocument;
	}

	/**
	 * @param identificationDocument the identificationDocument to set
	 */
	public void setIdentificationDocument(String identificationDocument) {
		this.identificationDocument = identificationDocument;
	}

	/**
	 * @return the executingDate
	 */
	public Long getExecutingDate() {
		return executingDate;
	}

	/**
	 * @param executingDate the executingDate to set
	 */
	public void setExecutingDate(Long executingDate) {
		this.executingDate = executingDate;
	}

	/**
	 * @return the executeDate
	 */
	public Long getExecutedDate() {
		return executedDate;
	}

	/**
	 * @param executeDate the executeDate to set
	 */
	public void setExecutedDate(Long executedDate) {
		this.executedDate = executedDate;
	}

	/**
	 * @return the reportingDate
	 */
	public Long getReportingDate() {
		return reportingDate;
	}

	/**
	 * @param reportingDate the reportingDate to set
	 */
	public void setReportingDate(Long reportingDate) {
		this.reportingDate = reportingDate;
	}

	/**
	 * @return the reportedDate
	 */
	public Long getReportedDate() {
		return reportedDate;
	}

	/**
	 * @param reportedDate the reportedDate to set
	 */
	public void setReportedDate(Long reportedDate) {
		this.reportedDate = reportedDate;
	}

	/**
	 * @return the signedDate
	 */
	public Long getSignedDate() {
		return signedDate;
	}

	/**
	 * @param signedDate the signedDate to set
	 */
	public void setSignedDate(Long signedDate) {
		this.signedDate = signedDate;
	}

	/**
	 * @return the deliveredDate
	 */
	public Long getDeliveredDate() {
		return deliveredDate;
	}

	/**
	 * @param deliveredDate the deliveredDate to set
	 */
	public void setDeliveredDate(Long deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	/**
	 * @return the archivedDate
	 */
	public Long getArchivedDate() {
		return archivedDate;
	}

	/**
	 * @param archivedDate the archivedDate to set
	 */
	public void setArchivedDate(Long archivedDate) {
		this.archivedDate = archivedDate;
	}

	/**
	 * @return the canceledDate
	 */
	public Long getCanceledDate() {
		return canceledDate;
	}

	/**
	 * @param canceledDate the canceledDate to set
	 */
	public void setCanceledDate(Long canceledDate) {
		this.canceledDate = canceledDate;
	}
	
	/**
	 * @return the masterModalityType
	 */
	public BaseObjectDTO getMasterModalityType() {
		return masterModalityType;
	}

	/**
	 * @param masterModalityType the masterModalityType to set
	 */
	public void setMasterModalityType(BaseObjectDTO masterModalityType) {
		this.masterModalityType = masterModalityType;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the scheduledModality
	 */
	public ModalityDTO getScheduledModality() {
		return scheduledModality;
	}

	/**
	 * @param scheduledModality the scheduledModality to set
	 */
	public void setScheduledModality(ModalityDTO scheduledModality) {
		this.scheduledModality = scheduledModality;
	}

	@Override
	public Object getIdd() {
		return getIdOrder();
	}
}
