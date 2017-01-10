package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the detectiontask database table.
 * 
 */
@Entity
@Table(name="detectiontask")
@NamedQuery(name="Detectiontask.findAll", query="SELECT d FROM Detectiontask d")
public class Detectiontask implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idDetectionTask;

	private Object active;

	private String attachmentUrl;

	private Object comment;

	private Object completed;

	private Timestamp created;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date execution;

	private String filename;

	private String mimeType;

	@Temporal(TemporalType.TIMESTAMP)
	private Date scheduleFrom;

	private Timestamp scheduleTo;

	private String taskUUID;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Detectionresult
	@OneToMany(mappedBy="detectiontask")
	private List<Detectionresult> detectionresults;

	//bi-directional many-to-one association to Detectionplan
	@ManyToOne
	@JoinColumn(name="idDetectionPlan")
	private Detectionplan detectionplan;

	//bi-directional many-to-one association to Detectiontype
	@ManyToOne
	@JoinColumn(name="idDetectionType")
	private Detectiontype detectiontype;

	public Detectiontask() {
	}

	public int getIdDetectionTask() {
		return this.idDetectionTask;
	}

	public void setIdDetectionTask(int idDetectionTask) {
		this.idDetectionTask = idDetectionTask;
	}

	public Object getActive() {
		return this.active;
	}

	public void setActive(Object active) {
		this.active = active;
	}

	public String getAttachmentUrl() {
		return this.attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	public Object getComment() {
		return this.comment;
	}

	public void setComment(Object comment) {
		this.comment = comment;
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

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getMimeType() {
		return this.mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Date getScheduleFrom() {
		return this.scheduleFrom;
	}

	public void setScheduleFrom(Date scheduleFrom) {
		this.scheduleFrom = scheduleFrom;
	}

	public Timestamp getScheduleTo() {
		return this.scheduleTo;
	}

	public void setScheduleTo(Timestamp scheduleTo) {
		this.scheduleTo = scheduleTo;
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

	public List<Detectionresult> getDetectionresults() {
		return this.detectionresults;
	}

	public void setDetectionresults(List<Detectionresult> detectionresults) {
		this.detectionresults = detectionresults;
	}

	public Detectionresult addDetectionresult(Detectionresult detectionresult) {
		getDetectionresults().add(detectionresult);
		detectionresult.setDetectiontask(this);

		return detectionresult;
	}

	public Detectionresult removeDetectionresult(Detectionresult detectionresult) {
		getDetectionresults().remove(detectionresult);
		detectionresult.setDetectiontask(null);

		return detectionresult;
	}

	public Detectionplan getDetectionplan() {
		return this.detectionplan;
	}

	public void setDetectionplan(Detectionplan detectionplan) {
		this.detectionplan = detectionplan;
	}

	public Detectiontype getDetectiontype() {
		return this.detectiontype;
	}

	public void setDetectiontype(Detectiontype detectiontype) {
		this.detectiontype = detectiontype;
	}

}