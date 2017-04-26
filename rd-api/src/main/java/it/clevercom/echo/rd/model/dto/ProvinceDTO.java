package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.clevercom.echo.common.dto.AbstractEchoDTO;

@JsonIgnoreProperties({"created","updated","userupdate","active","idd"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProvinceDTO extends AbstractEchoDTO implements Serializable {
	private static final long serialVersionUID = -358465563941917249L;
	
	private Long idprovince;
	private RegionDTO region;
	private String provincename;
	private String provincestdcode;
	private Date created;
	private Date updated;
	private String userupdate;
	private Boolean active;
	//private Set<MunicipalityDTO> municipalities = new HashSet<MunicipalityDTO>(0);

	public ProvinceDTO() {
	}

	public ProvinceDTO(Long idprovince, Date created, Date updated, String userupdate, boolean active) {
		this.idprovince = idprovince;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
	}

	/**
	 * @return the idprovince
	 */
	public Long getIdprovince() {
		return idprovince;
	}

	/**
	 * @param idprovince the idprovince to set
	 */
	public void setIdprovince(Long idprovince) {
		this.idprovince = idprovince;
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
	 * @return the provincename
	 */
	public String getProvincename() {
		return provincename;
	}

	/**
	 * @param provincename the provincename to set
	 */
	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}

	/**
	 * @return the provincestdcode
	 */
	public String getProvincestdcode() {
		return provincestdcode;
	}

	/**
	 * @param provincestdcode the provincestdcode to set
	 */
	public void setProvincestdcode(String provincestdcode) {
		this.provincestdcode = provincestdcode;
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

	@Override
	public Object getIdd() {
		return idprovince;
	}
	
}
