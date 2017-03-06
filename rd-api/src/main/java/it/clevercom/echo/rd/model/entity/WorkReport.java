package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rd_work_report database table.
 * 
 */
@Entity
@Table(name="rd_work_report")
@NamedQuery(name="WorkReport.findAll", query="SELECT w FROM WorkReport w")
public class WorkReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idworkreport;

	private Long accessionnumber;

	private Boolean active;

	private String body;

	private Timestamp completiondate;

	private Timestamp created;

	private Timestamp creationdate;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to WorkSession
	@ManyToOne
	@JoinColumn(name="idworksession")
	private WorkSession rdWorkSession;

	//bi-directional many-to-one association to WorkStatus
	@ManyToOne
	@JoinColumn(name="statuscode")
	private WorkStatus rdWorkStatus;

	//bi-directional many-to-one association to WorkTask
	@ManyToOne
	@JoinColumn(name="idworktask")
	private WorkTask rdWorkTask;

	//bi-directional many-to-one association to WorkReportLog
	@OneToMany(mappedBy="rdWorkReport")
	private List<WorkReportLog> rdWorkReportLogs;

	//bi-directional many-to-one association to WorkReportUser
	@OneToMany(mappedBy="rdWorkReport")
	private List<WorkReportUser> rdWorkReportUsers;

	public WorkReport() {
	}

	public Long getIdworkreport() {
		return this.idworkreport;
	}

	public void setIdworkreport(Long idworkreport) {
		this.idworkreport = idworkreport;
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

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Timestamp getCompletiondate() {
		return this.completiondate;
	}

	public void setCompletiondate(Timestamp completiondate) {
		this.completiondate = completiondate;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getCreationdate() {
		return this.creationdate;
	}

	public void setCreationdate(Timestamp creationdate) {
		this.creationdate = creationdate;
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

	public WorkTask getRdWorkTask() {
		return this.rdWorkTask;
	}

	public void setRdWorkTask(WorkTask rdWorkTask) {
		this.rdWorkTask = rdWorkTask;
	}

	public List<WorkReportLog> getRdWorkReportLogs() {
		return this.rdWorkReportLogs;
	}

	public void setRdWorkReportLogs(List<WorkReportLog> rdWorkReportLogs) {
		this.rdWorkReportLogs = rdWorkReportLogs;
	}

	public WorkReportLog addRdWorkReportLog(WorkReportLog rdWorkReportLog) {
		getRdWorkReportLogs().add(rdWorkReportLog);
		rdWorkReportLog.setRdWorkReport(this);

		return rdWorkReportLog;
	}

	public WorkReportLog removeRdWorkReportLog(WorkReportLog rdWorkReportLog) {
		getRdWorkReportLogs().remove(rdWorkReportLog);
		rdWorkReportLog.setRdWorkReport(null);

		return rdWorkReportLog;
	}

	public List<WorkReportUser> getRdWorkReportUsers() {
		return this.rdWorkReportUsers;
	}

	public void setRdWorkReportUsers(List<WorkReportUser> rdWorkReportUsers) {
		this.rdWorkReportUsers = rdWorkReportUsers;
	}

	public WorkReportUser addRdWorkReportUser(WorkReportUser rdWorkReportUser) {
		getRdWorkReportUsers().add(rdWorkReportUser);
		rdWorkReportUser.setRdWorkReport(this);

		return rdWorkReportUser;
	}

	public WorkReportUser removeRdWorkReportUser(WorkReportUser rdWorkReportUser) {
		getRdWorkReportUsers().remove(rdWorkReportUser);
		rdWorkReportUser.setRdWorkReport(null);

		return rdWorkReportUser;
	}

}