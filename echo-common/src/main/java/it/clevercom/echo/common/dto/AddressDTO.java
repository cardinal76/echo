package it.clevercom.echo.common.dto;

import java.io.Serializable;

public class AddressDTO implements Serializable {
	private static final long serialVersionUID = 7196804921832846804L;

	private LocalityDTO locality;
	private String street;
	
	public AddressDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddressDTO(LocalityDTO locality, String street) {
		super();
		this.locality = locality;
		this.street = street;
	}

	/**
	 * @return the locality
	 */
	public LocalityDTO getLocality() {
		return locality;
	}
	
	/**
	 * @param locality the locality to set
	 */
	public void setLocality(LocalityDTO locality) {
		this.locality = locality;
	}
	
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
