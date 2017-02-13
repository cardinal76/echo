package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;

import it.clevercom.echo.rd.model.entity.Country;

public class RegionDTO implements Serializable {
	private static final long serialVersionUID = 7984610602526625802L;
	private Integer idRegion;
	private Country country;
	private String regionName;
	private String regionStdCode;
	private Date created;
	private Date updated;
	private String userUpdate;
	private boolean active;
	
	/**
	 * @return the idRegion
	 */
	public Integer getIdRegion() {
		return idRegion;
	}
	
	/**
	 * @param idRegion the idRegion to set
	 */
	public void setIdRegion(Integer idRegion) {
		this.idRegion = idRegion;
	}
	
	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}
	
	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}
	
	/**
	 * @return the regionName
	 */
	public String getRegionName() {
		return regionName;
	}
	
	/**
	 * @param regionName the regionName to set
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	/**
	 * @return the regionStdCode
	 */
	public String getRegionStdCode() {
		return regionStdCode;
	}
	
	/**
	 * @param regionStdCode the regionStdCode to set
	 */
	public void setRegionStdCode(String regionStdCode) {
		this.regionStdCode = regionStdCode;
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
