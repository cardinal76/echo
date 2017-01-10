package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the notification database table.
 * 
 */
@Entity
@Table(name="notification")
@NamedQuery(name="Notification.findAll", query="SELECT n FROM Notification n")
public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idNotification;

	private Object active;

	private Timestamp created;

	private Object description;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Notificationtype
	@ManyToOne
	@JoinColumn(name="idNotificationType")
	private Notificationtype notificationtype;

	public Notification() {
	}

	public int getIdNotification() {
		return this.idNotification;
	}

	public void setIdNotification(int idNotification) {
		this.idNotification = idNotification;
	}

	public Object getActive() {
		return this.active;
	}

	public void setActive(Object active) {
		this.active = active;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Object getDescription() {
		return this.description;
	}

	public void setDescription(Object description) {
		this.description = description;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Notificationtype getNotificationtype() {
		return this.notificationtype;
	}

	public void setNotificationtype(Notificationtype notificationtype) {
		this.notificationtype = notificationtype;
	}

}