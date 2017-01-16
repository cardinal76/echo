package it.clevercom.echo.tm.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CareGiverDTO implements Serializable {
	private static final long serialVersionUID = 9050386437727257377L;
	
	private Integer idCareGiver;
	// rettificare tipo
	private Integer login;
	// rettificare tipo
	private Integer municipality;
	private String name;
	private String surname;
	private Character gender;
	private String taxCode;
	private Date birthDate;
	private String nationality;
	private String homeAddress;
	private String phoneNumber;
	private Date created;
	private Date updated;
	private boolean active;
	private String updateUser;
	// rettificare tipo
	private Set<Integer> patientCaregivers = new HashSet<Integer>(0);

	public CareGiverDTO() {
	}

	/**
	 * @return the idCareGiver
	 */
	public Integer getIdCareGiver() {
		return idCareGiver;
	}

	/**
	 * @param idCareGiver the idCareGiver to set
	 */
	public void setIdCareGiver(Integer idCareGiver) {
		this.idCareGiver = idCareGiver;
	}

	/**
	 * @return the login
	 */
	public Integer getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(Integer login) {
		this.login = login;
	}

	/**
	 * @return the municipality
	 */
	public Integer getMunicipality() {
		return municipality;
	}

	/**
	 * @param municipality the municipality to set
	 */
	public void setMunicipality(Integer municipality) {
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
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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
	 * @return the homeAddress
	 */
	public String getHomeAddress() {
		return homeAddress;
	}

	/**
	 * @param homeAddress the homeAddress to set
	 */
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
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
	 * @return the patientCaregivers
	 */
	public Set<Integer> getPatientCaregivers() {
		return patientCaregivers;
	}

	/**
	 * @param patientCaregivers the patientCaregivers to set
	 */
	public void setPatientCaregivers(Set<Integer> patientCaregivers) {
		this.patientCaregivers = patientCaregivers;
	}
}
