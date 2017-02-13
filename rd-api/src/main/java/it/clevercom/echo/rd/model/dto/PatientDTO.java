package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PatientDTO implements Serializable {
	private static final long serialVersionUID = -5792506613248264989L;
	private Integer idPatient;
	private MunicipalityDTO municipality;
	private String name;
	private String surname;
	private Date dateOfBirth;
	private Character gender;
	private String residenceStreetAddress;
	private String residenceCityCode;
	private String residenceCountry;
	private String domicileStreetAddress;
	private String domicileCityCode;
	private String domicileCountry;
	private String maritalStatus;
	private String birthPlace;
	private String citizenshipId;
	private String citizenshipDesc;
	private Date deathDate;
	private String taxCode;
	private String nationality;
	private String phoneNumber;
	private Date created;
	private Date updated;
	private String updateUser;
	private boolean active;
	private Set<Hl7patientDTO> hl7patients = new HashSet<Hl7patientDTO>(0);
	
	/**
	 * @return the idPatient
	 */
	public Integer getIdPatient() {
		return idPatient;
	}
	
	/**
	 * @param idPatient the idPatient to set
	 */
	public void setIdPatient(Integer idPatient) {
		this.idPatient = idPatient;
	}
	
	/**
	 * @return the municipality
	 */
	public MunicipalityDTO getMunicipality() {
		return municipality;
	}
	
	/**
	 * @param municipality the municipality to set
	 */
	public void setMunicipality(MunicipalityDTO municipality) {
		this.municipality = municipality;
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
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	/**
	 * @return the gender
	 */
	public Character getGender() {
		return gender;
	}
	
	/**
	 * @param gender the gender to set
	 */
	public void setGender(Character gender) {
		this.gender = gender;
	}
	
	/**
	 * @return the residenceStreetAddress
	 */
	public String getResidenceStreetAddress() {
		return residenceStreetAddress;
	}
	
	/**
	 * @param residenceStreetAddress the residenceStreetAddress to set
	 */
	public void setResidenceStreetAddress(String residenceStreetAddress) {
		this.residenceStreetAddress = residenceStreetAddress;
	}
	
	/**
	 * @return the residenceCityCode
	 */
	public String getResidenceCityCode() {
		return residenceCityCode;
	}
	
	/**
	 * @param residenceCityCode the residenceCityCode to set
	 */
	public void setResidenceCityCode(String residenceCityCode) {
		this.residenceCityCode = residenceCityCode;
	}
	
	/**
	 * @return the residenceCountry
	 */
	public String getResidenceCountry() {
		return residenceCountry;
	}
	
	/**
	 * @param residenceCountry the residenceCountry to set
	 */
	public void setResidenceCountry(String residenceCountry) {
		this.residenceCountry = residenceCountry;
	}
	
	/**
	 * @return the domicileStreetAddress
	 */
	public String getDomicileStreetAddress() {
		return domicileStreetAddress;
	}
	
	/**
	 * @param domicileStreetAddress the domicileStreetAddress to set
	 */
	public void setDomicileStreetAddress(String domicileStreetAddress) {
		this.domicileStreetAddress = domicileStreetAddress;
	}
	
	/**
	 * @return the domicileCityCode
	 */
	public String getDomicileCityCode() {
		return domicileCityCode;
	}
	
	/**
	 * @param domicileCityCode the domicileCityCode to set
	 */
	public void setDomicileCityCode(String domicileCityCode) {
		this.domicileCityCode = domicileCityCode;
	}
	
	/**
	 * @return the domicileCountry
	 */
	public String getDomicileCountry() {
		return domicileCountry;
	}
	
	/**
	 * @param domicileCountry the domicileCountry to set
	 */
	public void setDomicileCountry(String domicileCountry) {
		this.domicileCountry = domicileCountry;
	}
	
	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}
	
	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	/**
	 * @return the birthPlace
	 */
	public String getBirthPlace() {
		return birthPlace;
	}
	
	/**
	 * @param birthPlace the birthPlace to set
	 */
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	
	/**
	 * @return the citizenshipId
	 */
	public String getCitizenshipId() {
		return citizenshipId;
	}
	
	/**
	 * @param citizenshipId the citizenshipId to set
	 */
	public void setCitizenshipId(String citizenshipId) {
		this.citizenshipId = citizenshipId;
	}
	
	/**
	 * @return the citizenshipDesc
	 */
	public String getCitizenshipDesc() {
		return citizenshipDesc;
	}
	
	/**
	 * @param citizenshipDesc the citizenshipDesc to set
	 */
	public void setCitizenshipDesc(String citizenshipDesc) {
		this.citizenshipDesc = citizenshipDesc;
	}
	
	/**
	 * @return the deathDate
	 */
	public Date getDeathDate() {
		return deathDate;
	}
	
	/**
	 * @param deathDate the deathDate to set
	 */
	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}
	
	/**
	 * @return the taxCode
	 */
	public String getTaxCode() {
		return taxCode;
	}
	
	/**
	 * @param taxCode the taxCode to set
	 */
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	
	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}
	
	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}
	
	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	
	/**
	 * @return the updated
	 */
	public Date getUpdated() {
		return updated;
	}
	
	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}
	
	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * @return the hl7patients
	 */
	public Set<Hl7patientDTO> getHl7patients() {
		return hl7patients;
	}
	
	/**
	 * @param hl7patients the hl7patients to set
	 */
	public void setHl7patients(Set<Hl7patientDTO> hl7patients) {
		this.hl7patients = hl7patients;
	}
}
