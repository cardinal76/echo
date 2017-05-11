package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;

import it.clevercom.echo.common.dto.AbstractEchoDTO;

public class WorkTaskDTO extends AbstractEchoDTO implements Serializable {
	
	private static final long serialVersionUID = 5376111644338310682L;

	private Long idWorkTask;
	private Long accessionNumber;
	private BaseObjectDTO workStatus;
	private BaseObjectDTO workPriority;
	private BaseObjectDTO service;
	private ModalityDTO modality;
	private Long scheduledDate;
	private Long executedDate;
	private String studyId;
	private String studyUuid;
	private UserDTO user;
	private Long executingDate;
	private Long reportingDate;
	private Long reportedDate;
	private String executingNote;
	private String executedNote;
	private String reportingNote;
	private String reportedNote;

	private Date created;
	private Date updated;
	private String userupdate;
	private Boolean active;
	
	@Override
	public Object getIdd() {
		return getIdWorkTask();
	}

	/**
	 * @return the idWorkTask
	 */
	public Long getIdWorkTask() {
		return idWorkTask;
	}

	/**
	 * @param idWorkTask the idWorkTask to set
	 */
	public void setIdWorkTask(Long idWorkTask) {
		this.idWorkTask = idWorkTask;
	}

	/**
	 * @return the accessionNumber
	 */
	public Long getAccessionNumber() {
		return accessionNumber;
	}

	/**
	 * @param accessionNumber the accessionNumber to set
	 */
	public void setAccessionNumber(Long accessionNumber) {
		this.accessionNumber = accessionNumber;
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
	 * @return the service
	 */
	public BaseObjectDTO getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(BaseObjectDTO service) {
		this.service = service;
	}

	/**
	 * @return the modality
	 */
	public ModalityDTO getModality() {
		return modality;
	}

	/**
	 * @param modality the modality to set
	 */
	public void setModality(ModalityDTO modality) {
		this.modality = modality;
	}

	/**
	 * @return the scheduledDate
	 */
	public Long getScheduledDate() {
		return scheduledDate;
	}

	/**
	 * @param scheduledDate the scheduledDate to set
	 */
	public void setScheduledDate(Long scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	/**
	 * @return the executedDate
	 */
	public Long getExecutedDate() {
		return executedDate;
	}

	/**
	 * @param executedDate the executedDate to set
	 */
	public void setExecutedDate(Long executedDate) {
		this.executedDate = executedDate;
	}

	/**
	 * @return the studyId
	 */
	public String getStudyId() {
		return studyId;
	}

	/**
	 * @param studyId the studyId to set
	 */
	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}

	/**
	 * @return the studyUuid
	 */
	public String getStudyUuid() {
		return studyUuid;
	}

	/**
	 * @param studyUuid the studyUuid to set
	 */
	public void setStudyUuid(String studyUuid) {
		this.studyUuid = studyUuid;
	}

	/**
	 * @return the user
	 */
	public UserDTO getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserDTO user) {
		this.user = user;
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
	 * @return the executingNote
	 */
	public String getExecutingNote() {
		return executingNote;
	}

	/**
	 * @param executingNote the executingNote to set
	 */
	public void setExecutingNote(String executingNote) {
		this.executingNote = executingNote;
	}

	/**
	 * @return the executedNote
	 */
	public String getExecutedNote() {
		return executedNote;
	}

	/**
	 * @param executedNote the executedNote to set
	 */
	public void setExecutedNote(String executedNote) {
		this.executedNote = executedNote;
	}

	/**
	 * @return the reportingNote
	 */
	public String getReportingNote() {
		return reportingNote;
	}

	/**
	 * @param reportingNote the reportingNote to set
	 */
	public void setReportingNote(String reportingNote) {
		this.reportingNote = reportingNote;
	}

	/**
	 * @return the reportedNote
	 */
	public String getReportedNote() {
		return reportedNote;
	}

	/**
	 * @param reportedNote the reportedNote to set
	 */
	public void setReportedNote(String reportedNote) {
		this.reportedNote = reportedNote;
	}
}