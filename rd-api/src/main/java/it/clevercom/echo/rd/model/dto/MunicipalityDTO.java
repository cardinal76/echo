package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;

public class MunicipalityDTO implements Serializable {
	private static final long serialVersionUID = -6561565038292300914L;
	private Integer idMunicipality;
	private ProvinceDTO province;
	private String municipalityName;
	private String municipalityStdCode;
	private Date created;
	private Date updated;
	private String updateUser;
	private boolean active;
	
	/**
	 * @return the idMunicipality
	 */
	public Integer getIdMunicipality() {
		return idMunicipality;
	}
	
	/**
	 * @param idMunicipality the idMunicipality to set
	 */
	public void setIdMunicipality(Integer idMunicipality) {
		this.idMunicipality = idMunicipality;
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
	 * @return the municipalityName
	 */
	public String getMunicipalityName() {
		return municipalityName;
	}
	
	/**
	 * @param municipalityName the municipalityName to set
	 */
	public void setMunicipalityName(String municipalityName) {
		this.municipalityName = municipalityName;
	}
	
	/**
	 * @return the municipalityStdCode
	 */
	public String getMunicipalityStdCode() {
		return municipalityStdCode;
	}
	
	/**
	 * @param municipalityStdCode the municipalityStdCode to set
	 */
	public void setMunicipalityStdCode(String municipalityStdCode) {
		this.municipalityStdCode = municipalityStdCode;
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
}
