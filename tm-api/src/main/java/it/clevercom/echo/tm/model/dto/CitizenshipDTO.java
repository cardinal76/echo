package it.clevercom.echo.tm.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.clevercom.echo.common.dto.AbstractEchoDTO;

@JsonIgnoreProperties({"created","updated","userupdate","active","idd"})
public class CitizenshipDTO extends AbstractEchoDTO implements Serializable {
	private static final long serialVersionUID = 2823533961722407809L;

	private Long idcitizenship;
	private String description;
	
	// transient attributes
	private Date created;
	private Date updated;
	private String userupdate;
	private Boolean active;

	public CitizenshipDTO() {
	}

	public CitizenshipDTO(Long idcitizenship, String description, Date created, Date updated, String userupdate, boolean active) {
		this.idcitizenship = idcitizenship;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
	}

	/**
	 * @return the idcitizenship
	 */
	public Long getIdcitizenship() {
		return idcitizenship;
	}

	/**
	 * @param idcitizenship the idcitizenship to set
	 */
	public void setIdcitizenship(Long idcitizenship) {
		this.idcitizenship = idcitizenship;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
		return getIdcitizenship();
	}
}
