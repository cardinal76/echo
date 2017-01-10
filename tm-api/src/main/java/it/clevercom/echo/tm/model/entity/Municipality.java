package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the municipality database table.
 * 
 */
@Entity
@Table(name="municipality")
@NamedQuery(name="Municipality.findAll", query="SELECT m FROM Municipality m")
public class Municipality implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idMunicipality;

	private Object active;

	private Timestamp created;

	private String municipalityName;

	private String municipalityStdCode;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Caregiver
	@OneToMany(mappedBy="municipality")
	private List<Caregiver> caregivers;

	//bi-directional many-to-one association to Province
	@ManyToOne
	@JoinColumn(name="idProvince")
	private Province province;

	//bi-directional many-to-one association to Patient
	@OneToMany(mappedBy="municipality")
	private List<Patient> patients;

	public Municipality() {
	}

	public int getIdMunicipality() {
		return this.idMunicipality;
	}

	public void setIdMunicipality(int idMunicipality) {
		this.idMunicipality = idMunicipality;
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

	public String getMunicipalityName() {
		return this.municipalityName;
	}

	public void setMunicipalityName(String municipalityName) {
		this.municipalityName = municipalityName;
	}

	public String getMunicipalityStdCode() {
		return this.municipalityStdCode;
	}

	public void setMunicipalityStdCode(String municipalityStdCode) {
		this.municipalityStdCode = municipalityStdCode;
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

	public List<Caregiver> getCaregivers() {
		return this.caregivers;
	}

	public void setCaregivers(List<Caregiver> caregivers) {
		this.caregivers = caregivers;
	}

	public Caregiver addCaregiver(Caregiver caregiver) {
		getCaregivers().add(caregiver);
		caregiver.setMunicipality(this);

		return caregiver;
	}

	public Caregiver removeCaregiver(Caregiver caregiver) {
		getCaregivers().remove(caregiver);
		caregiver.setMunicipality(null);

		return caregiver;
	}

	public Province getProvince() {
		return this.province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public List<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public Patient addPatient(Patient patient) {
		getPatients().add(patient);
		patient.setMunicipality(this);

		return patient;
	}

	public Patient removePatient(Patient patient) {
		getPatients().remove(patient);
		patient.setMunicipality(null);

		return patient;
	}

}