package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.clevercom.echo.common.dto.AbstractEchoDTO;
import it.clevercom.echo.rd.model.entity.Patient;

@JsonIgnoreProperties({"created","updated","userupdate","active","idd"})
public class Hl7PatientDTO extends AbstractEchoDTO implements Serializable {
	private static final long serialVersionUID = 8105708703666941232L;

	private Long idhl7patient;
    private Patient patient;
    private String teamIdPatient;
    private String teamAuthorityNsId;
    private String teamAuthorityUid;
    private String teamIdtypecode;
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
    private String cfIdPatient;
    private String cfAuthorityNsId;
    private String cfAuthorityUid;
    private String cfIdtypecode;
    private String stpIdPatient;
    private String stpAuthorityNsId;
    private String stpAuthorityUid;
    private String stpIdtypecode;
    private String eniIdPatient;
    private String eniAuthorityNsId;
    private String eniAuthorityUid;
    private String eniIdtypecode;
    private String ccIdPatient;
    private String ccAuthorityNsId;
    private String ccAuthorityUid;
    private String ccIdtypecode;
    private String pkIdPatient;
    private String pkAuthorityNsId;
    private String pkAuthorityUid;
    private String pkIdtypecode;
	
	// transient attributes
	private Date created;
	private Date updated;
	private String userupdate;
	private Boolean active;
	
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
	 * @return the teamIdPatient
	 */
	public String getTeamIdPatient() {
		return teamIdPatient;
	}

	/**
	 * @param teamIdPatient the teamIdPatient to set
	 */
	public void setTeamIdPatient(String teamIdPatient) {
		this.teamIdPatient = teamIdPatient;
	}

	/**
	 * @return the teamAuthorityNsId
	 */
	public String getTeamAuthorityNsId() {
		return teamAuthorityNsId;
	}

	/**
	 * @param teamAuthorityNsId the teamAuthorityNsId to set
	 */
	public void setTeamAuthorityNsId(String teamAuthorityNsId) {
		this.teamAuthorityNsId = teamAuthorityNsId;
	}

	/**
	 * @return the teamAuthorityUid
	 */
	public String getTeamAuthorityUid() {
		return teamAuthorityUid;
	}

	/**
	 * @param teamAuthorityUid the teamAuthorityUid to set
	 */
	public void setTeamAuthorityUid(String teamAuthorityUid) {
		this.teamAuthorityUid = teamAuthorityUid;
	}

	/**
	 * @return the teamIdtypecode
	 */
	public String getTeamIdtypecode() {
		return teamIdtypecode;
	}

	/**
	 * @param teamIdtypecode the teamIdtypecode to set
	 */
	public void setTeamIdtypecode(String teamIdtypecode) {
		this.teamIdtypecode = teamIdtypecode;
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

	/**
	 * @return the cfIdPatient
	 */
	public String getCfIdPatient() {
		return cfIdPatient;
	}

	/**
	 * @param cfIdPatient the cfIdPatient to set
	 */
	public void setCfIdPatient(String cfIdPatient) {
		this.cfIdPatient = cfIdPatient;
	}

	/**
	 * @return the cfAuthorityNsId
	 */
	public String getCfAuthorityNsId() {
		return cfAuthorityNsId;
	}

	/**
	 * @param cfAuthorityNsId the cfAuthorityNsId to set
	 */
	public void setCfAuthorityNsId(String cfAuthorityNsId) {
		this.cfAuthorityNsId = cfAuthorityNsId;
	}

	/**
	 * @return the cfAuthorityUid
	 */
	public String getCfAuthorityUid() {
		return cfAuthorityUid;
	}

	/**
	 * @param cfAuthorityUid the cfAuthorityUid to set
	 */
	public void setCfAuthorityUid(String cfAuthorityUid) {
		this.cfAuthorityUid = cfAuthorityUid;
	}

	/**
	 * @return the cfIdtypecode
	 */
	public String getCfIdtypecode() {
		return cfIdtypecode;
	}

	/**
	 * @param cfIdtypecode the cfIdtypecode to set
	 */
	public void setCfIdtypecode(String cfIdtypecode) {
		this.cfIdtypecode = cfIdtypecode;
	}

	/**
	 * @return the stpIdPatient
	 */
	public String getStpIdPatient() {
		return stpIdPatient;
	}

	/**
	 * @param stpIdPatient the stpIdPatient to set
	 */
	public void setStpIdPatient(String stpIdPatient) {
		this.stpIdPatient = stpIdPatient;
	}

	/**
	 * @return the stpAuthorityNsId
	 */
	public String getStpAuthorityNsId() {
		return stpAuthorityNsId;
	}

	/**
	 * @param stpAuthorityNsId the stpAuthorityNsId to set
	 */
	public void setStpAuthorityNsId(String stpAuthorityNsId) {
		this.stpAuthorityNsId = stpAuthorityNsId;
	}

	/**
	 * @return the stpAuthorityUid
	 */
	public String getStpAuthorityUid() {
		return stpAuthorityUid;
	}

	/**
	 * @param stpAuthorityUid the stpAuthorityUid to set
	 */
	public void setStpAuthorityUid(String stpAuthorityUid) {
		this.stpAuthorityUid = stpAuthorityUid;
	}

	/**
	 * @return the stpIdtypecode
	 */
	public String getStpIdtypecode() {
		return stpIdtypecode;
	}

	/**
	 * @param stpIdtypecode the stpIdtypecode to set
	 */
	public void setStpIdtypecode(String stpIdtypecode) {
		this.stpIdtypecode = stpIdtypecode;
	}

	/**
	 * @return the eniIdPatient
	 */
	public String getEniIdPatient() {
		return eniIdPatient;
	}

	/**
	 * @param eniIdPatient the eniIdPatient to set
	 */
	public void setEniIdPatient(String eniIdPatient) {
		this.eniIdPatient = eniIdPatient;
	}

	/**
	 * @return the eniAuthorityNsId
	 */
	public String getEniAuthorityNsId() {
		return eniAuthorityNsId;
	}

	/**
	 * @param eniAuthorityNsId the eniAuthorityNsId to set
	 */
	public void setEniAuthorityNsId(String eniAuthorityNsId) {
		this.eniAuthorityNsId = eniAuthorityNsId;
	}

	/**
	 * @return the eniAuthorityUid
	 */
	public String getEniAuthorityUid() {
		return eniAuthorityUid;
	}

	/**
	 * @param eniAuthorityUid the eniAuthorityUid to set
	 */
	public void setEniAuthorityUid(String eniAuthorityUid) {
		this.eniAuthorityUid = eniAuthorityUid;
	}

	/**
	 * @return the eniIdtypecode
	 */
	public String getEniIdtypecode() {
		return eniIdtypecode;
	}

	/**
	 * @param eniIdtypecode the eniIdtypecode to set
	 */
	public void setEniIdtypecode(String eniIdtypecode) {
		this.eniIdtypecode = eniIdtypecode;
	}

	/**
	 * @return the ccIdPatient
	 */
	public String getCcIdPatient() {
		return ccIdPatient;
	}

	/**
	 * @param ccIdPatient the ccIdPatient to set
	 */
	public void setCcIdPatient(String ccIdPatient) {
		this.ccIdPatient = ccIdPatient;
	}

	/**
	 * @return the ccAuthorityNsId
	 */
	public String getCcAuthorityNsId() {
		return ccAuthorityNsId;
	}

	/**
	 * @param ccAuthorityNsId the ccAuthorityNsId to set
	 */
	public void setCcAuthorityNsId(String ccAuthorityNsId) {
		this.ccAuthorityNsId = ccAuthorityNsId;
	}

	/**
	 * @return the ccAuthorityUid
	 */
	public String getCcAuthorityUid() {
		return ccAuthorityUid;
	}

	/**
	 * @param ccAuthorityUid the ccAuthorityUid to set
	 */
	public void setCcAuthorityUid(String ccAuthorityUid) {
		this.ccAuthorityUid = ccAuthorityUid;
	}

	/**
	 * @return the ccIdtypecode
	 */
	public String getCcIdtypecode() {
		return ccIdtypecode;
	}

	/**
	 * @param ccIdtypecode the ccIdtypecode to set
	 */
	public void setCcIdtypecode(String ccIdtypecode) {
		this.ccIdtypecode = ccIdtypecode;
	}

	/**
	 * @return the pkIdPatient
	 */
	public String getPkIdPatient() {
		return pkIdPatient;
	}

	/**
	 * @param pkIdPatient the pkIdPatient to set
	 */
	public void setPkIdPatient(String pkIdPatient) {
		this.pkIdPatient = pkIdPatient;
	}

	/**
	 * @return the pkAuthorityNsId
	 */
	public String getPkAuthorityNsId() {
		return pkAuthorityNsId;
	}

	/**
	 * @param pkAuthorityNsId the pkAuthorityNsId to set
	 */
	public void setPkAuthorityNsId(String pkAuthorityNsId) {
		this.pkAuthorityNsId = pkAuthorityNsId;
	}

	/**
	 * @return the pkAuthorityUid
	 */
	public String getPkAuthorityUid() {
		return pkAuthorityUid;
	}

	/**
	 * @param pkAuthorityUid the pkAuthorityUid to set
	 */
	public void setPkAuthorityUid(String pkAuthorityUid) {
		this.pkAuthorityUid = pkAuthorityUid;
	}

	/**
	 * @return the pkIdtypecode
	 */
	public String getPkIdtypecode() {
		return pkIdtypecode;
	}

	/**
	 * @param pkIdtypecode the pkIdtypecode to set
	 */
	public void setPkIdtypecode(String pkIdtypecode) {
		this.pkIdtypecode = pkIdtypecode;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
