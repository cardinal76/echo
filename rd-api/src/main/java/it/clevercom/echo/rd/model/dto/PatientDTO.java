package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"created","updated","userUpdate","active"})
public class PatientDTO implements Serializable {
	private static final long serialVersionUID = 8178223709710995097L;
	
	private Long idPatient;	
	private String name;
	private String surname;
	private Long dateOfBirth;
	private String gender;
	private String taxCode;
	private String healthCode;
    private String fullname;
	private String phoneNumber;
	private String email;
	
	private Long deathDate;
	
	private Date created;
	private Date updated;
	private String userUpdate;
	private Boolean active;
	
	private String prettyPrint;
	
	private BaseObjectDTO citizenship;
	private BaseObjectDTO maritalStatus;
	private BaseObjectDTO intOrganizationUnit;
	private BaseObjectDTO extOrganizationUnit;
	private LocalityDTO birthPlace;
	private AddressDTO residence;
	private AddressDTO domicile;
	
	public PatientDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return
	 */
	public PatientDTO buildExtendedObject() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.setPrettyPrint(this.getName() + " " + this.getSurname() + " (" + ((this.getDateOfBirth()!=null) ? df.format(new Date(this.getDateOfBirth()))  : "--/--/----") + ")");
		return this;
	}

	/**
	 * @return the idPatient
	 */
	public Long getIdPatient() {
		return idPatient;
	}

	/**
	 * @param idPatient the idPatient to set
	 */
	public void setIdPatient(Long idPatient) {
		this.idPatient = idPatient;
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
	public Long getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	 * @return the healthCode
	 */
	public String getHealthCode() {
		return healthCode;
	}

	/**
	 * @param healthCode the healthCode to set
	 */
	public void setHealthCode(String healthCode) {
		this.healthCode = healthCode;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the deathDate
	 */
	public Long getDeathDate() {
		return deathDate;
	}

	/**
	 * @param deathDate the deathDate to set
	 */
	public void setDeathDate(Long deathDate) {
		this.deathDate = deathDate;
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
	 * @return the userUpdate
	 */
	public String getUserUpdate() {
		return userUpdate;
	}

	/**
	 * @param userUpdate the userUpdate to set
	 */
	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * @return the prettyPrint
	 */
	public String getPrettyPrint() {
		return prettyPrint;
	}

	/**
	 * @param prettyPrint the prettyPrint to set
	 */
	public void setPrettyPrint(String prettyPrint) {
		this.prettyPrint = prettyPrint;
	}

	/**
	 * @return the citizenship
	 */
	public BaseObjectDTO getCitizenship() {
		return citizenship;
	}

	/**
	 * @param citizenship the citizenship to set
	 */
	public void setCitizenship(BaseObjectDTO citizenship) {
		this.citizenship = citizenship;
	}

	/**
	 * @return the maritalStatus
	 */
	public BaseObjectDTO getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(BaseObjectDTO maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the intOrganizationUnit
	 */
	public BaseObjectDTO getIntOrganizationUnit() {
		return intOrganizationUnit;
	}

	/**
	 * @param intOrganizationUnit the intOrganizationUnit to set
	 */
	public void setIntOrganizationUnit(BaseObjectDTO intOrganizationUnit) {
		this.intOrganizationUnit = intOrganizationUnit;
	}

	/**
	 * @return the extOrganizationUnit
	 */
	public BaseObjectDTO getExtOrganizationUnit() {
		return extOrganizationUnit;
	}

	/**
	 * @param extOrganizationUnit the extOrganizationUnit to set
	 */
	public void setExtOrganizationUnit(BaseObjectDTO extOrganizationUnit) {
		this.extOrganizationUnit = extOrganizationUnit;
	}

	/**
	 * @return the birthPlace
	 */
	public LocalityDTO getBirthPlace() {
		return birthPlace;
	}

	/**
	 * @param birthPlace the birthPlace to set
	 */
	public void setBirthPlace(LocalityDTO birthPlace) {
		this.birthPlace = birthPlace;
	}

	/**
	 * @return the residence
	 */
	public AddressDTO getResidence() {
		return residence;
	}

	/**
	 * @param residence the residence to set
	 */
	public void setResidence(AddressDTO residence) {
		this.residence = residence;
	}

	/**
	 * @return the domicile
	 */
	public AddressDTO getDomicile() {
		return domicile;
	}

	/**
	 * @param domicile the domicile to set
	 */
	public void setDomicile(AddressDTO domicile) {
		this.domicile = domicile;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
}
