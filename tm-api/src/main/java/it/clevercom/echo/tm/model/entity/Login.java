package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the login database table.
 * 
 */
@Entity
@Table(name="login")
@NamedQuery(name="Login.findAll", query="SELECT l FROM Login l")
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idLogin;

	private Object active;

	private Object adLogon;

	private String authorities;

	private Timestamp created;

	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastPasswordReset;

	private String password;

	private Timestamp updated;

	private String updateUser;

	private String username;

	//bi-directional many-to-one association to Caregiver
	@OneToMany(mappedBy="login")
	private List<Caregiver> caregivers;

	//bi-directional many-to-one association to Languagetype
	@ManyToOne
	@JoinColumn(name="idLanguageType")
	private Languagetype languagetype;

	//bi-directional many-to-one association to Nurse
	@OneToMany(mappedBy="login")
	private List<Nurse> nurses;

	//bi-directional many-to-one association to Patient
	@OneToMany(mappedBy="login")
	private List<Patient> patients;

	//bi-directional many-to-one association to Physician
	@OneToMany(mappedBy="login")
	private List<Physician> physicians;

	//bi-directional many-to-one association to Worklist
	@OneToMany(mappedBy="login")
	private List<Worklist> worklists;

	public Login() {
	}

	public int getIdLogin() {
		return this.idLogin;
	}

	public void setIdLogin(int idLogin) {
		this.idLogin = idLogin;
	}

	public Object getActive() {
		return this.active;
	}

	public void setActive(Object active) {
		this.active = active;
	}

	public Object getAdLogon() {
		return this.adLogon;
	}

	public void setAdLogon(Object adLogon) {
		this.adLogon = adLogon;
	}

	public String getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastPasswordReset() {
		return this.lastPasswordReset;
	}

	public void setLastPasswordReset(Date lastPasswordReset) {
		this.lastPasswordReset = lastPasswordReset;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Caregiver> getCaregivers() {
		return this.caregivers;
	}

	public void setCaregivers(List<Caregiver> caregivers) {
		this.caregivers = caregivers;
	}

	public Caregiver addCaregiver(Caregiver caregiver) {
		getCaregivers().add(caregiver);
		caregiver.setLogin(this);

		return caregiver;
	}

	public Caregiver removeCaregiver(Caregiver caregiver) {
		getCaregivers().remove(caregiver);
		caregiver.setLogin(null);

		return caregiver;
	}

	public Languagetype getLanguagetype() {
		return this.languagetype;
	}

	public void setLanguagetype(Languagetype languagetype) {
		this.languagetype = languagetype;
	}

	public List<Nurse> getNurses() {
		return this.nurses;
	}

	public void setNurses(List<Nurse> nurses) {
		this.nurses = nurses;
	}

	public Nurse addNurs(Nurse nurs) {
		getNurses().add(nurs);
		nurs.setLogin(this);

		return nurs;
	}

	public Nurse removeNurs(Nurse nurs) {
		getNurses().remove(nurs);
		nurs.setLogin(null);

		return nurs;
	}

	public List<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public Patient addPatient(Patient patient) {
		getPatients().add(patient);
		patient.setLogin(this);

		return patient;
	}

	public Patient removePatient(Patient patient) {
		getPatients().remove(patient);
		patient.setLogin(null);

		return patient;
	}

	public List<Physician> getPhysicians() {
		return this.physicians;
	}

	public void setPhysicians(List<Physician> physicians) {
		this.physicians = physicians;
	}

	public Physician addPhysician(Physician physician) {
		getPhysicians().add(physician);
		physician.setLogin(this);

		return physician;
	}

	public Physician removePhysician(Physician physician) {
		getPhysicians().remove(physician);
		physician.setLogin(null);

		return physician;
	}

	public List<Worklist> getWorklists() {
		return this.worklists;
	}

	public void setWorklists(List<Worklist> worklists) {
		this.worklists = worklists;
	}

	public Worklist addWorklist(Worklist worklist) {
		getWorklists().add(worklist);
		worklist.setLogin(this);

		return worklist;
	}

	public Worklist removeWorklist(Worklist worklist) {
		getWorklists().remove(worklist);
		worklist.setLogin(null);

		return worklist;
	}

}