package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the meetingrequest database table.
 * 
 */
@Entity
@Table(name="meetingrequest")
@NamedQuery(name="Meetingrequest.findAll", query="SELECT m FROM Meetingrequest m")
public class Meetingrequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idMeetingRequest;

	private Object active;

	private Object approved;

	private Timestamp created;

	private String meetingDate;

	private String motivation;

	private Timestamp updated;

	private Object updateUser;

	//bi-directional many-to-one association to Patient
	@ManyToOne
	@JoinColumn(name="idPatient")
	private Patient patient;

	//bi-directional many-to-one association to Physician
	@ManyToOne
	@JoinColumn(name="idPhysician")
	private Physician physician;

	public Meetingrequest() {
	}

	public int getIdMeetingRequest() {
		return this.idMeetingRequest;
	}

	public void setIdMeetingRequest(int idMeetingRequest) {
		this.idMeetingRequest = idMeetingRequest;
	}

	public Object getActive() {
		return this.active;
	}

	public void setActive(Object active) {
		this.active = active;
	}

	public Object getApproved() {
		return this.approved;
	}

	public void setApproved(Object approved) {
		this.approved = approved;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getMeetingDate() {
		return this.meetingDate;
	}

	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getMotivation() {
		return this.motivation;
	}

	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public Object getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(Object updateUser) {
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