package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;

public class CountryDTO implements Serializable {
	private static final long serialVersionUID = -6249152659110759288L;
	private Integer idCountry;
	private String countryName;
	private String countryNicename;
	private String countryIso2;
	private String countryIso3;
	private Integer countryIsoNumCode;
	private Date created;
	private Date updated;
	private String userUpdate;
	private boolean active;
	
	/**
	 * @return the idCountry
	 */
	public Integer getIdCountry() {
		return idCountry;
	}
	
	/**
	 * @param idCountry the idCountry to set
	 */
	public void setIdCountry(Integer idCountry) {
		this.idCountry = idCountry;
	}
	
	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}
	
	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	/**
	 * @return the countryNicename
	 */
	public String getCountryNicename() {
		return countryNicename;
	}
	
	/**
	 * @param countryNicename the countryNicename to set
	 */
	public void setCountryNicename(String countryNicename) {
		this.countryNicename = countryNicename;
	}
	
	/**
	 * @return the countryIso2
	 */
	public String getCountryIso2() {
		return countryIso2;
	}
	
	/**
	 * @param countryIso2 the countryIso2 to set
	 */
	public void setCountryIso2(String countryIso2) {
		this.countryIso2 = countryIso2;
	}
	
	/**
	 * @return the countryIso3
	 */
	public String getCountryIso3() {
		return countryIso3;
	}
	
	/**
	 * @param countryIso3 the countryIso3 to set
	 */
	public void setCountryIso3(String countryIso3) {
		this.countryIso3 = countryIso3;
	}
	
	/**
	 * @return the countryIsoNumCode
	 */
	public Integer getCountryIsoNumCode() {
		return countryIsoNumCode;
	}
	
	/**
	 * @param countryIsoNumCode the countryIsoNumCode to set
	 */
	public void setCountryIsoNumCode(Integer countryIsoNumCode) {
		this.countryIsoNumCode = countryIsoNumCode;
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
