package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rd_work_task_log database table.
 * 
 */
@Entity
@Table(name="rd_work_task_log")
@NamedQuery(name="WorkTaskLog.findAll", query="SELECT w FROM WorkTaskLog w")
public class WorkTaskLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idworktasklog;

	private Long accessionnumber;

	private Boolean active;

	private Timestamp created;

	private Timestamp executiondate;

	private Long idmodality;

	private Long idservice;

	private Long iduser;

	private Long idworksession;

	private String prioritycode;

	private Timestamp scheduleddate;

	private String statuscode;

	private Long studyid;

	private String studyuuid;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to WorkTask
	@ManyToOne
	@JoinColumn(name="idworktask")
	private WorkTask rdWorkTask;

	public WorkTaskLog() {
	}

	public Long getIdworktasklog() {
		return this.idworktasklog;
	}

	public void setIdworktasklog(Long idworktasklog) {
		this.idworktasklog = idworktasklog;
	}

	public Long getAccessionnumber() {
		return this.accessionnumber;
	}

	public void setAccessionnumber(Long accessionnumber) {
		this.accessionnumber = accessionnumber;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getExecutiondate() {
		return this.executiondate;
	}

	public void setExecutiondate(Timestamp executiondate) {
		this.executiondate = executiondate;
	}

	public Long getIdmodality() {
		return this.idmodality;
	}

	public void setIdmodality(Long idmodality) {
		this.idmodality = idmodality;
	}

	public Long getIdservice() {
		return this.idservice;
	}

	public void setIdservice(Long idservice) {
		this.idservice = idservice;
	}

	public Long getIduser() {
		return this.iduser;
	}

	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}

	public Long getIdworksession() {
		return this.idworksession;
	}

	public void setIdworksession(Long idworksession) {
		this.idworksession = idworksession;
	}

	public String getPrioritycode() {
		return this.prioritycode;
	}

	public void setPrioritycode(String prioritycode) {
		this.prioritycode = prioritycode;
	}

	public Timestamp getScheduleddate() {
		return this.scheduleddate;
	}

	public void setScheduleddate(Timestamp scheduleddate) {
		this.scheduleddate = scheduleddate;
	}

	public String getStatuscode() {
		return this.statuscode;
	}

	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}

	public Long getStudyid() {
		return this.studyid;
	}

	public void setStudyid(Long studyid) {
		this.studyid = studyid;
	}

	public String getStudyuuid() {
		return this.studyuuid;
	}

	public void setStudyuuid(String studyuuid) {
		this.studyuuid = studyuuid;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getUserupdate() {
		return this.userupdate;
	}

	public void setUserupdate(String userupdate) {
		this.userupdate = userupdate;
	}

	public WorkTask getRdWorkTask() {
		return this.rdWorkTask;
	}

	public void setRdWorkTask(WorkTask rdWorkTask) {
		this.rdWorkTask = rdWorkTask;
	}

}