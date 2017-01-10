package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the systemevent database table.
 * 
 */
@Entity
@Table(name="systemevent")
@NamedQuery(name="Systemevent.findAll", query="SELECT s FROM Systemevent s")
public class Systemevent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idEvent;

	private Object active;

	private Timestamp created;

	private Object description;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Systemeventtype
	@ManyToOne
	@JoinColumn(name="idSystemEventType")
	private Systemeventtype systemeventtype;

	public Systemevent() {
	}

	public int getIdEvent() {
		return this.idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
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

	public Systemeventtype getSystemeventtype() {
		return this.systemeventtype;
	}

	public void setSystemeventtype(Systemeventtype systemeventtype) {
		this.systemeventtype = systemeventtype;
	}

}