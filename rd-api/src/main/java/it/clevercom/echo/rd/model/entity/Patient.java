package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rd_patient database table.
 * 
 */
@Entity
@Table(name="rd_patient")
@NamedQuery(name="Patient.findAll", query="SELECT p FROM Patient p")
public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idpatient;

	private Boolean active;

	private Timestamp created;

	@Temporal(TemporalType.DATE)
	private Date dateofbirth;

	@Temporal(TemporalType.DATE)
	private Date deathdate;

	private String domicilestreetaddress;

	private String email;

	private String gender;

	private String name;

	private String phonenumber;

	private String residencestreetaddress;

	private String surname;

	private String taxcode;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to Hl7Patient
	@OneToMany(mappedBy="rdPatient")
	private List<Hl7Patient> rdHl7Patients;

	//bi-directional many-to-one association to Citizenship
	@ManyToOne
	@JoinColumn(name="idcitizenship")
	private Citizenship rdCitizenship;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="birthplaceidcountry")
	private Country rdCountry1;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="domicileidcountry")
	private Country rdCountry2;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="residenceidcountry")
	private Country rdCountry3;

	//bi-directional many-to-one association to Maritalstatus
	@ManyToOne
	@JoinColumn(name="codemaritalstatus")
	private Maritalstatus rdMaritalstatus;

	//bi-directional many-to-one association to Municipality
	@ManyToOne
	@JoinColumn(name="birthplaceidmunicipality")
	private Municipality rdMunicipality1;

	//bi-directional many-to-one association to Municipality
	@ManyToOne
	@JoinColumn(name="domicileidmunicipality")
	private Municipality rdMunicipality2;

	//bi-directional many-to-one association to Municipality
	@ManyToOne
	@JoinColumn(name="residenceidmunicipality")
	private Municipality rdMunicipality3;

	//bi-directional many-to-one association to OrganizationUnit
	@ManyToOne
	@JoinColumn(name="idextorganizationunit")
	private OrganizationUnit rdOrganizationUnit1;

	//bi-directional many-to-one association to OrganizationUnit
	@ManyToOne
	@JoinColumn(name="idintorganizationunit")
	private OrganizationUnit rdOrganizationUnit2;

	//bi-directional many-to-one association to PatientCodingActor
	@OneToMany(mappedBy="rdPatient")
	private List<PatientCodingActor> rdPatientCodingActors;

	//bi-directional many-to-one association to WorkSession
	@OneToMany(mappedBy="rdPatient")
	private List<WorkSession> rdWorkSessions;

	public Patient() {
	}

	public Long getIdpatient() {
		return this.idpatient;
	}

	public void setIdpatient(Long idpatient) {
		this.idpatient = idpatient;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Date getDateofbirth() {
		return this.dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public Date getDeathdate() {
		return this.deathdate;
	}

	public void setDeathdate(Date deathdate) {
		this.deathdate = deathdate;
	}

	public String getDomicilestreetaddress() {
		return this.domicilestreetaddress;
	}

	public void setDomicilestreetaddress(String domicilestreetaddress) {
		this.domicilestreetaddress = domicilestreetaddress;
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

	public String getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getResidencestreetaddress() {
		return this.residencestreetaddress;
	}

	public void setResidencestreetaddress(String residencestreetaddress) {
		this.residencestreetaddress = residencestreetaddress;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTaxcode() {
		return this.taxcode;
	}

	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getUserupdate() {
		return this.userupdate;
	}

	public void setUserupdate(String userupdate) {
		this.userupdate = userupdate;
	}

	public List<Hl7Patient> getRdHl7Patients() {
		return this.rdHl7Patients;
	}

	public void setRdHl7Patients(List<Hl7Patient> rdHl7Patients) {
		this.rdHl7Patients = rdHl7Patients;
	}

	public Hl7Patient addRdHl7Patient(Hl7Patient rdHl7Patient) {
		getRdHl7Patients().add(rdHl7Patient);
		rdHl7Patient.setRdPatient(this);

		return rdHl7Patient;
	}

	public Hl7Patient removeRdHl7Patient(Hl7Patient rdHl7Patient) {
		getRdHl7Patients().remove(rdHl7Patient);
		rdHl7Patient.setRdPatient(null);

		return rdHl7Patient;
	}

	public Citizenship getRdCitizenship() {
		return this.rdCitizenship;
	}

	public void setRdCitizenship(Citizenship rdCitizenship) {
		this.rdCitizenship = rdCitizenship;
	}

	public Country getRdCountry1() {
		return this.rdCountry1;
	}

	public void setRdCountry1(Country rdCountry1) {
		this.rdCountry1 = rdCountry1;
	}

	public Country getRdCountry2() {
		return this.rdCountry2;
	}

	public void setRdCountry2(Country rdCountry2) {
		this.rdCountry2 = rdCountry2;
	}

	public Country getRdCountry3() {
		return this.rdCountry3;
	}

	public void setRdCountry3(Country rdCountry3) {
		this.rdCountry3 = rdCountry3;
	}

	public Maritalstatus getRdMaritalstatus() {
		return this.rdMaritalstatus;
	}

	public void setRdMaritalstatus(Maritalstatus rdMaritalstatus) {
		this.rdMaritalstatus = rdMaritalstatus;
	}

	public Municipality getRdMunicipality1() {
		return this.rdMunicipality1;
	}

	public void setRdMunicipality1(Municipality rdMunicipality1) {
		this.rdMunicipality1 = rdMunicipality1;
	}

	public Municipality getRdMunicipality2() {
		return this.rdMunicipality2;
	}

	public void setRdMunicipality2(Municipality rdMunicipality2) {
		this.rdMunicipality2 = rdMunicipality2;
	}

	public Municipality getRdMunicipality3() {
		return this.rdMunicipality3;
	}

	public void setRdMunicipality3(Municipality rdMunicipality3) {
		this.rdMunicipality3 = rdMunicipality3;
	}

	public OrganizationUnit getRdOrganizationUnit1() {
		return this.rdOrganizationUnit1;
	}

	public void setRdOrganizationUnit1(OrganizationUnit rdOrganizationUnit1) {
		this.rdOrganizationUnit1 = rdOrganizationUnit1;
	}

	public OrganizationUnit getRdOrganizationUnit2() {
		return this.rdOrganizationUnit2;
	}

	public void setRdOrganizationUnit2(OrganizationUnit rdOrganizationUnit2) {
		this.rdOrganizationUnit2 = rdOrganizationUnit2;
	}

	public List<PatientCodingActor> getRdPatientCodingActors() {
		return this.rdPatientCodingActors;
	}

	public void setRdPatientCodingActors(List<PatientCodingActor> rdPatientCodingActors) {
		this.rdPatientCodingActors = rdPatientCodingActors;
	}

	public PatientCodingActor addRdPatientCodingActor(PatientCodingActor rdPatientCodingActor) {
		getRdPatientCodingActors().add(rdPatientCodingActor);
		rdPatientCodingActor.setRdPatient(this);

		return rdPatientCodingActor;
	}

	public PatientCodingActor removeRdPatientCodingActor(PatientCodingActor rdPatientCodingActor) {
		getRdPatientCodingActors().remove(rdPatientCodingActor);
		rdPatientCodingActor.setRdPatient(null);

		return rdPatientCodingActor;
	}

	public List<WorkSession> getRdWorkSessions() {
		return this.rdWorkSessions;
	}

	public void setRdWorkSessions(List<WorkSession> rdWorkSessions) {
		this.rdWorkSessions = rdWorkSessions;
	}

	public WorkSession addRdWorkSession(WorkSession rdWorkSession) {
		getRdWorkSessions().add(rdWorkSession);
		rdWorkSession.setRdPatient(this);

		return rdWorkSession;
	}

	public WorkSession removeRdWorkSession(WorkSession rdWorkSession) {
		getRdWorkSessions().remove(rdWorkSession);
		rdWorkSession.setRdPatient(null);

		return rdWorkSession;
	}

}