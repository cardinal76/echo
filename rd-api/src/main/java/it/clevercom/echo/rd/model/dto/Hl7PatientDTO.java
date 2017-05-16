package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;

import it.clevercom.echo.common.dto.AbstractEchoDTO;
import it.clevercom.echo.rd.model.entity.Patient;

public class Hl7PatientDTO extends AbstractEchoDTO implements Serializable {
	private static final long serialVersionUID = 8105708703666941232L;

	private Long idhl7patient;
	private Patient patient;
	private String idhl7authoritypatient;
	private String idauthoritynamespaceid;
	private String idauthority;
	private String idtypecode;
	private String name;
	private String surname;
	private Date dateofbirth;
	private String gender;
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
	private String messagecontrolid;
	
	@Override
	public Object getIdd() {
		return getIdhl7patient();
	}

	/**
	 * @return the idhl7patient
	 */
	public Long getIdhl7patient() {
		return idhl7patient;
	}

	/**
	 * @param idhl7patient the idhl7patient to set
	 */
	public void setIdhl7patient(Long idhl7patient) {
		this.idhl7patient = idhl7patient;
	}

	/**
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * @return the idhl7authoritypatient
	 */
	public String getIdhl7authoritypatient() {
		return idhl7authoritypatient;
	}

	/**
	 * @param idhl7authoritypatient the idhl7authoritypatient to set
	 */
	public void setIdhl7authoritypatient(String idhl7authoritypatient) {
		this.idhl7authoritypatient = idhl7authoritypatient;
	}

	/**
	 * @return the idauthoritynamespaceid
	 */
	public String getIdauthoritynamespaceid() {
		return idauthoritynamespaceid;
	}

	/**
	 * @param idauthoritynamespaceid the idauthoritynamespaceid to set
	 */
	public void setIdauthoritynamespaceid(String idauthoritynamespaceid) {
		this.idauthoritynamespaceid = idauthoritynamespaceid;
	}

	/**
	 * @return the idauthority
	 */
	public String getIdauthority() {
		return idauthority;
	}

	/**
	 * @param idauthority the idauthority to set
	 */
	public void setIdauthority(String idauthority) {
		this.idauthority = idauthority;
	}

	/**
	 * @return the idtypecode
	 */
	public String getIdtypecode() {
		return idtypecode;
	}

	/**
	 * @param idtypecode the idtypecode to set
	 */
	public void setIdtypecode(String idtypecode) {
		this.idtypecode = idtypecode;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the dateofbirth
	 */
	public Date getDateofbirth() {
		return dateofbirth;
	}

	/**
	 * @param dateofbirth the dateofbirth to set
	 */
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the residencestreetaddress
	 */
	public String getResidencestreetaddress() {
		return residencestreetaddress;
	}

	/**
	 * @param residencestreetaddress the residencestreetaddress to set
	 */
	public void setResidencestreetaddress(String residencestreetaddress) {
		this.residencestreetaddress = residencestreetaddress;
	}

	/**
	 * @return the residencecitycode
	 */
	public String getResidencecitycode() {
		return residencecitycode;
	}

	/**
	 * @param residencecitycode the residencecitycode to set
	 */
	public void setResidencecitycode(String residencecitycode) {
		this.residencecitycode = residencecitycode;
	}

	/**
	 * @return the residencecountry
	 */
	public String getResidencecountry() {
		return residencecountry;
	}

	/**
	 * @param residencecountry the residencecountry to set
	 */
	public void setResidencecountry(String residencecountry) {
		this.residencecountry = residencecountry;
	}

	/**
	 * @return the domicilestreetaddress
	 */
	public String getDomicilestreetaddress() {
		return domicilestreetaddress;
	}

	/**
	 * @param domicilestreetaddress the domicilestreetaddress to set
	 */
	public void setDomicilestreetaddress(String domicilestreetaddress) {
		this.domicilestreetaddress = domicilestreetaddress;
	}

	/**
	 * @return the domicilecitycode
	 */
	public String getDomicilecitycode() {
		return domicilecitycode;
	}

	/**
	 * @param domicilecitycode the domicilecitycode to set
	 */
	public void setDomicilecitycode(String domicilecitycode) {
		this.domicilecitycode = domicilecitycode;
	}

	/**
	 * @return the domicilecountry
	 */
	public String getDomicilecountry() {
		return domicilecountry;
	}

	/**
	 * @param domicilecountry the domicilecountry to set
	 */
	public void setDomicilecountry(String domicilecountry) {
		this.domicilecountry = domicilecountry;
	}

	/**
	 * @return the maritalstatus
	 */
	public String getMaritalstatus() {
		return maritalstatus;
	}

	/**
	 * @param maritalstatus the maritalstatus to set
	 */
	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
	}

	/**
	 * @return the birthplace
	 */
	public String getBirthplace() {
		return birthplace;
	}

	/**
	 * @param birthplace the birthplace to set
	 */
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	/**
	 * @return the citizenshipid
	 */
	public String getCitizenshipid() {
		return citizenshipid;
	}

	/**
	 * @param citizenshipid the citizenshipid to set
	 */
	public void setCitizenshipid(String citizenshipid) {
		this.citizenshipid = citizenshipid;
	}

	/**
	 * @return the citizenshipdesc
	 */
	public String getCitizenshipdesc() {
		return citizenshipdesc;
	}

	/**
	 * @param citizenshipdesc the citizenshipdesc to set
	 */
	public void setCitizenshipdesc(String citizenshipdesc) {
		this.citizenshipdesc = citizenshipdesc;
	}

	/**
	 * @return the deathdate
	 */
	public Date getDeathdate() {
		return deathdate;
	}

	/**
	 * @param deathdate the deathdate to set
	 */
	public void setDeathdate(Date deathdate) {
		this.deathdate = deathdate;
	}

	/**
	 * @return the messagecontrolid
	 */
	public String getMessagecontrolid() {
		return messagecontrolid;
	}

	/**
	 * @param messagecontrolid the messagecontrolid to set
	 */
	public void setMessagecontrolid(String messagecontrolid) {
		this.messagecontrolid = messagecontrolid;
	}
}
