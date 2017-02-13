package it.clevercom.echo.rd.model.entity;
// Generated 13-feb-2017 9.08.37 by Hibernate Tools 5.1.0.Final

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
 * Reporttracking generated by hbm2java
 */
@Entity
@Table(name = "reporttracking", catalog = "rmdw")
public class Reporttracking implements java.io.Serializable {

	private Integer idReportTracking;
	private Report report;
	private int idRadiologicalTask;
	private Integer accessionNumber;
	private String statusCode;
	private Date creationDate;
	private Date completionDate;
	private String body;
	private String rejectReason;
	private int idWorklist;
	private Date created;
	private Date updated;
	private String userUpdate;
	private boolean active;

	public Reporttracking() {
	}

	public Reporttracking(Report report, int idRadiologicalTask, String statusCode, Date creationDate, String body,
			int idWorklist, Date created, Date updated, String userUpdate, boolean active) {
		this.report = report;
		this.idRadiologicalTask = idRadiologicalTask;
		this.statusCode = statusCode;
		this.creationDate = creationDate;
		this.body = body;
		this.idWorklist = idWorklist;
		this.created = created;
		this.updated = updated;
		this.userUpdate = userUpdate;
		this.active = active;
	}

	public Reporttracking(Report report, int idRadiologicalTask, Integer accessionNumber, String statusCode,
			Date creationDate, Date completionDate, String body, String rejectReason, int idWorklist, Date created,
			Date updated, String userUpdate, boolean active) {
		this.report = report;
		this.idRadiologicalTask = idRadiologicalTask;
		this.accessionNumber = accessionNumber;
		this.statusCode = statusCode;
		this.creationDate = creationDate;
		this.completionDate = completionDate;
		this.body = body;
		this.rejectReason = rejectReason;
		this.idWorklist = idWorklist;
		this.created = created;
		this.updated = updated;
		this.userUpdate = userUpdate;
		this.active = active;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idReportTracking", unique = true, nullable = false)
	public Integer getIdReportTracking() {
		return this.idReportTracking;
	}

	public void setIdReportTracking(Integer idReportTracking) {
		this.idReportTracking = idReportTracking;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idReport", nullable = false)
	public Report getReport() {
		return this.report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	@Column(name = "idRadiologicalTask", nullable = false)
	public int getIdRadiologicalTask() {
		return this.idRadiologicalTask;
	}

	public void setIdRadiologicalTask(int idRadiologicalTask) {
		this.idRadiologicalTask = idRadiologicalTask;
	}

	@Column(name = "accessionNumber")
	public Integer getAccessionNumber() {
		return this.accessionNumber;
	}

	public void setAccessionNumber(Integer accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	@Column(name = "statusCode", nullable = false, length = 100)
	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creationDate", nullable = false, length = 19)
	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "completionDate", length = 19)
	public Date getCompletionDate() {
		return this.completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	@Column(name = "body", nullable = false)
	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Column(name = "rejectReason")
	public String getRejectReason() {
		return this.rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	@Column(name = "idWorklist", nullable = false)
	public int getIdWorklist() {
		return this.idWorklist;
	}

	public void setIdWorklist(int idWorklist) {
		this.idWorklist = idWorklist;
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

	@Column(name = "userUpdate", nullable = false, length = 100)
	public String getUserUpdate() {
		return this.userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}