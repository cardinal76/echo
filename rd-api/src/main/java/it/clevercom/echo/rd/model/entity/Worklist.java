package it.clevercom.echo.rd.model.entity;
// Generated 15-feb-2017 18.09.08 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Worklist generated by hbm2java
 */
@Entity
@Table(name = "WORKLIST")
public class Worklist implements java.io.Serializable {

	private long idworklist;
	private Status status;
	private Service service;
	private Priority priority;
	private Modality modality;
	private Risuser risuser;
	private Radiologytask radiologytask;
	private long accessionnumber;
	private Date scheduleddate;
	private Date executiondate;
	private String studyuuid;
	private String studyid;
	private Date updated;
	private String userupdate;
	private short active;
	private Set<Report> reports = new HashSet<Report>(0);
	private Set<Worklisttracking> worklisttrackings = new HashSet<Worklisttracking>(0);

	public Worklist() {
	}

	public Worklist(long idworklist, Status status, Service service, Priority priority, Modality modality,
			Risuser risuser, Radiologytask radiologytask, long accessionnumber, Date scheduleddate, String studyuuid,
			Date updated, String userupdate, short active) {
		this.idworklist = idworklist;
		this.status = status;
		this.service = service;
		this.priority = priority;
		this.modality = modality;
		this.risuser = risuser;
		this.radiologytask = radiologytask;
		this.accessionnumber = accessionnumber;
		this.scheduleddate = scheduleddate;
		this.studyuuid = studyuuid;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
	}

	public Worklist(long idworklist, Status status, Service service, Priority priority, Modality modality,
			Risuser risuser, Radiologytask radiologytask, long accessionnumber, Date scheduleddate, Date executiondate,
			String studyuuid, String studyid, Date updated, String userupdate, short active, Set<Report> reports,
			Set<Worklisttracking> worklisttrackings) {
		this.idworklist = idworklist;
		this.status = status;
		this.service = service;
		this.priority = priority;
		this.modality = modality;
		this.risuser = risuser;
		this.radiologytask = radiologytask;
		this.accessionnumber = accessionnumber;
		this.scheduleddate = scheduleddate;
		this.executiondate = executiondate;
		this.studyuuid = studyuuid;
		this.studyid = studyid;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
		this.reports = reports;
		this.worklisttrackings = worklisttrackings;
	}

	@Id

	@Column(name = "IDWORKLIST", unique = true, nullable = false, precision = 10, scale = 0)
	public long getIdworklist() {
		return this.idworklist;
	}

	public void setIdworklist(long idworklist) {
		this.idworklist = idworklist;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STATUSCODE", nullable = false)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDSERVICE", nullable = false)
	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRIORITYCODE", nullable = false)
	public Priority getPriority() {
		return this.priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDMODALITY", nullable = false)
	public Modality getModality() {
		return this.modality;
	}

	public void setModality(Modality modality) {
		this.modality = modality;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDRISUSER", nullable = false)
	public Risuser getRisuser() {
		return this.risuser;
	}

	public void setRisuser(Risuser risuser) {
		this.risuser = risuser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDRADIOLOGICTASK", nullable = false)
	public Radiologytask getRadiologytask() {
		return this.radiologytask;
	}

	public void setRadiologytask(Radiologytask radiologytask) {
		this.radiologytask = radiologytask;
	}

	@Column(name = "ACCESSIONNUMBER", nullable = false, precision = 10, scale = 0)
	public long getAccessionnumber() {
		return this.accessionnumber;
	}

	public void setAccessionnumber(long accessionnumber) {
		this.accessionnumber = accessionnumber;
	}

	@Column(name = "SCHEDULEDDATE", nullable = false)
	public Date getScheduleddate() {
		return this.scheduleddate;
	}

	public void setScheduleddate(Date scheduleddate) {
		this.scheduleddate = scheduleddate;
	}

	@Column(name = "EXECUTIONDATE")
	public Date getExecutiondate() {
		return this.executiondate;
	}

	public void setExecutiondate(Date executiondate) {
		this.executiondate = executiondate;
	}

	@Column(name = "STUDYUUID", nullable = false, length = 100)
	public String getStudyuuid() {
		return this.studyuuid;
	}

	public void setStudyuuid(String studyuuid) {
		this.studyuuid = studyuuid;
	}

	@Column(name = "STUDYID", length = 100)
	public String getStudyid() {
		return this.studyid;
	}

	public void setStudyid(String studyid) {
		this.studyid = studyid;
	}

	@Column(name = "UPDATED", nullable = false)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Column(name = "USERUPDATE", nullable = false, length = 100)
	public String getUserupdate() {
		return this.userupdate;
	}

	public void setUserupdate(String userupdate) {
		this.userupdate = userupdate;
	}

	@Column(name = "ACTIVE", nullable = false, precision = 3, scale = 0)
	public short getActive() {
		return this.active;
	}

	public void setActive(short active) {
		this.active = active;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "worklist")
	public Set<Report> getReports() {
		return this.reports;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "worklist")
	public Set<Worklisttracking> getWorklisttrackings() {
		return this.worklisttrackings;
	}

	public void setWorklisttrackings(Set<Worklisttracking> worklisttrackings) {
		this.worklisttrackings = worklisttrackings;
	}

}
