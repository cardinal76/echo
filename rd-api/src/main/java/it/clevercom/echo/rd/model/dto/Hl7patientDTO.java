package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;

import it.clevercom.echo.rd.model.entity.Patient;

public class Hl7patientDTO implements Serializable {
	private static final long serialVersionUID = -7876807585618205400L;
	
	// composite key
	private String idPatientPid3Cx1;
	private String idAuthorityNamespaceIdPid3Cx4Hd1;
	
	private Patient patient;
	private String idAuthorityPid3Cx4Hd2;
	private String idTypeCodePid3Cx5;
	private String namePid5Xpn2;
	private String surnamePid5Xpn1;
	private Date dateOfBirthPid7Ts1;
	private Character genderPid8;
	private String residenceStreetAddressPid11Xad1Sad1;
	private String residenceCityCodePid11Xad3;
	private String residenceCountryPid11Xad6;
	private String domicileStreetAddressPid11Xad1Sad1;
	private String domicileCityCodePid11Xad3;
	private String domicileCountryPid11Xad6;
	private String maritalStatusPid16Ce1;
	private String birthPlacePid23;
	private String citizenshipIdPid26Ce1;
	private String citizenshipDescPid26Ce2;
	private Date deathDatePid29Ts1;
	private String messageControlId;
	
	/**
	 * @return the idPatientPid3Cx1
	 */
	public String getIdPatientPid3Cx1() {
		return idPatientPid3Cx1;
	}
	
	/**
	 * @param idPatientPid3Cx1 the idPatientPid3Cx1 to set
	 */
	public void setIdPatientPid3Cx1(String idPatientPid3Cx1) {
		this.idPatientPid3Cx1 = idPatientPid3Cx1;
	}
	
	/**
	 * @return the idAuthorityNamespaceIdPid3Cx4Hd1
	 */
	public String getIdAuthorityNamespaceIdPid3Cx4Hd1() {
		return idAuthorityNamespaceIdPid3Cx4Hd1;
	}
	
	/**
	 * @param idAuthorityNamespaceIdPid3Cx4Hd1 the idAuthorityNamespaceIdPid3Cx4Hd1 to set
	 */
	public void setIdAuthorityNamespaceIdPid3Cx4Hd1(String idAuthorityNamespaceIdPid3Cx4Hd1) {
		this.idAuthorityNamespaceIdPid3Cx4Hd1 = idAuthorityNamespaceIdPid3Cx4Hd1;
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
	 * @return the idAuthorityPid3Cx4Hd2
	 */
	public String getIdAuthorityPid3Cx4Hd2() {
		return idAuthorityPid3Cx4Hd2;
	}
	
	/**
	 * @param idAuthorityPid3Cx4Hd2 the idAuthorityPid3Cx4Hd2 to set
	 */
	public void setIdAuthorityPid3Cx4Hd2(String idAuthorityPid3Cx4Hd2) {
		this.idAuthorityPid3Cx4Hd2 = idAuthorityPid3Cx4Hd2;
	}
	
	/**
	 * @return the idTypeCodePid3Cx5
	 */
	public String getIdTypeCodePid3Cx5() {
		return idTypeCodePid3Cx5;
	}
	
	/**
	 * @param idTypeCodePid3Cx5 the idTypeCodePid3Cx5 to set
	 */
	public void setIdTypeCodePid3Cx5(String idTypeCodePid3Cx5) {
		this.idTypeCodePid3Cx5 = idTypeCodePid3Cx5;
	}
	
	/**
	 * @return the namePid5Xpn2
	 */
	public String getNamePid5Xpn2() {
		return namePid5Xpn2;
	}
	
	/**
	 * @param namePid5Xpn2 the namePid5Xpn2 to set
	 */
	public void setNamePid5Xpn2(String namePid5Xpn2) {
		this.namePid5Xpn2 = namePid5Xpn2;
	}
	
	/**
	 * @return the surnamePid5Xpn1
	 */
	public String getSurnamePid5Xpn1() {
		return surnamePid5Xpn1;
	}
	
	/**
	 * @param surnamePid5Xpn1 the surnamePid5Xpn1 to set
	 */
	public void setSurnamePid5Xpn1(String surnamePid5Xpn1) {
		this.surnamePid5Xpn1 = surnamePid5Xpn1;
	}
	
	/**
	 * @return the dateOfBirthPid7Ts1
	 */
	public Date getDateOfBirthPid7Ts1() {
		return dateOfBirthPid7Ts1;
	}
	
	/**
	 * @param dateOfBirthPid7Ts1 the dateOfBirthPid7Ts1 to set
	 */
	public void setDateOfBirthPid7Ts1(Date dateOfBirthPid7Ts1) {
		this.dateOfBirthPid7Ts1 = dateOfBirthPid7Ts1;
	}
	
	/**
	 * @return the genderPid8
	 */
	public Character getGenderPid8() {
		return genderPid8;
	}
	
	/**
	 * @param genderPid8 the genderPid8 to set
	 */
	public void setGenderPid8(Character genderPid8) {
		this.genderPid8 = genderPid8;
	}
	
	/**
	 * @return the residenceStreetAddressPid11Xad1Sad1
	 */
	public String getResidenceStreetAddressPid11Xad1Sad1() {
		return residenceStreetAddressPid11Xad1Sad1;
	}
	
	/**
	 * @param residenceStreetAddressPid11Xad1Sad1 the residenceStreetAddressPid11Xad1Sad1 to set
	 */
	public void setResidenceStreetAddressPid11Xad1Sad1(String residenceStreetAddressPid11Xad1Sad1) {
		this.residenceStreetAddressPid11Xad1Sad1 = residenceStreetAddressPid11Xad1Sad1;
	}
	
	/**
	 * @return the residenceCityCodePid11Xad3
	 */
	public String getResidenceCityCodePid11Xad3() {
		return residenceCityCodePid11Xad3;
	}
	
	/**
	 * @param residenceCityCodePid11Xad3 the residenceCityCodePid11Xad3 to set
	 */
	public void setResidenceCityCodePid11Xad3(String residenceCityCodePid11Xad3) {
		this.residenceCityCodePid11Xad3 = residenceCityCodePid11Xad3;
	}
	
	/**
	 * @return the residenceCountryPid11Xad6
	 */
	public String getResidenceCountryPid11Xad6() {
		return residenceCountryPid11Xad6;
	}
	
	/**
	 * @param residenceCountryPid11Xad6 the residenceCountryPid11Xad6 to set
	 */
	public void setResidenceCountryPid11Xad6(String residenceCountryPid11Xad6) {
		this.residenceCountryPid11Xad6 = residenceCountryPid11Xad6;
	}
	
	/**
	 * @return the domicileStreetAddressPid11Xad1Sad1
	 */
	public String getDomicileStreetAddressPid11Xad1Sad1() {
		return domicileStreetAddressPid11Xad1Sad1;
	}
	
	/**
	 * @param domicileStreetAddressPid11Xad1Sad1 the domicileStreetAddressPid11Xad1Sad1 to set
	 */
	public void setDomicileStreetAddressPid11Xad1Sad1(String domicileStreetAddressPid11Xad1Sad1) {
		this.domicileStreetAddressPid11Xad1Sad1 = domicileStreetAddressPid11Xad1Sad1;
	}
	
	/**
	 * @return the domicileCityCodePid11Xad3
	 */
	public String getDomicileCityCodePid11Xad3() {
		return domicileCityCodePid11Xad3;
	}
	
	/**
	 * @param domicileCityCodePid11Xad3 the domicileCityCodePid11Xad3 to set
	 */
	public void setDomicileCityCodePid11Xad3(String domicileCityCodePid11Xad3) {
		this.domicileCityCodePid11Xad3 = domicileCityCodePid11Xad3;
	}
	
	/**
	 * @return the domicileCountryPid11Xad6
	 */
	public String getDomicileCountryPid11Xad6() {
		return domicileCountryPid11Xad6;
	}
	
	/**
	 * @param domicileCountryPid11Xad6 the domicileCountryPid11Xad6 to set
	 */
	public void setDomicileCountryPid11Xad6(String domicileCountryPid11Xad6) {
		this.domicileCountryPid11Xad6 = domicileCountryPid11Xad6;
	}
	
	/**
	 * @return the maritalStatusPid16Ce1
	 */
	public String getMaritalStatusPid16Ce1() {
		return maritalStatusPid16Ce1;
	}
	
	/**
	 * @param maritalStatusPid16Ce1 the maritalStatusPid16Ce1 to set
	 */
	public void setMaritalStatusPid16Ce1(String maritalStatusPid16Ce1) {
		this.maritalStatusPid16Ce1 = maritalStatusPid16Ce1;
	}
	
	/**
	 * @return the birthPlacePid23
	 */
	public String getBirthPlacePid23() {
		return birthPlacePid23;
	}
	
	/**
	 * @param birthPlacePid23 the birthPlacePid23 to set
	 */
	public void setBirthPlacePid23(String birthPlacePid23) {
		this.birthPlacePid23 = birthPlacePid23;
	}
	
	/**
	 * @return the citizenshipIdPid26Ce1
	 */
	public String getCitizenshipIdPid26Ce1() {
		return citizenshipIdPid26Ce1;
	}
	
	/**
	 * @param citizenshipIdPid26Ce1 the citizenshipIdPid26Ce1 to set
	 */
	public void setCitizenshipIdPid26Ce1(String citizenshipIdPid26Ce1) {
		this.citizenshipIdPid26Ce1 = citizenshipIdPid26Ce1;
	}
	
	/**
	 * @return the citizenshipDescPid26Ce2
	 */
	public String getCitizenshipDescPid26Ce2() {
		return citizenshipDescPid26Ce2;
	}
	
	/**
	 * @param citizenshipDescPid26Ce2 the citizenshipDescPid26Ce2 to set
	 */
	public void setCitizenshipDescPid26Ce2(String citizenshipDescPid26Ce2) {
		this.citizenshipDescPid26Ce2 = citizenshipDescPid26Ce2;
	}
	
	/**
	 * @return the deathDatePid29Ts1
	 */
	public Date getDeathDatePid29Ts1() {
		return deathDatePid29Ts1;
	}
	
	/**
	 * @param deathDatePid29Ts1 the deathDatePid29Ts1 to set
	 */
	public void setDeathDatePid29Ts1(Date deathDatePid29Ts1) {
		this.deathDatePid29Ts1 = deathDatePid29Ts1;
	}
	
	/**
	 * @return the messageControlId
	 */
	public String getMessageControlId() {
		return messageControlId;
	}
	
	/**
	 * @param messageControlId the messageControlId to set
	 */
	public void setMessageControlId(String messageControlId) {
		this.messageControlId = messageControlId;
	}
}
