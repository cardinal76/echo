package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the healthkit_patient database table.
 * 
 */
@Entity
@Table(name="healthkit_patient")
@NamedQuery(name="HealthkitPatient.findAll", query="SELECT h FROM HealthkitPatient h")
public class HealthkitPatient implements Serializable {
	private static final long serialVersionUID = 1L;

	private byte active;

	@Temporal(TemporalType.TIMESTAMP)
	private Date assignmentDate;

	private Timestamp created;

	@Temporal(TemporalType.TIMESTAMP)
	private Date restitutionDate;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Healthkit
	@ManyToOne
	@JoinColumn(name="idHealthKit")
	private Healthkit healthkit;

	//bi-directional many-to-one association to Patient
	@ManyToOne
	@JoinColumn(name="idPatient")
	private Patient patient;

	public HealthkitPatient() {
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public Date getAssignmentDate() {
		return this.assignmentDate;
	}

	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Date getRestitutionDate() {
		return this.restitutionDate;
	}

	public void setRestitutionDate(Date restitutionDate) {
		this.restitutionDate = restitutionDate;
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

	public Healthkit getHealthkit() {
		return this.healthkit;
	}

	public void setHealthkit(Healthkit healthkit) {
		this.healthkit = healthkit;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}