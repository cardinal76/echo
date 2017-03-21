package it.clevercom.echo.tm.model.entity;
// Generated 17-feb-2017 16.34.42 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * LanguageType generated by hbm2java
 */
@Entity
@Table(name = "language_type")
public class LanguageType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3453767993449863960L;
	private long idlanguagetype;
	private String code;
	private String description;
	private Date created;
	private Date updated;
	private boolean active;
	private String updateuser;

	public LanguageType() {
	}

	public LanguageType(long idlanguagetype, String code, String description, Date created, Date updated,
			boolean active, String updateuser) {
		this.idlanguagetype = idlanguagetype;
		this.code = code;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateuser = updateuser;
	}

	@Id

	@Column(name = "idlanguagetype", unique = true, nullable = false)
	public long getIdlanguagetype() {
		return this.idlanguagetype;
	}

	public void setIdlanguagetype(long idlanguagetype) {
		this.idlanguagetype = idlanguagetype;
	}

	@Column(name = "code", nullable = false, length = 5)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "description", nullable = false, length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, length = 29)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = false, length = 29)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name = "updateuser", nullable = false, length = 100)
	public String getUpdateuser() {
		return this.updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

}
