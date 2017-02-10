package it.clevercom.echo.rd.model.entity;
// Generated 10-feb-2017 17.38.23 by Hibernate Tools 5.1.0.Final

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

/**
 * Worklist generated by hbm2java
 */
@Entity
@Table(name = "worklist", catalog = "rmdw")
public class Worklist implements java.io.Serializable {

	private Integer idWorklist;
	private Modality modality;
	private Priority priority;
	private Radiologictask radiologictask;
	private Service service;
	private Status status;
	private User user;
	private int accessionNumber;
	private Date scheduledDate;
	private Date executionDate;
	private String studyUuid;
	private String studyId;
	private Date updated;
	private String userUpdate;
	private boolean active;
	private Set<Worklisttracking> worklisttrackings = new HashSet<Worklisttracking>(0);
	private Set<Report> reports = new HashSet<Report>(0);

	public Worklist() {
	}

	public Worklist(Modality modality, Priority priority, Radiologictask radiologictask, Service service, Status status,
			User user, int accessionNumber, Date scheduledDate, String studyUuid, Date updated, String userUpdate,
			boolean active) {
		this.modality = modality;
		this.priority = priority;
		this.radiologictask = radiologictask;
		this.service = service;
		this.status = status;
		this.user = user;
		this.accessionNumber = accessionNumber;
		this.scheduledDate = scheduledDate;
		this.studyUuid = studyUuid;
		this.updated = updated;
		this.userUpdate = userUpdate;
		this.active = active;
	}

	public Worklist(Modality modality, Priority priority, Radiologictask radiologictask, Service service, Status status,
			User user, int accessionNumber, Date scheduledDate, Date executionDate, String studyUuid, String studyId,
			Date updated, String userUpdate, boolean active, Set<Worklisttracking> worklisttrackings,
			Set<Report> reports) {
		this.modality = modality;
		this.priority = priority;
		this.radiologictask = radiologictask;
		this.service = service;
		this.status = status;
		this.user = user;
		this.accessionNumber = accessionNumber;
		this.scheduledDate = scheduledDate;
		this.executionDate = executionDate;
		this.studyUuid = studyUuid;
		this.studyId = studyId;
		this.updated = updated;
		this.userUpdate = userUpdate;
		this.active = active;
		this.worklisttrackings = worklisttrackings;
		this.reports = reports;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idWorklist", unique = true, nullable = false)
	public Integer getIdWorklist() {
		return this.idWorklist;
	}

	public void setIdWorklist(Integer idWorklist) {
		this.idWorklist = idWorklist;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idModality", nullable = false)
	public Modality getModality() {
		return this.modality;
	}

	public void setModality(Modality modality) {
		this.modality = modality;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "priorityCode", nullable = false)
	public Priority getPriority() {
		return this.priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idRadiologicalTask", nullable = false)
	public Radiologictask getRadiologictask() {
		return this.radiologictask;
	}

	public void setRadiologictask(Radiologictask radiologictask) {
		this.radiologictask = radiologictask;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idService", nullable = false)
	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "statusCode", nullable = false)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "accessionNumber", nullable = false)
	public int getAccessionNumber() {
		return this.accessionNumber;
	}

	public void setAccessionNumber(int accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "scheduledDate", nullable = false, length = 19)
	public Date getScheduledDate() {
		return this.scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "executionDate", length = 19)
	public Date getExecutionDate() {
		return this.executionDate;
	}

	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}

	@Column(name = "studyUuid", nullable = false, length = 100)
	public String getStudyUuid() {
		return this.studyUuid;
	}

	public void setStudyUuid(String studyUuid) {
		this.studyUuid = studyUuid;
	}

	@Column(name = "studyId", length = 100)
	public String getStudyId() {
		return this.studyId;
	}

	public void setStudyId(String studyId) {
		this.studyId = studyId;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "worklist")
	public Set<Worklisttracking> getWorklisttrackings() {
		return this.worklisttrackings;
	}

	public void setWorklisttrackings(Set<Worklisttracking> worklisttrackings) {
		this.worklisttrackings = worklisttrackings;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "worklist")
	public Set<Report> getReports() {
		return this.reports;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

}
