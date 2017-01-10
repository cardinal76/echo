package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the patient database table.
 * 
 */
@Entity
@Table(name="patient")
@NamedQuery(name="Patient.findAll", query="SELECT p FROM Patient p")
public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPatient;

	private Object active;

	@Temporal(TemporalType.DATE)
	private Date birthDate;

	private Timestamp created;

	private String gender;

	private String homeAddress;

	private String name;

	private String nationality;

	private String phoneNumber;

	private String surname;

	private String taxCode;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Clinicfolder
	@OneToMany(mappedBy="patient")
	private List<Clinicfolder> clinicfolders;

	//bi-directional many-to-one association to Customthreshold
	@OneToMany(mappedBy="patient")
	private List<Customthreshold> customthresholds;

	//bi-directional many-to-one association to Detectionplan
	@OneToMany(mappedBy="patient")
	private List<Detectionplan> detectionplans;

	//bi-directional many-to-one association to Meetingrequest
	@OneToMany(mappedBy="patient")
	private List<Meetingrequest> meetingrequests;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="patient")
	private List<Message> messages;

	//bi-directional many-to-many association to Nurse
	@ManyToMany(mappedBy="patients")
	private List<Nurse> nurses;

	//bi-directional many-to-one association to Municipality
	@ManyToOne
	@JoinColumn(name="idMunicipality")
	private Municipality municipality;

	//bi-directional many-to-one association to PatientCaregiver
	@OneToMany(mappedBy="patient")
	private List<PatientCaregiver> patientCaregivers;

	//bi-directional many-to-one association to PhysicianPatient
	@OneToMany(mappedBy="patient")
	private List<PhysicianPatient> physicianPatients;

	//bi-directional many-to-one association to Treatmentplan
	@OneToMany(mappedBy="patient")
	private List<Treatmentplan> treatmentplans;

	//bi-directional many-to-one association to HealthkitPatient
	@OneToMany(mappedBy="patient")
	private List<HealthkitPatient> healthkitPatients;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="idLogin")
	private Login login;

	public Patient() {
	}

	public int getIdPatient() {
		return this.idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	public Object getActive() {
		return this.active;
	}

	public void setActive(Object active) {
		this.active = active;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTaxCode() {
		return this.taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
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

	public List<Clinicfolder> getClinicfolders() {
		return this.clinicfolders;
	}

	public void setClinicfolders(List<Clinicfolder> clinicfolders) {
		this.clinicfolders = clinicfolders;
	}

	public Clinicfolder addClinicfolder(Clinicfolder clinicfolder) {
		getClinicfolders().add(clinicfolder);
		clinicfolder.setPatient(this);

		return clinicfolder;
	}

	public Clinicfolder removeClinicfolder(Clinicfolder clinicfolder) {
		getClinicfolders().remove(clinicfolder);
		clinicfolder.setPatient(null);

		return clinicfolder;
	}

	public List<Customthreshold> getCustomthresholds() {
		return this.customthresholds;
	}

	public void setCustomthresholds(List<Customthreshold> customthresholds) {
		this.customthresholds = customthresholds;
	}

	public Customthreshold addCustomthreshold(Customthreshold customthreshold) {
		getCustomthresholds().add(customthreshold);
		customthreshold.setPatient(this);

		return customthreshold;
	}

	public Customthreshold removeCustomthreshold(Customthreshold customthreshold) {
		getCustomthresholds().remove(customthreshold);
		customthreshold.setPatient(null);

		return customthreshold;
	}

	public List<Detectionplan> getDetectionplans() {
		return this.detectionplans;
	}

	public void setDetectionplans(List<Detectionplan> detectionplans) {
		this.detectionplans = detectionplans;
	}

	public Detectionplan addDetectionplan(Detectionplan detectionplan) {
		getDetectionplans().add(detectionplan);
		detectionplan.setPatient(this);

		return detectionplan;
	}

	public Detectionplan removeDetectionplan(Detectionplan detectionplan) {
		getDetectionplans().remove(detectionplan);
		detectionplan.setPatient(null);

		return detectionplan;
	}

	public List<Meetingrequest> getMeetingrequests() {
		return this.meetingrequests;
	}

	public void setMeetingrequests(List<Meetingrequest> meetingrequests) {
		this.meetingrequests = meetingrequests;
	}

	public Meetingrequest addMeetingrequest(Meetingrequest meetingrequest) {
		getMeetingrequests().add(meetingrequest);
		meetingrequest.setPatient(this);

		return meetingrequest;
	}

	public Meetingrequest removeMeetingrequest(Meetingrequest meetingrequest) {
		getMeetingrequests().remove(meetingrequest);
		meetingrequest.setPatient(null);

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
		message.setPatient(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setPatient(null);

		return message;
	}

	public List<Nurse> getNurses() {
		return this.nurses;
	}

	public void setNurses(List<Nurse> nurses) {
		this.nurses = nurses;
	}

	public Municipality getMunicipality() {
		return this.municipality;
	}

	public void setMunicipality(Municipality municipality) {
		this.municipality = municipality;
	}

	public List<PatientCaregiver> getPatientCaregivers() {
		return this.patientCaregivers;
	}

	public void setPatientCaregivers(List<PatientCaregiver> patientCaregivers) {
		this.patientCaregivers = patientCaregivers;
	}

	public PatientCaregiver addPatientCaregiver(PatientCaregiver patientCaregiver) {
		getPatientCaregivers().add(patientCaregiver);
		patientCaregiver.setPatient(this);

		return patientCaregiver;
	}

	public PatientCaregiver removePatientCaregiver(PatientCaregiver patientCaregiver) {
		getPatientCaregivers().remove(patientCaregiver);
		patientCaregiver.setPatient(null);

		return patientCaregiver;
	}

	public List<PhysicianPatient> getPhysicianPatients() {
		return this.physicianPatients;
	}

	public void setPhysicianPatients(List<PhysicianPatient> physicianPatients) {
		this.physicianPatients = physicianPatients;
	}

	public PhysicianPatient addPhysicianPatient(PhysicianPatient physicianPatient) {
		getPhysicianPatients().add(physicianPatient);
		physicianPatient.setPatient(this);

		return physicianPatient;
	}

	public PhysicianPatient removePhysicianPatient(PhysicianPatient physicianPatient) {
		getPhysicianPatients().remove(physicianPatient);
		physicianPatient.setPatient(null);

		return physicianPatient;
	}

	public List<Treatmentplan> getTreatmentplans() {
		return this.treatmentplans;
	}

	public void setTreatmentplans(List<Treatmentplan> treatmentplans) {
		this.treatmentplans = treatmentplans;
	}

	public Treatmentplan addTreatmentplan(Treatmentplan treatmentplan) {
		getTreatmentplans().add(treatmentplan);
		treatmentplan.setPatient(this);

		return treatmentplan;
	}

	public Treatmentplan removeTreatmentplan(Treatmentplan treatmentplan) {
		getTreatmentplans().remove(treatmentplan);
		treatmentplan.setPatient(null);

		return treatmentplan;
	}

	public List<HealthkitPatient> getHealthkitPatients() {
		return this.healthkitPatients;
	}

	public void setHealthkitPatients(List<HealthkitPatient> healthkitPatients) {
		this.healthkitPatients = healthkitPatients;
	}

	public HealthkitPatient addHealthkitPatient(HealthkitPatient healthkitPatient) {
		getHealthkitPatients().add(healthkitPatient);
		healthkitPatient.setPatient(this);

		return healthkitPatient;
	}

	public HealthkitPatient removeHealthkitPatient(HealthkitPatient healthkitPatient) {
		getHealthkitPatients().remove(healthkitPatient);
		healthkitPatient.setPatient(null);

		return healthkitPatient;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}