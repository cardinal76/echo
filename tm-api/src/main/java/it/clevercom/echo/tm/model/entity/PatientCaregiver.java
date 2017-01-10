package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the patient_caregiver database table.
 * 
 */
@Entity
@Table(name="patient_caregiver")
@NamedQuery(name="PatientCaregiver.findAll", query="SELECT p FROM PatientCaregiver p")
public class PatientCaregiver implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PatientCaregiverPK id;

	private Object active;

	private Timestamp created;

	private String relationship;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Caregiver
	@ManyToOne
	@JoinColumn(name="idCareGiver")
	private Caregiver caregiver;

	//bi-directional many-to-one association to Patient
	@ManyToOne
	@JoinColumn(name="idPatient")
	private Patient patient;

	public PatientCaregiver() {
	}

	public PatientCaregiverPK getId() {
		return this.id;
	}

	public void setId(PatientCaregiverPK id) {
		this.id = id;
	}

	public Object getActive() {
		return this.active;
	}

	public void setActive(Object active) {
		this.active = active;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getRelationship() {
		return this.relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
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

	public Caregiver getCaregiver() {
		return this.caregiver;
	}

	public void setCaregiver(Caregiver caregiver) {
		this.caregiver = caregiver;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}