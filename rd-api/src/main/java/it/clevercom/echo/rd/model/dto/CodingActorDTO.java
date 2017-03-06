package it.clevercom.echo.rd.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"created","updated","userupdate","active"})
public class CodingActorDTO implements java.io.Serializable {
	private static final long serialVersionUID = 2747455271900243910L;

	private Long idcodingactor;
	private String name;
	private Date created;
	private String updated;
	private String userupdate;
	private boolean active;

	public CodingActorDTO() {
	}

	public CodingActorDTO(Long idcodingactor, String name, Date created, String updated, String userupdate,
			boolean active) {
		this.idcodingactor = idcodingactor;
		this.name = name;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
	}

	/**
	 * @return the idcodingactor
	 */
	public Long getIdcodingactor() {
		return idcodingactor;
	}

	/**
	 * @param idcodingactor the idcodingactor to set
	 */
	public void setIdcodingactor(Long idcodingactor) {
		this.idcodingactor = idcodingactor;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	public String getUpdated() {
		return updated;
	}

	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(String updated) {
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
