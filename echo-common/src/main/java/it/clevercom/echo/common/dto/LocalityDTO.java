package it.clevercom.echo.common.dto;

import java.io.Serializable;

public class LocalityDTO implements Serializable {
	private static final long serialVersionUID = -5982900553259729774L;
	
	private BaseObjectDTO country;
	private BaseObjectDTO region;
	private BaseObjectDTO province;
	private BaseObjectDTO municipality;
	
	public LocalityDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocalityDTO(BaseObjectDTO country, BaseObjectDTO region, BaseObjectDTO province,	BaseObjectDTO municipality) {
		super();
		this.country = country;
		this.region = region;
		this.province = province;
		this.municipality = municipality;
	}

	/**
	 * @return the country
	 */
	public BaseObjectDTO getCountry() {
		return country;
	}
	
	/**
	 * @param country the country to set
	 */
	public void setCountry(BaseObjectDTO country) {
		this.country = country;
	}
	
	/**
	 * @return the region
	 */
	public BaseObjectDTO getRegion() {
		return region;
	}
	
	/**
	 * @param region the region to set
	 */
	public void setRegion(BaseObjectDTO region) {
		this.region = region;
	}
	
	/**
	 * @return the province
	 */
	public BaseObjectDTO getProvince() {
		return province;
	}
	
	/**
	 * @param province the province to set
	 */
	public void setProvince(BaseObjectDTO province) {
		this.province = province;
	}
	
	/**
	 * @return the municipality
	 */
	public BaseObjectDTO getMunicipality() {
		return municipality;
	}
	
	/**
	 * @param municipality the municipality to set
	 */
	public void setMunicipality(BaseObjectDTO municipality) {
		this.municipality = municipality;
	}
}
