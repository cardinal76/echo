package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the rd_hl7_patient database table.
 * 
 */
@Entity
@Table(name="rd_hl7_patient")
@NamedQuery(name="Hl7Patient.findAll", query="SELECT h FROM Hl7Patient h")
public class Hl7Patient implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Hl7PatientPK id;

	private String birthplace;

	private String citizenshipdesc;

	private String citizenshipid;

	@Temporal(TemporalType.DATE)
	private Date dateofbirth;

	@Temporal(TemporalType.DATE)
	private Date deathdate;

	private String domicilecitycode;

	private String domicilecountry;

	private String domicilestreetaddress;

	private String gender;

	private String idauthority;

	private String idtypecode;

	private String maritalstatus;

	private String messagecontrolid;

	private String name;

	private String residencecitycode;

	private String residencecountry;

	private String residencestreetaddress;

	private String surname;

	//bi-directional many-to-one association to Patient
	@ManyToOne
	@JoinColumn(name="idpatient")
	private Patient rdPatient;

	public Hl7Patient() {
	}

	public Hl7PatientPK getId() {
		return this.id;
	}

	public void setId(Hl7PatientPK id) {
		this.id = id;
	}

	public String getBirthplace() {
		return this.birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getCitizenshipdesc() {
		return this.citizenshipdesc;
	}

	public void setCitizenshipdesc(String citizenshipdesc) {
		this.citizenshipdesc = citizenshipdesc;
	}

	public String getCitizenshipid() {
		return this.citizenshipid;
	}

	public void setCitizenshipid(String citizenshipid) {
		this.citizenshipid = citizenshipid;
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

	public String getDomicilecitycode() {
		return this.domicilecitycode;
	}

	public void setDomicilecitycode(String domicilecitycode) {
		this.domicilecitycode = domicilecitycode;
	}

	public String getDomicilecountry() {
		return this.domicilecountry;
	}

	public void setDomicilecountry(String domicilecountry) {
		this.domicilecountry = domicilecountry;
	}

	public String getDomicilestreetaddress() {
		return this.domicilestreetaddress;
	}

	public void setDomicilestreetaddress(String domicilestreetaddress) {
		this.domicilestreetaddress = domicilestreetaddress;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdauthority() {
		return this.idauthority;
	}

	public void setIdauthority(String idauthority) {
		this.idauthority = idauthority;
	}

	public String getIdtypecode() {
		return this.idtypecode;
	}

	public void setIdtypecode(String idtypecode) {
		this.idtypecode = idtypecode;
	}

	public String getMaritalstatus() {
		return this.maritalstatus;
	}

	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
	}

	public String getMessagecontrolid() {
		return this.messagecontrolid;
	}

	public void setMessagecontrolid(String messagecontrolid) {
		this.messagecontrolid = messagecontrolid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResidencecitycode() {
		return this.residencecitycode;
	}

	public void setResidencecitycode(String residencecitycode) {
		this.residencecitycode = residencecitycode;
	}

	public String getResidencecountry() {
		return this.residencecountry;
	}

	public void setResidencecountry(String residencecountry) {
		this.residencecountry = residencecountry;
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

	public Patient getRdPatient() {
		return this.rdPatient;
	}

	public void setRdPatient(Patient rdPatient) {
		this.rdPatient = rdPatient;
	}

}