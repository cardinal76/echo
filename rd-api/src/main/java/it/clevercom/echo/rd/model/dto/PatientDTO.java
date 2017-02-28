package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"created","updated","userupdate","active"})
public class PatientDTO implements Serializable {
	private static final long serialVersionUID = 8178223709710995097L;
	
	private long idpatient;
	private CitizenshipDTO citizenship;
	private CountryDTO docimicileCountry;
	private CountryDTO residenceCountry;
	private CountryDTO birthplaceCountry;
	private MaritalstatusDTO maritalStatus;
	private MunicipalityDTO domicileMunicipality;
	private MunicipalityDTO residenceMunicipality;
	private MunicipalityDTO birthplaceMunicipality;
	private OrganizationUnitDTO internalOrgUnit;
	private OrganizationUnitDTO externalOrgUnit;
	private String name;
	private String surname;
	private Date dateofbirth;
	private String gender;
	private String residencestreetaddress;
	private String domicilestreetaddress;
	private Date deathdate;
	private String taxcode;
	private String phonenumber;
	private Date created;
	private Date updated;
	private String userupdate;
	private boolean active;
	private String email;
	private Set<PatientCodingActorDTO> patientCodingActors = new HashSet<PatientCodingActorDTO>(0);

	public PatientDTO() {
		super();
	}

	public PatientDTO(long idpatient, Date created, Date updated, String userupdate, boolean active) {
		this.idpatient = idpatient;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
	}

	/**
	 * @return the idpatient
	 */
	public long getIdpatient() {
		return idpatient;
	}

	/**
	 * @param idpatient the idpatient to set
	 */
	public void setIdpatient(long idpatient) {
		this.idpatient = idpatient;
	}

	/**
	 * @return the citizenship
	 */
	public CitizenshipDTO getCitizenship() {
		return citizenship;
	}

	/**
	 * @param citizenship the citizenship to set
	 */
	public void setCitizenship(CitizenshipDTO citizenship) {
		this.citizenship = citizenship;
	}

	/**
	 * @return the docimicileCountry
	 */
	public CountryDTO getDocimicileCountry() {
		return docimicileCountry;
	}

	/**
	 * @param docimicileCountry the docimicileCountry to set
	 */
	public void setDocimicileCountry(CountryDTO docimicileCountry) {
		this.docimicileCountry = docimicileCountry;
	}

	/**
	 * @return the residenceCountry
	 */
	public CountryDTO getResidenceCountry() {
		return residenceCountry;
	}

	/**
	 * @param residenceCountry the residenceCountry to set
	 */
	public void setResidenceCountry(CountryDTO residenceCountry) {
		this.residenceCountry = residenceCountry;
	}

	/**
	 * @return the birthplaceCountry
	 */
	public CountryDTO getBirthplaceCountry() {
		return birthplaceCountry;
	}

	/**
	 * @param birthplaceCountry the birthplaceCountry to set
	 */
	public void setBirthplaceCountry(CountryDTO birthplaceCountry) {
		this.birthplaceCountry = birthplaceCountry;
	}

	/**
	 * @return the maritalStatus
	 */
	public MaritalstatusDTO getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(MaritalstatusDTO maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the domicileMunicipality
	 */
	public MunicipalityDTO getDomicileMunicipality() {
		return domicileMunicipality;
	}

	/**
	 * @param domicileMunicipality the domicileMunicipality to set
	 */
	public void setDomicileMunicipality(MunicipalityDTO domicileMunicipality) {
		this.domicileMunicipality = domicileMunicipality;
	}

	/**
	 * @return the residenceMunicipality
	 */
	public MunicipalityDTO getResidenceMunicipality() {
		return residenceMunicipality;
	}

	/**
	 * @param residenceMunicipality the residenceMunicipality to set
	 */
	public void setResidenceMunicipality(MunicipalityDTO residenceMunicipality) {
		this.residenceMunicipality = residenceMunicipality;
	}

	/**
	 * @return the birthplaceMunicipality
	 */
	public MunicipalityDTO getBirthplaceMunicipality() {
		return birthplaceMunicipality;
	}

	/**
	 * @param birthplaceMunicipality the birthplaceMunicipality to set
	 */
	public void setBirthplaceMunicipality(MunicipalityDTO birthplaceMunicipality) {
		this.birthplaceMunicipality = birthplaceMunicipality;
	}

	/**
	 * @return the internalOrgUnit
	 */
	public OrganizationUnitDTO getInternalOrgUnit() {
		return internalOrgUnit;
	}

	/**
	 * @param internalOrgUnit the internalOrgUnit to set
	 */
	public void setInternalOrgUnit(OrganizationUnitDTO internalOrgUnit) {
		this.internalOrgUnit = internalOrgUnit;
	}

	/**
	 * @return the externalOrgUnit
	 */
	public OrganizationUnitDTO getExternalOrgUnit() {
		return externalOrgUnit;
	}

	/**
	 * @param externalOrgUnit the externalOrgUnit to set
	 */
	public void setExternalOrgUnit(OrganizationUnitDTO externalOrgUnit) {
		this.externalOrgUnit = externalOrgUnit;
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
	 * @return the patientCodingActors
	 */
	public Set<PatientCodingActorDTO> getPatientCodingActors() {
		return patientCodingActors;
	}

	/**
	 * @param patientCodingActors the patientCodingActors to set
	 */
	public void setPatientCodingActors(Set<PatientCodingActorDTO> patientCodingActors) {
		this.patientCodingActors = patientCodingActors;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
