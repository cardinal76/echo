package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rd_work_status database table.
 * 
 */
@Entity
@Table(name="rd_work_status")
@NamedQuery(name="WorkStatus.findAll", query="SELECT w FROM WorkStatus w")
public class WorkStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String code;

	private Boolean active;

	private Timestamp created;

	private String description;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="rdWorkStatus")
	private List<Order> rdOrders;

	//bi-directional many-to-one association to WorkReport
	@OneToMany(mappedBy="rdWorkStatus")
	private List<WorkReport> rdWorkReports;

	//bi-directional many-to-one association to WorkSession
	@OneToMany(mappedBy="rdWorkStatus")
	private List<WorkSession> rdWorkSessions;

	//bi-directional many-to-one association to WorkTask
	@OneToMany(mappedBy="rdWorkStatus")
	private List<WorkTask> rdWorkTasks;

	public WorkStatus() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<Order> getRdOrders() {
		return this.rdOrders;
	}

	public void setRdOrders(List<Order> rdOrders) {
		this.rdOrders = rdOrders;
	}

	public Order addRdOrder(Order rdOrder) {
		getRdOrders().add(rdOrder);
		rdOrder.setRdWorkStatus(this);

		return rdOrder;
	}

	public Order removeRdOrder(Order rdOrder) {
		getRdOrders().remove(rdOrder);
		rdOrder.setRdWorkStatus(null);

		return rdOrder;
	}

	public List<WorkReport> getRdWorkReports() {
		return this.rdWorkReports;
	}

	public void setRdWorkReports(List<WorkReport> rdWorkReports) {
		this.rdWorkReports = rdWorkReports;
	}

	public WorkReport addRdWorkReport(WorkReport rdWorkReport) {
		getRdWorkReports().add(rdWorkReport);
		rdWorkReport.setRdWorkStatus(this);

		return rdWorkReport;
	}

	public WorkReport removeRdWorkReport(WorkReport rdWorkReport) {
		getRdWorkReports().remove(rdWorkReport);
		rdWorkReport.setRdWorkStatus(null);

		return rdWorkReport;
	}

	public List<WorkSession> getRdWorkSessions() {
		return this.rdWorkSessions;
	}

	public void setRdWorkSessions(List<WorkSession> rdWorkSessions) {
		this.rdWorkSessions = rdWorkSessions;
	}

	public WorkSession addRdWorkSession(WorkSession rdWorkSession) {
		getRdWorkSessions().add(rdWorkSession);
		rdWorkSession.setRdWorkStatus(this);

		return rdWorkSession;
	}

	public WorkSession removeRdWorkSession(WorkSession rdWorkSession) {
		getRdWorkSessions().remove(rdWorkSession);
		rdWorkSession.setRdWorkStatus(null);

		return rdWorkSession;
	}

	public List<WorkTask> getRdWorkTasks() {
		return this.rdWorkTasks;
	}

	public void setRdWorkTasks(List<WorkTask> rdWorkTasks) {
		this.rdWorkTasks = rdWorkTasks;
	}

	public WorkTask addRdWorkTask(WorkTask rdWorkTask) {
		getRdWorkTasks().add(rdWorkTask);
		rdWorkTask.setRdWorkStatus(this);

		return rdWorkTask;
	}

	public WorkTask removeRdWorkTask(WorkTask rdWorkTask) {
		getRdWorkTasks().remove(rdWorkTask);
		rdWorkTask.setRdWorkStatus(null);

		return rdWorkTask;
	}

}