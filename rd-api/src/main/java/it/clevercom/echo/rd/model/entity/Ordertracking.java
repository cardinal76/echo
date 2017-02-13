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
import javax.persistence.UniqueConstraint;

/**
 * Ordertracking generated by hbm2java
 */
@Entity
@Table(name = "ordertracking", catalog = "rmdw", uniqueConstraints = @UniqueConstraint(columnNames = "idRadiologicalTask"))
public class Ordertracking implements java.io.Serializable {

	private Integer idOrderTracking;
	private Order order;
	private int idRadiologicalTask;
	private String statusCode;
	private String priorityCode;
	private String acquisitionChannel;
	private Date creationDate;
	private Date scheduledDate;
	private Date acceptanceDate;
	private Integer duration;
	private int originOrganizationUnit;
	private int targetOrganizationUnit;
	private String requestingPhysician;
	private String orderReason;
	private String rejectReason;
	private String clinicalHistory;
	private String notes;
	private Date created;
	private Date updated;
	private String userUpdate;
	private boolean active;

	public Ordertracking() {
	}

	public Ordertracking(Order order, int idRadiologicalTask, String statusCode, String priorityCode,
			String acquisitionChannel, Date creationDate, int originOrganizationUnit, int targetOrganizationUnit,
			Date created, Date updated, String userUpdate, boolean active) {
		this.order = order;
		this.idRadiologicalTask = idRadiologicalTask;
		this.statusCode = statusCode;
		this.priorityCode = priorityCode;
		this.acquisitionChannel = acquisitionChannel;
		this.creationDate = creationDate;
		this.originOrganizationUnit = originOrganizationUnit;
		this.targetOrganizationUnit = targetOrganizationUnit;
		this.created = created;
		this.updated = updated;
		this.userUpdate = userUpdate;
		this.active = active;
	}

	public Ordertracking(Order order, int idRadiologicalTask, String statusCode, String priorityCode,
			String acquisitionChannel, Date creationDate, Date scheduledDate, Date acceptanceDate, Integer duration,
			int originOrganizationUnit, int targetOrganizationUnit, String requestingPhysician, String orderReason,
			String rejectReason, String clinicalHistory, String notes, Date created, Date updated, String userUpdate,
			boolean active) {
		this.order = order;
		this.idRadiologicalTask = idRadiologicalTask;
		this.statusCode = statusCode;
		this.priorityCode = priorityCode;
		this.acquisitionChannel = acquisitionChannel;
		this.creationDate = creationDate;
		this.scheduledDate = scheduledDate;
		this.acceptanceDate = acceptanceDate;
		this.duration = duration;
		this.originOrganizationUnit = originOrganizationUnit;
		this.targetOrganizationUnit = targetOrganizationUnit;
		this.requestingPhysician = requestingPhysician;
		this.orderReason = orderReason;
		this.rejectReason = rejectReason;
		this.clinicalHistory = clinicalHistory;
		this.notes = notes;
		this.created = created;
		this.updated = updated;
		this.userUpdate = userUpdate;
		this.active = active;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idOrderTracking", unique = true, nullable = false)
	public Integer getIdOrderTracking() {
		return this.idOrderTracking;
	}

	public void setIdOrderTracking(Integer idOrderTracking) {
		this.idOrderTracking = idOrderTracking;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idOrder", nullable = false)
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Column(name = "idRadiologicalTask", unique = true, nullable = false)
	public int getIdRadiologicalTask() {
		return this.idRadiologicalTask;
	}

	public void setIdRadiologicalTask(int idRadiologicalTask) {
		this.idRadiologicalTask = idRadiologicalTask;
	}

	@Column(name = "statusCode", nullable = false, length = 100)
	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@Column(name = "priorityCode", nullable = false, length = 100)
	public String getPriorityCode() {
		return this.priorityCode;
	}

	public void setPriorityCode(String priorityCode) {
		this.priorityCode = priorityCode;
	}

	@Column(name = "acquisitionChannel", nullable = false, length = 8)
	public String getAcquisitionChannel() {
		return this.acquisitionChannel;
	}

	public void setAcquisitionChannel(String acquisitionChannel) {
		this.acquisitionChannel = acquisitionChannel;
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
	@Column(name = "scheduledDate", length = 19)
	public Date getScheduledDate() {
		return this.scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "acceptanceDate", length = 19)
	public Date getAcceptanceDate() {
		return this.acceptanceDate;
	}

	public void setAcceptanceDate(Date acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	@Column(name = "duration")
	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@Column(name = "originOrganizationUnit", nullable = false)
	public int getOriginOrganizationUnit() {
		return this.originOrganizationUnit;
	}

	public void setOriginOrganizationUnit(int originOrganizationUnit) {
		this.originOrganizationUnit = originOrganizationUnit;
	}

	@Column(name = "targetOrganizationUnit", nullable = false)
	public int getTargetOrganizationUnit() {
		return this.targetOrganizationUnit;
	}

	public void setTargetOrganizationUnit(int targetOrganizationUnit) {
		this.targetOrganizationUnit = targetOrganizationUnit;
	}

	@Column(name = "requestingPhysician")
	public String getRequestingPhysician() {
		return this.requestingPhysician;
	}

	public void setRequestingPhysician(String requestingPhysician) {
		this.requestingPhysician = requestingPhysician;
	}

	@Column(name = "orderReason")
	public String getOrderReason() {
		return this.orderReason;
	}

	public void setOrderReason(String orderReason) {
		this.orderReason = orderReason;
	}

	@Column(name = "rejectReason")
	public String getRejectReason() {
		return this.rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	@Column(name = "clinicalHistory")
	public String getClinicalHistory() {
		return this.clinicalHistory;
	}

	public void setClinicalHistory(String clinicalHistory) {
		this.clinicalHistory = clinicalHistory;
	}

	@Column(name = "notes")
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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