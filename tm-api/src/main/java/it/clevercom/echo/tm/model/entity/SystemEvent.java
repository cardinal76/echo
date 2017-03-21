package it.clevercom.echo.tm.model.entity;
// Generated 17-feb-2017 16.34.42 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SystemEvent generated by hbm2java
 */
@Entity
@Table(name = "system_event")
public class SystemEvent implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4707321590002983800L;
	private long idevent;
	private SystemeventType systemeventType;
	private String description;
	private Date created;
	private Date updated;
	private boolean active;
	private String updateuser;

	public SystemEvent() {
	}

	public SystemEvent(long idevent, SystemeventType systemeventType, Date created, Date updated, boolean active,
			String updateuser) {
		this.idevent = idevent;
		this.systemeventType = systemeventType;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateuser = updateuser;
	}

	public SystemEvent(long idevent, SystemeventType systemeventType, String description, Date created, Date updated,
			boolean active, String updateuser) {
		this.idevent = idevent;
		this.systemeventType = systemeventType;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateuser = updateuser;
	}

	@Id

	@Column(name = "idevent", unique = true, nullable = false)
	public long getIdevent() {
		return this.idevent;
	}

	public void setIdevent(long idevent) {
		this.idevent = idevent;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idsystemeventtype", nullable = false)
	public SystemeventType getSystemeventType() {
		return this.systemeventType;
	}

	public void setSystemeventType(SystemeventType systemeventType) {
		this.systemeventType = systemeventType;
	}

	@Column(name = "description")
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
