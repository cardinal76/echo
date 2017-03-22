package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"created","updated","userupdate","active"})
public class PatientDTO implements Serializable {
	private static final long serialVersionUID = 8178223709710995097L;
	
	private Long idpatient;	
	private String name;
	private String surname;
	private Long dateofbirth;
	private String gender;
	private String taxcode;
	private String healthCode;
	private String phonenumber;
	private String email;
	
	private Long deathdate;
	
	private Date created;
	private Date updated;
	private String userupdate;
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
	}

	public PatientDTO(Long idpatient, Date created, Date updated, String userupdate, boolean active) {
		this.idpatient = idpatient;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
	}
	
	/**
	 * @return
	 */
	public PatientDTO buildExtendedObject() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.setPrettyPrint(this.getName() + " " + this.getSurname() + " (" + df.format(this.getDateofbirth()) + ")");
		return this;
	}

	/**
	 * @return the idpatient
	 */
	public Long getIdpatient() {
		return idpatient;
	}

	/**
	 * @param idpatient the idpatient to set
	 */
	public void setIdpatient(Long idpatient) {
		this.idpatient = idpatient;
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
	public Long getDateofbirth() {
		return dateofbirth;
	}

	/**
	 * @param dateofbirth the dateofbirth to set
	 */
	public void setDateofbirth(Long dateofbirth) {
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
	 * @return the taxcode
	 */
	public String getTaxcode() {
		return taxcode;
	}

	/**
	 * @param taxcode the taxcode to set
	 */
	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
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
	 * @return the phonenumber
	 */
	public String getPhonenumber() {
		return phonenumber;
	}

	/**
	 * @param phonenumber the phonenumber to set
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
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
	 * @return the deathdate
	 */
	public Long getDeathdate() {
		return deathdate;
	}

	/**
	 * @param deathdate the deathdate to set
	 */
	public void setDeathdate(Long deathdate) {
		this.deathdate = deathdate;
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
	 * @return the userupdate
	 */
	public String getUserupdate() {
		return userupdate;
	}

	/**
	 * @param userupdate the userupdate to set
	 */
	public void setUserupdate(String userupdate) {
		this.userupdate = userupdate;
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
}
