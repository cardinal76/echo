package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the worktask database table.
 * 
 */
@Entity
@Table(name="worktask")
@NamedQuery(name="Worktask.findAll", query="SELECT w FROM Worktask w")
public class Worktask implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idWorkTask;

	private Object active;

	@Temporal(TemporalType.TIMESTAMP)
	private Date assignment;

	private Object completed;

	private Timestamp created;

	@Temporal(TemporalType.TIMESTAMP)
	private Date deadline;

	@Temporal(TemporalType.TIMESTAMP)
	private Date execution;

	private String status;

	private String taskUUID;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Worklist
	@ManyToOne
	@JoinColumn(name="idWorkList")
	private Worklist worklist;

	//bi-directional many-to-one association to Worktype
	@ManyToOne
	@JoinColumn(name="idWorkType")
	private Worktype worktype;

	public Worktask() {
	}

	public int getIdWorkTask() {
		return this.idWorkTask;
	}

	public void setIdWorkTask(int idWorkTask) {
		this.idWorkTask = idWorkTask;
	}

	public Object getActive() {
		return this.active;
	}

	public void setActive(Object active) {
		this.active = active;
	}

	public Date getAssignment() {
		return this.assignment;
	}

	public void setAssignment(Date assignment) {
		this.assignment = assignment;
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

	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Date getExecution() {
		return this.execution;
	}

	public void setExecution(Date execution) {
		this.execution = execution;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTaskUUID() {
		return this.taskUUID;
	}

	public void setTaskUUID(String taskUUID) {
		this.taskUUID = taskUUID;
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

	public Worklist getWorklist() {
		return this.worklist;
	}

	public void setWorklist(Worklist worklist) {
		this.worklist = worklist;
	}

	public Worktype getWorktype() {
		return this.worktype;
	}

	public void setWorktype(Worktype worktype) {
		this.worktype = worktype;
	}

}