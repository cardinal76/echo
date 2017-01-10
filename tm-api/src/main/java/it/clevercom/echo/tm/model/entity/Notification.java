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
 * Notification generated by hbm2java
 */
@Entity
@Table(name = "notification", catalog = "tmdw")
public class Notification implements java.io.Serializable {

	private Integer idNotification;
	private Notificationtype notificationtype;
	private String description;
	private Date created;
	private Date updated;
	private boolean active;
	private String updateUser;

	public Notification() {
	}

	public Notification(Notificationtype notificationtype, Date created, Date updated, boolean active,
			String updateUser) {
		this.notificationtype = notificationtype;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
	}

	public Notification(Notificationtype notificationtype, String description, Date created, Date updated,
			boolean active, String updateUser) {
		this.notificationtype = notificationtype;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idNotification", unique = true, nullable = false)
	public Integer getIdNotification() {
		return this.idNotification;
	}

	public void setIdNotification(Integer idNotification) {
		this.idNotification = idNotification;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idNotificationType", nullable = false)
	public Notificationtype getNotificationtype() {
		return this.notificationtype;
	}

	public void setNotificationtype(Notificationtype notificationtype) {
		this.notificationtype = notificationtype;
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
