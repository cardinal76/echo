package it.clevercom.echo.tm.model.entity;
// Generated 10-gen-2017 15.17.23 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Systemevent generated by hbm2java
 */
@Entity
@Table(name = "systemevent", catalog = "tmdw")
public class Systemevent implements java.io.Serializable {

	private Integer idEvent;
	private Systemeventtype systemeventtype;
	private String description;
	private Date created;
	private Date updated;
	private boolean active;
	private String updateUser;

	public Systemevent() {
	}

	public Systemevent(Systemeventtype systemeventtype, Date created, Date updated, boolean active, String updateUser) {
		this.systemeventtype = systemeventtype;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
	}

	public Systemevent(Systemeventtype systemeventtype, String description, Date created, Date updated, boolean active,
			String updateUser) {
		this.systemeventtype = systemeventtype;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idEvent", unique = true, nullable = false)
	public Integer getIdEvent() {
		return this.idEvent;
	}

	public void setIdEvent(Integer idEvent) {
		this.idEvent = idEvent;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idSystemEventType", nullable = false)
	public Systemeventtype getSystemeventtype() {
		return this.systemeventtype;
	}

	public void setSystemeventtype(Systemeventtype systemeventtype) {
		this.systemeventtype = systemeventtype;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = false, length = 19)
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

	@Column(name = "updateUser", nullable = false, length = 100)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}