package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;

public class ProvinceDTO implements Serializable{
	private Integer idProvince;
	private RegionDTO region;
	private String provinceName;
	private String provinceStdCode;
	private Date created;
	private Date updated;
	private String updateUser;
	private boolean active;
	
	/**
	 * @return the idProvince
	 */
	public Integer getIdProvince() {
		return idProvince;
	}
	
	/**
	 * @param idProvince the idProvince to set
	 */
	public void setIdProvince(Integer idProvince) {
		this.idProvince = idProvince;
	}
	
	/**
	 * @return the region
	 */
	public RegionDTO getRegion() {
		return region;
	}
	
	/**
	 * @param region the region to set
	 */
	public void setRegion(RegionDTO region) {
		this.region = region;
	}
	
	/**
	 * @return the provinceName
	 */
	public String getProvinceName() {
		return provinceName;
	}
	
	/**
	 * @param provinceName the provinceName to set
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	/**
	 * @return the provinceStdCode
	 */
	public String getProvinceStdCode() {
		return provinceStdCode;
	}
	
	/**
	 * @param provinceStdCode the provinceStdCode to set
	 */
	public void setProvinceStdCode(String provinceStdCode) {
		this.provinceStdCode = provinceStdCode;
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
