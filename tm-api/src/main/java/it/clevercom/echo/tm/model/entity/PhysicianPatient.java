package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the physician_patient database table.
 * 
 */
@Entity
@Table(name="physician_patient")
@NamedQuery(name="PhysicianPatient.findAll", query="SELECT p FROM PhysicianPatient p")
public class PhysicianPatient implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PhysicianPatientPK id;

	private Object active;

	private Timestamp created;

	private Object owner;

	private Object subscriber;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Patient
	@ManyToOne
	@JoinColumn(name="idPatient")
	private Patient patient;

	//bi-directional many-to-one association to Physician
	@ManyToOne
	@JoinColumn(name="idPhysician")
	private Physician physician;

	public PhysicianPatient() {
	}

	public PhysicianPatientPK getId() {
		return this.id;
	}

	public void setId(PhysicianPatientPK id) {
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

	public Object getOwner() {
		return this.owner;
	}

	public void setOwner(Object owner) {
		this.owner = owner;
	}

	public Object getSubscriber() {
		return this.subscriber;
	}

	public void setSubscriber(Object subscriber) {
		this.subscriber = subscriber;
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

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Physician getPhysician() {
		return this.physician;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}

}