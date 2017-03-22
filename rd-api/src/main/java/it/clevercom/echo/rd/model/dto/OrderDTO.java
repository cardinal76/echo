package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OrderDTO implements Serializable {
	private static final long serialVersionUID = 3464811952700440696L;

	private Long idorder;
	private BaseObjectDTO originOrganizationUnit;
	private BaseObjectDTO targetOrganizationUnit;
	private BaseObjectDTO workPriority;
	private WorkSessionDTO workSession;
	private BaseObjectDTO workStatus;
	private String acquisitionchannel;
	private Long creationdate;
	private Long scheduleddate;
	private Long acceptancedate;
	private Long duration;
	private String requestingphysician;
	private String orderreason;
	private String rejectreason;
	private String clinicalhistory;
	private String notes;
	private Date created;
	private Date updated;
	private String userupdate;
	private Boolean active;
	private Set<OrderLogDTO> orderLogs = new HashSet<OrderLogDTO>(0);
	private Set<OrderServiceDTO> orderServices = new HashSet<OrderServiceDTO>(0);
	
	/**
	 * @return the idorder
	 */
	public Long getIdorder() {
		return idorder;
	}
	
	/**
	 * @param idorder the idorder to set
	 */
	public void setIdorder(Long idorder) {
		this.idorder = idorder;
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
	 * @return the acquisitionchannel
	 */
	public String getAcquisitionchannel() {
		return acquisitionchannel;
	}
	
	/**
	 * @param acquisitionchannel the acquisitionchannel to set
	 */
	public void setAcquisitionchannel(String acquisitionchannel) {
		this.acquisitionchannel = acquisitionchannel;
	}
	
	/**
	 * @return the creationdate
	 */
	public Long getCreationdate() {
		return creationdate;
	}
	
	/**
	 * @param creationdate the creationdate to set
	 */
	public void setCreationdate(Long creationdate) {
		this.creationdate = creationdate;
	}
	
	/**
	 * @return the scheduleddate
	 */
	public Long getScheduleddate() {
		return scheduleddate;
	}
	
	/**
	 * @param scheduleddate the scheduleddate to set
	 */
	public void setScheduleddate(Long scheduleddate) {
		this.scheduleddate = scheduleddate;
	}
	
	/**
	 * @return the acceptancedate
	 */
	public Long getAcceptancedate() {
		return acceptancedate;
	}
	
	/**
	 * @param acceptancedate the acceptancedate to set
	 */
	public void setAcceptancedate(Long acceptancedate) {
		this.acceptancedate = acceptancedate;
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
	 * @return the requestingphysician
	 */
	public String getRequestingphysician() {
		return requestingphysician;
	}
	
	/**
	 * @param requestingphysician the requestingphysician to set
	 */
	public void setRequestingphysician(String requestingphysician) {
		this.requestingphysician = requestingphysician;
	}
	
	/**
	 * @return the orderreason
	 */
	public String getOrderreason() {
		return orderreason;
	}
	
	/**
	 * @param orderreason the orderreason to set
	 */
	public void setOrderreason(String orderreason) {
		this.orderreason = orderreason;
	}
	
	/**
	 * @return the rejectreason
	 */
	public String getRejectreason() {
		return rejectreason;
	}
	
	/**
	 * @param rejectreason the rejectreason to set
	 */
	public void setRejectreason(String rejectreason) {
		this.rejectreason = rejectreason;
	}
	
	/**
	 * @return the clinicalhistory
	 */
	public String getClinicalhistory() {
		return clinicalhistory;
	}
	
	/**
	 * @param clinicalhistory the clinicalhistory to set
	 */
	public void setClinicalhistory(String clinicalhistory) {
		this.clinicalhistory = clinicalhistory;
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
	 * @return the orderServices
	 */
	public Set<OrderServiceDTO> getOrderServices() {
		return orderServices;
	}
	
	/**
	 * @param orderServices the orderServices to set
	 */
	public void setOrderServices(Set<OrderServiceDTO> orderServices) {
		this.orderServices = orderServices;
	}
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
