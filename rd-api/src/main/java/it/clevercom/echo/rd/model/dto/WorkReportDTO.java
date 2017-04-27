package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.clevercom.echo.common.dto.AbstractEchoDTO;

@JsonIgnoreProperties({"created","updated","userupdate","active","idd"})
public class WorkReportDTO extends AbstractEchoDTO implements Serializable {

	private static final long serialVersionUID = 4871642167804795055L;
	
	public Long idWorkReport;
	public Long accessionNumber;
	public BaseObjectDTO workStatus;
	public WorkTaskDTO workTask;
	private Long creationDate;
	private Long completionDate;
	private String body;
	private Set<UserDTO> workReportUsers;
	
	private Date created;
	private Date updated;
	private String userupdate;
	private Boolean active;
	
	@Override
	public Object getIdd() {
		return getIdWorkReport();
	}

	/**
	 * @return the idWorkReport
	 */
	public Long getIdWorkReport() {
		return idWorkReport;
	}

	/**
	 * @param idWorkReport the idWorkReport to set
	 */
	public void setIdWorkReport(Long idWorkReport) {
		this.idWorkReport = idWorkReport;
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
	 * @return the workTask
	 */
	public WorkTaskDTO getWorkTask() {
		return workTask;
	}

	/**
	 * @param workTask the workTask to set
	 */
	public void setWorkTask(WorkTaskDTO workTask) {
		this.workTask = workTask;
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
	 * @return the completionDate
	 */
	public Long getCompletionDate() {
		return completionDate;
	}

	/**
	 * @param completionDate the completionDate to set
	 */
	public void setCompletionDate(Long completionDate) {
		this.completionDate = completionDate;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the workReportUsers
	 */
	public Set<UserDTO> getWorkReportUsers() {
		return workReportUsers;
	}

	/**
	 * @param workReportUsers the workReportUsers to set
	 */
	public void setWorkReportUsers(Set<UserDTO> workReportUsers) {
		this.workReportUsers = workReportUsers;
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

	
}
