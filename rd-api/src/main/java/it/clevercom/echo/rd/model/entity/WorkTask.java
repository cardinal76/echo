package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rd_work_task database table.
 * 
 */
@Entity
@Table(name="rd_work_task")
@NamedQuery(name="WorkTask.findAll", query="SELECT w FROM WorkTask w")
public class WorkTask implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idworktask;

	private Long accessionnumber;

	private Boolean active;

	private Timestamp created;

	private Timestamp executiondate;

	private Timestamp scheduleddate;

	private String studyid;

	private String studyuuid;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to WorkReport
	@OneToMany(mappedBy="rdWorkTask")
	private List<WorkReport> rdWorkReports;

	//bi-directional many-to-one association to Modality
	@ManyToOne
	@JoinColumn(name="idmodality")
	private Modality rdModality;

	//bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name="idservice")
	private Service rdService;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="iduser")
	private User rdUser;

	//bi-directional many-to-one association to WorkPriority
	@ManyToOne
	@JoinColumn(name="prioritycode")
	private WorkPriority rdWorkPriority;

	//bi-directional many-to-one association to WorkSession
	@ManyToOne
	@JoinColumn(name="idworksession")
	private WorkSession rdWorkSession;

	//bi-directional many-to-one association to WorkStatus
	@ManyToOne
	@JoinColumn(name="statuscode")
	private WorkStatus rdWorkStatus;

	//bi-directional many-to-one association to WorkTaskLog
	@OneToMany(mappedBy="rdWorkTask")
	private List<WorkTaskLog> rdWorkTaskLogs;

	public WorkTask() {
	}

	public Long getIdworktask() {
		return this.idworktask;
	}

	public void setIdworktask(Long idworktask) {
		this.idworktask = idworktask;
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

	public Timestamp getScheduleddate() {
		return this.scheduleddate;
	}

	public void setScheduleddate(Timestamp scheduleddate) {
		this.scheduleddate = scheduleddate;
	}

	public String getStudyid() {
		return this.studyid;
	}

	public void setStudyid(String studyid) {
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

	public List<WorkReport> getRdWorkReports() {
		return this.rdWorkReports;
	}

	public void setRdWorkReports(List<WorkReport> rdWorkReports) {
		this.rdWorkReports = rdWorkReports;
	}

	public WorkReport addRdWorkReport(WorkReport rdWorkReport) {
		getRdWorkReports().add(rdWorkReport);
		rdWorkReport.setRdWorkTask(this);

		return rdWorkReport;
	}

	public WorkReport removeRdWorkReport(WorkReport rdWorkReport) {
		getRdWorkReports().remove(rdWorkReport);
		rdWorkReport.setRdWorkTask(null);

		return rdWorkReport;
	}

	public Modality getRdModality() {
		return this.rdModality;
	}

	public void setRdModality(Modality rdModality) {
		this.rdModality = rdModality;
	}

	public Service getRdService() {
		return this.rdService;
	}

	public void setRdService(Service rdService) {
		this.rdService = rdService;
	}

	public User getRdUser() {
		return this.rdUser;
	}

	public void setRdUser(User rdUser) {
		this.rdUser = rdUser;
	}

	public WorkPriority getRdWorkPriority() {
		return this.rdWorkPriority;
	}

	public void setRdWorkPriority(WorkPriority rdWorkPriority) {
		this.rdWorkPriority = rdWorkPriority;
	}

	public WorkSession getRdWorkSession() {
		return this.rdWorkSession;
	}

	public void setRdWorkSession(WorkSession rdWorkSession) {
		this.rdWorkSession = rdWorkSession;
	}

	public WorkStatus getRdWorkStatus() {
		return this.rdWorkStatus;
	}

	public void setRdWorkStatus(WorkStatus rdWorkStatus) {
		this.rdWorkStatus = rdWorkStatus;
	}

	public List<WorkTaskLog> getRdWorkTaskLogs() {
		return this.rdWorkTaskLogs;
	}

	public void setRdWorkTaskLogs(List<WorkTaskLog> rdWorkTaskLogs) {
		this.rdWorkTaskLogs = rdWorkTaskLogs;
	}

	public WorkTaskLog addRdWorkTaskLog(WorkTaskLog rdWorkTaskLog) {
		getRdWorkTaskLogs().add(rdWorkTaskLog);
		rdWorkTaskLog.setRdWorkTask(this);

		return rdWorkTaskLog;
	}

	public WorkTaskLog removeRdWorkTaskLog(WorkTaskLog rdWorkTaskLog) {
		getRdWorkTaskLogs().remove(rdWorkTaskLog);
		rdWorkTaskLog.setRdWorkTask(null);

		return rdWorkTaskLog;
	}

}