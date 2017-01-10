package it.clevercom.echo.tm.model.entity;
// Generated 10-gen-2017 15.17.23 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Worktask generated by hbm2java
 */
@Entity
@Table(name = "worktask", catalog = "tmdw")
public class Worktask implements java.io.Serializable {

	private Integer idWorkTask;
	private Worklist worklist;
	private Worktype worktype;
	private String taskUuid;
	private Date assignment;
	private Date deadline;
	private Date execution;
	private Boolean completed;
	private String status;
	private Date created;
	private Date updated;
	private boolean active;
	private String updateUser;

	public Worktask() {
	}

	public Worktask(Worklist worklist, Worktype worktype, Date assignment, Date created, Date updated, boolean active,
			String updateUser) {
		this.worklist = worklist;
		this.worktype = worktype;
		this.assignment = assignment;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
	}

	public Worktask(Worklist worklist, Worktype worktype, String taskUuid, Date assignment, Date deadline,
			Date execution, Boolean completed, String status, Date created, Date updated, boolean active,
			String updateUser) {
		this.worklist = worklist;
		this.worktype = worktype;
		this.taskUuid = taskUuid;
		this.assignment = assignment;
		this.deadline = deadline;
		this.execution = execution;
		this.completed = completed;
		this.status = status;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idWorkTask", unique = true, nullable = false)
	public Integer getIdWorkTask() {
		return this.idWorkTask;
	}

	public void setIdWorkTask(Integer idWorkTask) {
		this.idWorkTask = idWorkTask;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idWorkList", nullable = false)
	public Worklist getWorklist() {
		return this.worklist;
	}

	public void setWorklist(Worklist worklist) {
		this.worklist = worklist;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idWorkType", nullable = false)
	public Worktype getWorktype() {
		return this.worktype;
	}

	public void setWorktype(Worktype worktype) {
		this.worktype = worktype;
	}

	@Column(name = "taskUUID", length = 36)
	public String getTaskUuid() {
		return this.taskUuid;
	}

	public void setTaskUuid(String taskUuid) {
		this.taskUuid = taskUuid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "assignment", nullable = false, length = 19)
	public Date getAssignment() {
		return this.assignment;
	}

	public void setAssignment(Date assignment) {
		this.assignment = assignment;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deadline", length = 19)
	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "execution", length = 19)
	public Date getExecution() {
		return this.execution;
	}

	public void setExecution(Date execution) {
		this.execution = execution;
	}

	@Column(name = "completed")
	public Boolean getCompleted() {
		return this.completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	@Column(name = "status", length = 15)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = false, length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name = "updateUser", nullable = false, length = 100)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}