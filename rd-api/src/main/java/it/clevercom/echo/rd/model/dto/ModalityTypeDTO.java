package it.clevercom.echo.rd.model.dto;
// Generated 21-feb-2017 16.05.29 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ModalityType generated by hbm2java
 */
@Entity
@Table(name = "modality_type")
public class ModalityTypeDTO implements java.io.Serializable {
	private static final long serialVersionUID = 3406464325790754778L;

	private long idmodalitytype;
	private String type;
	
	// transient attributes
	private transient Date created;
	private transient Date updated;
	private transient String updateuser;
	private transient boolean active;

	public ModalityTypeDTO() {
	}

	public ModalityTypeDTO(long idmodalitytype, String type, Date created, Date updated, String updateuser,
			boolean active) {
		this.idmodalitytype = idmodalitytype;
		this.type = type;
		this.created = created;
		this.updated = updated;
		this.updateuser = updateuser;
		this.active = active;
	}

	/**
	 * @return the idmodalitytype
	 */
	public long getIdmodalitytype() {
		return idmodalitytype;
	}

	/**
	 * @param idmodalitytype the idmodalitytype to set
	 */
	public void setIdmodalitytype(long idmodalitytype) {
		this.idmodalitytype = idmodalitytype;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the updateuser
	 */
	public String getUpdateuser() {
		return updateuser;
	}

	/**
	 * @param updateuser the updateuser to set
	 */
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
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

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}