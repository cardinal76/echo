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
 * PatientCaregiver generated by hbm2java
 */
@Entity
@Table(name = "patient_caregiver", catalog = "tmdw")
public class PatientCaregiver implements java.io.Serializable {

	private PatientCaregiverId id;
	private Caregiver caregiver;
	private Patient patient;
	private String relationship;
	private Date created;
	private Date updated;
	private boolean active;
	private String updateUser;

	public PatientCaregiver() {
	}

	public PatientCaregiver(PatientCaregiverId id, Caregiver caregiver, Patient patient, String relationship,
			Date created, Date updated, boolean active, String updateUser) {
		this.id = id;
		this.caregiver = caregiver;
		this.patient = patient;
		this.relationship = relationship;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idPatient", column = @Column(name = "idPatient", nullable = false)),
			@AttributeOverride(name = "idCareGiver", column = @Column(name = "idCareGiver", nullable = false)) })
	public PatientCaregiverId getId() {
		return this.id;
	}

	public void setId(PatientCaregiverId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCareGiver", nullable = false, insertable = false, updatable = false)
	public Caregiver getCaregiver() {
		return this.caregiver;
	}

	public void setCaregiver(Caregiver caregiver) {
		this.caregiver = caregiver;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPatient", nullable = false, insertable = false, updatable = false)
	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Column(name = "relationship", nullable = false, length = 11)
	public String getRelationship() {
		return this.relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
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
