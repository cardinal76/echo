package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the notificationtype database table.
 * 
 */
@Entity
@Table(name="notificationtype")
@NamedQuery(name="Notificationtype.findAll", query="SELECT n FROM Notificationtype n")
public class Notificationtype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idNotificationType;

	private Object active;

	private Timestamp created;

	private String description;

	private Timestamp updated;

	private String updatedUser;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="notificationtype")
	private List<Notification> notifications;

	public Notificationtype() {
	}

	public int getIdNotificationType() {
		return this.idNotificationType;
	}

	public void setIdNotificationType(int idNotificationType) {
		this.idNotificationType = idNotificationType;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getUpdatedUser() {
		return this.updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	public List<Notification> getNotifications() {
		return this.notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public Notification addNotification(Notification notification) {
		getNotifications().add(notification);
		notification.setNotificationtype(this);

		return notification;
	}

	public Notification removeNotification(Notification notification) {
		getNotifications().remove(notification);
		notification.setNotificationtype(null);

		return notification;
	}

}