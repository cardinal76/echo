package it.clevercom.echo.tm.model.entity;
// Generated 17-gen-2017 15.09.29 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Detectiontask generated by hbm2java
 */
@Entity
@Table(name = "detectiontask", catalog = "tmdw", uniqueConstraints = @UniqueConstraint(columnNames = "taskUUID"))
public class Detectiontask implements java.io.Serializable {

	private Integer idDetectionTask;
	private Detectionplan detectionplan;
	private Detectiontype detectiontype;
	private String description;
	private Date execution;
	private Date scheduleFrom;
	private Date scheduleTo;
	private Boolean completed;
	private String attachmentUrl;
	private String mimeType;
	private String filename;
	private String comment;
	private String taskUuid;
	private Date created;
	private Date updated;
	private boolean active;
	private String updateUser;
	private Set<Detectionresult> detectionresults = new HashSet<Detectionresult>(0);

	public Detectiontask() {
	}

	public Detectiontask(Detectiontype detectiontype, String taskUuid, Date created, Date updated, boolean active,
			String updateUser) {
		this.detectiontype = detectiontype;
		this.taskUuid = taskUuid;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
	}

	public Detectiontask(Detectionplan detectionplan, Detectiontype detectiontype, String description, Date execution,
			Date scheduleFrom, Date scheduleTo, Boolean completed, String attachmentUrl, String mimeType,
			String filename, String comment, String taskUuid, Date created, Date updated, boolean active,
			String updateUser, Set<Detectionresult> detectionresults) {
		this.detectionplan = detectionplan;
		this.detectiontype = detectiontype;
		this.description = description;
		this.execution = execution;
		this.scheduleFrom = scheduleFrom;
		this.scheduleTo = scheduleTo;
		this.completed = completed;
		this.attachmentUrl = attachmentUrl;
		this.mimeType = mimeType;
		this.filename = filename;
		this.comment = comment;
		this.taskUuid = taskUuid;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
		this.detectionresults = detectionresults;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idDetectionTask", unique = true, nullable = false)
	public Integer getIdDetectionTask() {
		return this.idDetectionTask;
	}

	public void setIdDetectionTask(Integer idDetectionTask) {
		this.idDetectionTask = idDetectionTask;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idDetectionPlan")
	public Detectionplan getDetectionplan() {
		return this.detectionplan;
	}

	public void setDetectionplan(Detectionplan detectionplan) {
		this.detectionplan = detectionplan;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idDetectionType", nullable = false)
	public Detectiontype getDetectiontype() {
		return this.detectiontype;
	}

	public void setDetectiontype(Detectiontype detectiontype) {
		this.detectiontype = detectiontype;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "execution", length = 19)
	public Date getExecution() {
		return this.execution;
	}

	public void setExecution(Date execution) {
		this.execution = execution;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "scheduleFrom", length = 19)
	public Date getScheduleFrom() {
		return this.scheduleFrom;
	}

	public void setScheduleFrom(Date scheduleFrom) {
		this.scheduleFrom = scheduleFrom;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "scheduleTo", length = 19)
	public Date getScheduleTo() {
		return this.scheduleTo;
	}

	public void setScheduleTo(Date scheduleTo) {
		this.scheduleTo = scheduleTo;
	}

	@Column(name = "completed")
	public Boolean getCompleted() {
		return this.completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	@Column(name = "attachmentUrl")
	public String getAttachmentUrl() {
		return this.attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	@Column(name = "mimeType")
	public String getMimeType() {
		return this.mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	@Column(name = "filename")
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "comment")
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "taskUUID", unique = true, nullable = false, length = 36)
	public String getTaskUuid() {
		return this.taskUuid;
	}

	public void setTaskUuid(String taskUuid) {
		this.taskUuid = taskUuid;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "detectiontask")
	public Set<Detectionresult> getDetectionresults() {
		return this.detectionresults;
	}

	public void setDetectionresults(Set<Detectionresult> detectionresults) {
		this.detectionresults = detectionresults;
	}

}
