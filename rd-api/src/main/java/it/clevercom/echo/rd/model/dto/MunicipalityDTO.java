package it.clevercom.echo.rd.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"created","updated","userupdate","active"})
public class MunicipalityDTO implements java.io.Serializable {
	private static final long serialVersionUID = -1870547415190618881L;

	private Long idmunicipality;
	private ProvinceDTO province;
	private String municipalityname;
	private String municipalitystdcode;
	
	// transient attributes
	private Date created;
	private Date updated;
	private String userupdate;
	private Boolean active;
	private String postalcode;

	public MunicipalityDTO() {
	}

	public MunicipalityDTO(Long idmunicipality, Date created, Date updated, String userupdate, boolean active) {
		this.idmunicipality = idmunicipality;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
	}

	/**
	 * @return the idmunicipality
	 */
	public Long getIdmunicipality() {
		return idmunicipality;
	}

	/**
	 * @param idmunicipality the idmunicipality to set
	 */
	public void setIdmunicipality(Long idmunicipality) {
		this.idmunicipality = idmunicipality;
	}

	/**
	 * @return the province
	 */
	public ProvinceDTO getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(ProvinceDTO province) {
		this.province = province;
	}

	/**
	 * @return the municipalityname
	 */
	public String getMunicipalityname() {
		return municipalityname;
	}

	/**
	 * @param municipalityname the municipalityname to set
	 */
	public void setMunicipalityname(String municipalityname) {
		this.municipalityname = municipalityname;
	}

	/**
	 * @return the municipalitystdcode
	 */
	public String getMunicipalitystdcode() {
		return municipalitystdcode;
	}

	/**
	 * @param municipalitystdcode the municipalitystdcode to set
	 */
	public void setMunicipalitystdcode(String municipalitystdcode) {
		this.municipalitystdcode = municipalitystdcode;
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
	public Boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * @return the postalcode
	 */
	public String getPostalcode() {
		return postalcode;
	}

	/**
	 * @param postalcode the postalcode to set
	 */
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
}
