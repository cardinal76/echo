package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"created","updated","userupdate","active"})
public class BodyApparatusDTO implements Serializable {
	private static final long serialVersionUID = -9001955080302115293L;
	
	private Long idbodyapparatus;
    private char code;
    private String description;
    
    // transient attributes
    private Date created;
    private Date updated;
    private String userupdate;
    private Boolean active;

	public BodyApparatusDTO() {
	}

	public BodyApparatusDTO(Character code, String description, Date created, Date updated, String userupdate, boolean active) {
		this.code = code;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
	}

	/**
	 * @return the idbodyapparatus
	 */
	public Long getIdbodyapparatus() {
		return idbodyapparatus;
	}

	/**
	 * @param idbodyapparatus the idbodyapparatus to set
	 */
	public void setIdbodyapparatus(Long idbodyapparatus) {
		this.idbodyapparatus = idbodyapparatus;
	}

	/**
	 * @return the code
	 */
	public char getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(char code) {
		this.code = code;
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
	public Boolean getActive() {
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
