package it.clevercom.echo.rd.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties({"created","updated","userupdate","active"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryDTO implements java.io.Serializable {
	private static final long serialVersionUID = 41850689146052393L;
	
	private Long idcountry;
	private String countryname;
	private String countrynicename;
	private String countryiso2;
	private String countryiso3;
	private Long countryisonumcode;
	
	// transient attributes
	private Date created;
	private Date updated;
	private String userupdate;
	private Boolean active;

	public CountryDTO() {
	}

	public CountryDTO(Long idcountry, Date created, Date updated, String userupdate, boolean active) {
		this.idcountry = idcountry;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
	}

	/**
	 * @return the idcountry
	 */
	public Long getIdcountry() {
		return idcountry;
	}

	/**
	 * @param idcountry the idcountry to set
	 */
	public void setIdcountry(Long idcountry) {
		this.idcountry = idcountry;
	}

	/**
	 * @return the countryname
	 */
	public String getCountryname() {
		return countryname;
	}

	/**
	 * @param countryname the countryname to set
	 */
	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	/**
	 * @return the countrynicename
	 */
	public String getCountrynicename() {
		return countrynicename;
	}

	/**
	 * @param countrynicename the countrynicename to set
	 */
	public void setCountrynicename(String countrynicename) {
		this.countrynicename = countrynicename;
	}

	/**
	 * @return the countryiso2
	 */
	public String getCountryiso2() {
		return countryiso2;
	}

	/**
	 * @param countryiso2 the countryiso2 to set
	 */
	public void setCountryiso2(String countryiso2) {
		this.countryiso2 = countryiso2;
	}

	/**
	 * @return the countryiso3
	 */
	public String getCountryiso3() {
		return countryiso3;
	}

	/**
	 * @param countryiso3 the countryiso3 to set
	 */
	public void setCountryiso3(String countryiso3) {
		this.countryiso3 = countryiso3;
	}

	/**
	 * @return the countryisonumcode
	 */
	public Long getCountryisonumcode() {
		return countryisonumcode;
	}

	/**
	 * @param countryisonumcode the countryisonumcode to set
	 */
	public void setCountryisonumcode(Long countryisonumcode) {
		this.countryisonumcode = countryisonumcode;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
