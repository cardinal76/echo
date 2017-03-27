package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.clevercom.echo.rd.model.entity.Patient;

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
	private String orderReason;
	private String rejectReason;
	private String clinicalHistory;
	private String notes;
	private Date created;
	private Date updated;
	private String userUpdate;
	private Boolean active;
	private Set<OrderLogDTO> orderLogs = new HashSet<OrderLogDTO>(0);
	private Set<BaseObjectDTO> services = new HashSet<BaseObjectDTO>(0);
	
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
	 * @return the orderReason
	 */
	public String getOrderReason() {
		return orderReason;
	}
	
	/**
	 * @param orderReason the orderReason to set
	 */
	public void setOrderReason(String orderReason) {
		this.orderReason = orderReason;
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
	 * @return the clinicalHistory
	 */
	public String getClinicalHistory() {
		return clinicalHistory;
	}
	
	/**
	 * @param clinicalHistory the clinicalHistory to set
	 */
	public void setClinicalHistory(String clinicalHistory) {
		this.clinicalHistory = clinicalHistory;
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
	 * @return the orderLogs
	 */
	public Set<OrderLogDTO> getOrderLogs() {
		return orderLogs;
	}
	
	/**
	 * @param orderLogs the orderLogs to set
	 */
	public void setOrderLogs(Set<OrderLogDTO> orderLogs) {
		this.orderLogs = orderLogs;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
