package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.clevercom.echo.common.dto.AbstractEchoDTO;

@JsonIgnoreProperties({"created","updated","userupdate","active","idd"})
public class WorkSessionDTO extends AbstractEchoDTO implements Serializable {
	
	private static final long serialVersionUID = 6709543309831003561L;

	public Long idWorkSession;
	public PatientSmartDTO patient;
	public BaseObjectDTO workStatus;
	public Set<WorkTaskDTO> workTasks;
	public Set<WorkReportDTO> workReports;
	
	private Date created;
	private Date updated;
	private String userupdate;
	private Boolean active;
	
	@Override
	public Object getIdd() {
		return getIdWorkSession();
	}

	/**
	 * @return the idWorkSession
	 */
	public Long getIdWorkSession() {
		return idWorkSession;
	}

	/**
	 * @param idWorkSession the idWorkSession to set
	 */
	public void setIdWorkSession(Long idWorkSession) {
		this.idWorkSession = idWorkSession;
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
	 * @return the workTasks
	 */
	public Set<WorkTaskDTO> getWorkTasks() {
		return workTasks;
	}

	/**
	 * @param workTasks the workTasks to set
	 */
	public void setWorkTasks(Set<WorkTaskDTO> workTasks) {
		this.workTasks = workTasks;
	}

	/**
	 * @return the workReports
	 */
	public Set<WorkReportDTO> getWorkReports() {
		return workReports;
	}

	/**
	 * @param workReports the workReports to set
	 */
	public void setWorkReports(Set<WorkReportDTO> workReports) {
		this.workReports = workReports;
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
}
