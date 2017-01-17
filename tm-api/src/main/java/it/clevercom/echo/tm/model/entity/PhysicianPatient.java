package it.clevercom.echo.tm.model.entity;
// Generated 17-gen-2017 15.09.29 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PhysicianPatient generated by hbm2java
 */
@Entity
@Table(name = "physician_patient", catalog = "tmdw")
public class PhysicianPatient implements java.io.Serializable {

	private PhysicianPatientId id;
	private Patient patient;
	private Physician physician;
	private Boolean owner;
	private Boolean subscriber;
	private Date created;
	private Date updated;
	private boolean active;
	private String updateUser;

	public PhysicianPatient() {
	}

	public PhysicianPatient(PhysicianPatientId id, Patient patient, Physician physician, Date created, Date updated,
			boolean active, String updateUser) {
		this.id = id;
		this.patient = patient;
		this.physician = physician;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
	}

	public PhysicianPatient(PhysicianPatientId id, Patient patient, Physician physician, Boolean owner,
			Boolean subscriber, Date created, Date updated, boolean active, String updateUser) {
		this.id = id;
		this.patient = patient;
		this.physician = physician;
		this.owner = owner;
		this.subscriber = subscriber;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idPhysician", column = @Column(name = "idPhysician", nullable = false)),
			@AttributeOverride(name = "idPatient", column = @Column(name = "idPatient", nullable = false)) })
	public PhysicianPatientId getId() {
		return this.id;
	}

	public void setId(PhysicianPatientId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPatient", nullable = false, insertable = false, updatable = false)
	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPhysician", nullable = false, insertable = false, updatable = false)
	public Physician getPhysician() {
		return this.physician;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}

	@Column(name = "owner")
	public Boolean getOwner() {
		return this.owner;
	}

	public void setOwner(Boolean owner) {
		this.owner = owner;
	}

	@Column(name = "subscriber")
	public Boolean getSubscriber() {
		return this.subscriber;
	}

	public void setSubscriber(Boolean subscriber) {
		this.subscriber = subscriber;
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
