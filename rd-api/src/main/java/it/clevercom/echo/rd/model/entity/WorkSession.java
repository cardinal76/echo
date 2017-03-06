package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rd_work_session database table.
 * 
 */
@Entity
@Table(name="rd_work_session")
@NamedQuery(name="WorkSession.findAll", query="SELECT w FROM WorkSession w")
public class WorkSession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idworksession;

	private Boolean active;

	private Timestamp created;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="rdWorkSession")
	private List<Order> rdOrders;

	//bi-directional many-to-one association to WorkReport
	@OneToMany(mappedBy="rdWorkSession")
	private List<WorkReport> rdWorkReports;

	//bi-directional many-to-one association to Patient
	@ManyToOne
	@JoinColumn(name="idpatient")
	private Patient rdPatient;

	//bi-directional many-to-one association to WorkStatus
	@ManyToOne
	@JoinColumn(name="statuscode")
	private WorkStatus rdWorkStatus;

	//bi-directional many-to-one association to WorkTask
	@OneToMany(mappedBy="rdWorkSession")
	private List<WorkTask> rdWorkTasks;

	public WorkSession() {
	}

	public Long getIdworksession() {
		return this.idworksession;
	}

	public void setIdworksession(Long idworksession) {
		this.idworksession = idworksession;
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
		rdOrder.setRdWorkSession(this);

		return rdOrder;
	}

	public Order removeRdOrder(Order rdOrder) {
		getRdOrders().remove(rdOrder);
		rdOrder.setRdWorkSession(null);

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
		rdWorkReport.setRdWorkSession(this);

		return rdWorkReport;
	}

	public WorkReport removeRdWorkReport(WorkReport rdWorkReport) {
		getRdWorkReports().remove(rdWorkReport);
		rdWorkReport.setRdWorkSession(null);

		return rdWorkReport;
	}

	public Patient getRdPatient() {
		return this.rdPatient;
	}

	public void setRdPatient(Patient rdPatient) {
		this.rdPatient = rdPatient;
	}

	public WorkStatus getRdWorkStatus() {
		return this.rdWorkStatus;
	}

	public void setRdWorkStatus(WorkStatus rdWorkStatus) {
		this.rdWorkStatus = rdWorkStatus;
	}

	public List<WorkTask> getRdWorkTasks() {
		return this.rdWorkTasks;
	}

	public void setRdWorkTasks(List<WorkTask> rdWorkTasks) {
		this.rdWorkTasks = rdWorkTasks;
	}

	public WorkTask addRdWorkTask(WorkTask rdWorkTask) {
		getRdWorkTasks().add(rdWorkTask);
		rdWorkTask.setRdWorkSession(this);

		return rdWorkTask;
	}

	public WorkTask removeRdWorkTask(WorkTask rdWorkTask) {
		getRdWorkTasks().remove(rdWorkTask);
		rdWorkTask.setRdWorkSession(null);

		return rdWorkTask;
	}

}