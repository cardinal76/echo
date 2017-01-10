package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the message database table.
 * 
 */
@Entity
@Table(name="message")
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idMessage;

	private Object active;

	private String attachmentURL;

	private Timestamp created;

	private Object message;

	@Temporal(TemporalType.TIMESTAMP)
	private Date readedByPatient;

	@Temporal(TemporalType.TIMESTAMP)
	private Date readedByPhysician;

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

	public Message() {
	}

	public int getIdMessage() {
		return this.idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}

	public Object getActive() {
		return this.active;
	}

	public void setActive(Object active) {
		this.active = active;
	}

	public String getAttachmentURL() {
		return this.attachmentURL;
	}

	public void setAttachmentURL(String attachmentURL) {
		this.attachmentURL = attachmentURL;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Object getMessage() {
		return this.message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public Date getReadedByPatient() {
		return this.readedByPatient;
	}

	public void setReadedByPatient(Date readedByPatient) {
		this.readedByPatient = readedByPatient;
	}

	public Date getReadedByPhysician() {
		return this.readedByPhysician;
	}

	public void setReadedByPhysician(Date readedByPhysician) {
		this.readedByPhysician = readedByPhysician;
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