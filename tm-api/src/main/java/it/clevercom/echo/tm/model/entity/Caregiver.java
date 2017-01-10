package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the caregiver database table.
 * 
 */
@Entity
@Table(name="caregiver")
@NamedQuery(name="Caregiver.findAll", query="SELECT c FROM Caregiver c")
public class Caregiver implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idCareGiver;

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

	//bi-directional many-to-one association to Municipality
	@ManyToOne
	@JoinColumn(name="idMunicipality")
	private Municipality municipality;

	//bi-directional many-to-one association to PatientCaregiver
	@OneToMany(mappedBy="caregiver")
	private List<PatientCaregiver> patientCaregivers;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="idLogin")
	private Login login;

	public Caregiver() {
	}

	public int getIdCareGiver() {
		return this.idCareGiver;
	}

	public void setIdCareGiver(int idCareGiver) {
		this.idCareGiver = idCareGiver;
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
		patientCaregiver.setCaregiver(this);

		return patientCaregiver;
	}

	public PatientCaregiver removePatientCaregiver(PatientCaregiver patientCaregiver) {
		getPatientCaregivers().remove(patientCaregiver);
		patientCaregiver.setCaregiver(null);

		return patientCaregiver;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}