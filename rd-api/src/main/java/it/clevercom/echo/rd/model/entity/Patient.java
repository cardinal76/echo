package it.clevercom.echo.rd.model.entity;
// Generated 15-feb-2017 18.09.08 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Patient generated by hbm2java
 */
@Entity
@Table(name = "PATIENT")
public class Patient implements java.io.Serializable {

	private long idpatient;
	private Municipality municipality;
	private String name;
	private String surname;
	private Date dateofbirth;
	private Character gender;
	private String residencestreetaddress;
	private String residencecitycode;
	private String residencecountry;
	private String domicilestreetaddress;
	private String domicilecitycode;
	private String domicilecountry;
	private String maritalstatus;
	private String birthplace;
	private String citizenshipid;
	private String citizenshipdesc;
	private Date deathdate;
	private String taxcode;
	private String nationality;
	private String phonenumber;
	private Date created;
	private Date updated;
	private String updateuser;
	private short active;
	private Set<Hl7patient> hl7patients = new HashSet<Hl7patient>(0);
	private Set<Radiologytask> radiologytasks = new HashSet<Radiologytask>(0);

	public Patient() {
	}

	public Patient(long idpatient, Municipality municipality, Date created, Date updated, String updateuser,
			short active) {
		this.idpatient = idpatient;
		this.municipality = municipality;
		this.created = created;
		this.updated = updated;
		this.updateuser = updateuser;
		this.active = active;
	}

	public Patient(long idpatient, Municipality municipality, String name, String surname, Date dateofbirth,
			Character gender, String residencestreetaddress, String residencecitycode, String residencecountry,
			String domicilestreetaddress, String domicilecitycode, String domicilecountry, String maritalstatus,
			String birthplace, String citizenshipid, String citizenshipdesc, Date deathdate, String taxcode,
			String nationality, String phonenumber, Date created, Date updated, String updateuser, short active,
			Set<Hl7patient> hl7patients, Set<Radiologytask> radiologytasks) {
		this.idpatient = idpatient;
		this.municipality = municipality;
		this.name = name;
		this.surname = surname;
		this.dateofbirth = dateofbirth;
		this.gender = gender;
		this.residencestreetaddress = residencestreetaddress;
		this.residencecitycode = residencecitycode;
		this.residencecountry = residencecountry;
		this.domicilestreetaddress = domicilestreetaddress;
		this.domicilecitycode = domicilecitycode;
		this.domicilecountry = domicilecountry;
		this.maritalstatus = maritalstatus;
		this.birthplace = birthplace;
		this.citizenshipid = citizenshipid;
		this.citizenshipdesc = citizenshipdesc;
		this.deathdate = deathdate;
		this.taxcode = taxcode;
		this.nationality = nationality;
		this.phonenumber = phonenumber;
		this.created = created;
		this.updated = updated;
		this.updateuser = updateuser;
		this.active = active;
		this.hl7patients = hl7patients;
		this.radiologytasks = radiologytasks;
	}

	@Id

	@Column(name = "IDPATIENT", unique = true, nullable = false, precision = 10, scale = 0)
	public long getIdpatient() {
		return this.idpatient;
	}

	public void setIdpatient(long idpatient) {
		this.idpatient = idpatient;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDMUNICIPALITY", nullable = false)
	public Municipality getMunicipality() {
		return this.municipality;
	}

	public void setMunicipality(Municipality municipality) {
		this.municipality = municipality;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SURNAME", length = 100)
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATEOFBIRTH", length = 7)
	public Date getDateofbirth() {
		return this.dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	@Column(name = "GENDER", length = 1)
	public Character getGender() {
		return this.gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	@Column(name = "RESIDENCESTREETADDRESS", length = 120)
	public String getResidencestreetaddress() {
		return this.residencestreetaddress;
	}

	public void setResidencestreetaddress(String residencestreetaddress) {
		this.residencestreetaddress = residencestreetaddress;
	}

	@Column(name = "RESIDENCECITYCODE", length = 50)
	public String getResidencecitycode() {
		return this.residencecitycode;
	}

	public void setResidencecitycode(String residencecitycode) {
		this.residencecitycode = residencecitycode;
	}

	@Column(name = "RESIDENCECOUNTRY", length = 4)
	public String getResidencecountry() {
		return this.residencecountry;
	}

	public void setResidencecountry(String residencecountry) {
		this.residencecountry = residencecountry;
	}

	@Column(name = "DOMICILESTREETADDRESS", length = 120)
	public String getDomicilestreetaddress() {
		return this.domicilestreetaddress;
	}

	public void setDomicilestreetaddress(String domicilestreetaddress) {
		this.domicilestreetaddress = domicilestreetaddress;
	}

	@Column(name = "DOMICILECITYCODE", length = 50)
	public String getDomicilecitycode() {
		return this.domicilecitycode;
	}

	public void setDomicilecitycode(String domicilecitycode) {
		this.domicilecitycode = domicilecitycode;
	}

	@Column(name = "DOMICILECOUNTRY", length = 4)
	public String getDomicilecountry() {
		return this.domicilecountry;
	}

	public void setDomicilecountry(String domicilecountry) {
		this.domicilecountry = domicilecountry;
	}

	@Column(name = "MARITALSTATUS", length = 20)
	public String getMaritalstatus() {
		return this.maritalstatus;
	}

	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
	}

	@Column(name = "BIRTHPLACE", length = 250)
	public String getBirthplace() {
		return this.birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	@Column(name = "CITIZENSHIPID", length = 20)
	public String getCitizenshipid() {
		return this.citizenshipid;
	}

	public void setCitizenshipid(String citizenshipid) {
		this.citizenshipid = citizenshipid;
	}

	@Column(name = "CITIZENSHIPDESC", length = 199)
	public String getCitizenshipdesc() {
		return this.citizenshipdesc;
	}

	public void setCitizenshipdesc(String citizenshipdesc) {
		this.citizenshipdesc = citizenshipdesc;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DEATHDATE", length = 7)
	public Date getDeathdate() {
		return this.deathdate;
	}

	public void setDeathdate(Date deathdate) {
		this.deathdate = deathdate;
	}

	@Column(name = "TAXCODE", length = 50)
	public String getTaxcode() {
		return this.taxcode;
	}

	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}

	@Column(name = "NATIONALITY", length = 100)
	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name = "PHONENUMBER", length = 100)
	public String getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	@Column(name = "CREATED", nullable = false)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "UPDATED", nullable = false)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Column(name = "UPDATEUSER", nullable = false, length = 100)
	public String getUpdateuser() {
		return this.updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	@Column(name = "ACTIVE", nullable = false, precision = 3, scale = 0)
	public short getActive() {
		return this.active;
	}

	public void setActive(short active) {
		this.active = active;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	public Set<Hl7patient> getHl7patients() {
		return this.hl7patients;
	}

	public void setHl7patients(Set<Hl7patient> hl7patients) {
		this.hl7patients = hl7patients;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	public Set<Radiologytask> getRadiologytasks() {
		return this.radiologytasks;
	}

	public void setRadiologytasks(Set<Radiologytask> radiologytasks) {
		this.radiologytasks = radiologytasks;
	}

}
