package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the nurse database table.
 * 
 */
@Entity
@Table(name="nurse")
@NamedQuery(name="Nurse.findAll", query="SELECT n FROM Nurse n")
public class Nurse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idNurse;

	private Object active;

	private Timestamp created;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	private String gender;

	private String name;

	private String skype;

	private String surname;

	private String telephone;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-many association to Patient
	@ManyToMany
	@JoinTable(
		name="nurse_patient"
		, joinColumns={
			@JoinColumn(name="idNurse")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idPatient")
			}
		)
	private List<Patient> patients;

	//bi-directional many-to-one association to NurseOrganizationunit
	@OneToMany(mappedBy="nurse")
	private List<NurseOrganizationunit> nurseOrganizationunits;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="idLogin")
	private Login login;

	public Nurse() {
	}

	public int getIdNurse() {
		return this.idNurse;
	}

	public void setIdNurse(int idNurse) {
		this.idNurse = idNurse;
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

	public List<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public List<NurseOrganizationunit> getNurseOrganizationunits() {
		return this.nurseOrganizationunits;
	}

	public void setNurseOrganizationunits(List<NurseOrganizationunit> nurseOrganizationunits) {
		this.nurseOrganizationunits = nurseOrganizationunits;
	}

	public NurseOrganizationunit addNurseOrganizationunit(NurseOrganizationunit nurseOrganizationunit) {
		getNurseOrganizationunits().add(nurseOrganizationunit);
		nurseOrganizationunit.setNurse(this);

		return nurseOrganizationunit;
	}

	public NurseOrganizationunit removeNurseOrganizationunit(NurseOrganizationunit nurseOrganizationunit) {
		getNurseOrganizationunits().remove(nurseOrganizationunit);
		nurseOrganizationunit.setNurse(null);

		return nurseOrganizationunit;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}