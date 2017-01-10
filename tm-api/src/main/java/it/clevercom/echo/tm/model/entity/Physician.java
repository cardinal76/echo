package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the physician database table.
 * 
 */
@Entity
@Table(name="physician")
@NamedQuery(name="Physician.findAll", query="SELECT p FROM Physician p")
public class Physician implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPhysician;

	private Object active;

	private Timestamp created;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	private String email;

	private String gender;

	private String name;

	private String skype;

	private String surname;

	private String telephone;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Meetingrequest
	@OneToMany(mappedBy="physician")
	private List<Meetingrequest> meetingrequests;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="physician")
	private List<Message> messages;

	//bi-directional many-to-many association to Specialtytype
	@ManyToMany
	@JoinTable(
		name="physician_specialtytype"
		, joinColumns={
			@JoinColumn(name="idPhysician")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idSpecialtyType")
			}
		)
	private List<Specialtytype> specialtytypes;

	//bi-directional many-to-one association to PhysicianOrganizationunit
	@OneToMany(mappedBy="physician")
	private List<PhysicianOrganizationunit> physicianOrganizationunits;

	//bi-directional many-to-one association to PhysicianPatient
	@OneToMany(mappedBy="physician")
	private List<PhysicianPatient> physicianPatients;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="idLogin")
	private Login login;

	public Physician() {
	}

	public int getIdPhysician() {
		return this.idPhysician;
	}

	public void setIdPhysician(int idPhysician) {
		this.idPhysician = idPhysician;
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

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSkype() {
		return this.skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public List<Meetingrequest> getMeetingrequests() {
		return this.meetingrequests;
	}

	public void setMeetingrequests(List<Meetingrequest> meetingrequests) {
		this.meetingrequests = meetingrequests;
	}

	public Meetingrequest addMeetingrequest(Meetingrequest meetingrequest) {
		getMeetingrequests().add(meetingrequest);
		meetingrequest.setPhysician(this);

		return meetingrequest;
	}

	public Meetingrequest removeMeetingrequest(Meetingrequest meetingrequest) {
		getMeetingrequests().remove(meetingrequest);
		meetingrequest.setPhysician(null);

		return meetingrequest;
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setPhysician(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setPhysician(null);

		return message;
	}

	public List<Specialtytype> getSpecialtytypes() {
		return this.specialtytypes;
	}

	public void setSpecialtytypes(List<Specialtytype> specialtytypes) {
		this.specialtytypes = specialtytypes;
	}

	public List<PhysicianOrganizationunit> getPhysicianOrganizationunits() {
		return this.physicianOrganizationunits;
	}

	public void setPhysicianOrganizationunits(List<PhysicianOrganizationunit> physicianOrganizationunits) {
		this.physicianOrganizationunits = physicianOrganizationunits;
	}

	public PhysicianOrganizationunit addPhysicianOrganizationunit(PhysicianOrganizationunit physicianOrganizationunit) {
		getPhysicianOrganizationunits().add(physicianOrganizationunit);
		physicianOrganizationunit.setPhysician(this);

		return physicianOrganizationunit;
	}

	public PhysicianOrganizationunit removePhysicianOrganizationunit(PhysicianOrganizationunit physicianOrganizationunit) {
		getPhysicianOrganizationunits().remove(physicianOrganizationunit);
		physicianOrganizationunit.setPhysician(null);

		return physicianOrganizationunit;
	}

	public List<PhysicianPatient> getPhysicianPatients() {
		return this.physicianPatients;
	}

	public void setPhysicianPatients(List<PhysicianPatient> physicianPatients) {
		this.physicianPatients = physicianPatients;
	}

	public PhysicianPatient addPhysicianPatient(PhysicianPatient physicianPatient) {
		getPhysicianPatients().add(physicianPatient);
		physicianPatient.setPhysician(this);

		return physicianPatient;
	}

	public PhysicianPatient removePhysicianPatient(PhysicianPatient physicianPatient) {
		getPhysicianPatients().remove(physicianPatient);
		physicianPatient.setPhysician(null);

		return physicianPatient;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}