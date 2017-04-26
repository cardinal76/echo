package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.clevercom.echo.common.dto.AbstractEchoDTO;

@JsonIgnoreProperties({"created","updated","userupdate","active","idd"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionDTO extends AbstractEchoDTO implements Serializable {
	private static final long serialVersionUID = 1509740953792223624L;

	private Long idregion;
	private CountryDTO country;
	private String regionname;
	private String regionstdcode;
	private Date created;
	private Date updated;
	private String userupdate;
	private Boolean active;
	//private Set<ProvinceDTO> provinces = new HashSet<ProvinceDTO>(0);

	public RegionDTO() {
	}

	public RegionDTO(Long idregion, Date created, Date updated, String userupdate, boolean active) {
		this.idregion = idregion;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
	}

	/**
	 * @return the idregion
	 */
	public Long getIdregion() {
		return idregion;
	}

	/**
	 * @param idregion the idregion to set
	 */
	public void setIdregion(Long idregion) {
		this.idregion = idregion;
	}

	/**
	 * @return the country
	 */
	public CountryDTO getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(CountryDTO country) {
		this.country = country;
	}

	/**
	 * @return the regionname
	 */
	public String getRegionname() {
		return regionname;
	}

	/**
	 * @param regionname the regionname to set
	 */
	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	/**
	 * @return the regionstdcode
	 */
	public String getRegionstdcode() {
		return regionstdcode;
	}

	/**
	 * @param regionstdcode the regionstdcode to set
	 */
	public void setRegionstdcode(String regionstdcode) {
		this.regionstdcode = regionstdcode;
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

	@Override
	public Object getIdd() {
		return idregion;
	}
}
