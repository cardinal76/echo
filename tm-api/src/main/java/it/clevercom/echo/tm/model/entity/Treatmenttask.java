package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the treatmenttask database table.
 * 
 */
@Entity
@Table(name="treatmenttask")
@NamedQuery(name="Treatmenttask.findAll", query="SELECT t FROM Treatmenttask t")
public class Treatmenttask implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTreatmentTask;

	private Object active;

	private Object completed;

	private Timestamp created;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date execution;

	@Temporal(TemporalType.TIMESTAMP)
	private Date scheduleFrom;

	@Temporal(TemporalType.TIMESTAMP)
	private Date scheduleTo;

	private String taskUUID;

	private String type;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Treatmentplan
	@ManyToOne
	@JoinColumn(name="idTreatmentPlan")
	private Treatmentplan treatmentplan;

	public Treatmenttask() {
	}

	public int getIdTreatmentTask() {
		return this.idTreatmentTask;
	}

	public void setIdTreatmentTask(int idTreatmentTask) {
		this.idTreatmentTask = idTreatmentTask;
	}

	public Object getActive() {
		return this.active;
	}

	public void setActive(Object active) {
		this.active = active;
	}

	public Object getCompleted() {
		return this.completed;
	}

	public void setCompleted(Object completed) {
		this.completed = completed;
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

	public Date getExecution() {
		return this.execution;
	}

	public void setExecution(Date execution) {
		this.execution = execution;
	}

	public Date getScheduleFrom() {
		return this.scheduleFrom;
	}

	public void setScheduleFrom(Date scheduleFrom) {
		this.scheduleFrom = scheduleFrom;
	}

	public Date getScheduleTo() {
		return this.scheduleTo;
	}

	public void setScheduleTo(Date scheduleTo) {
		this.scheduleTo = scheduleTo;
	}

	public String getTaskUUID() {
		return this.taskUUID;
	}

	public void setTaskUUID(String taskUUID) {
		this.taskUUID = taskUUID;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Treatmentplan getTreatmentplan() {
		return this.treatmentplan;
	}

	public void setTreatmentplan(Treatmentplan treatmentplan) {
		this.treatmentplan = treatmentplan;
	}

}