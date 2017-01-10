package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the systemeventtype database table.
 * 
 */
@Entity
@Table(name="systemeventtype")
@NamedQuery(name="Systemeventtype.findAll", query="SELECT s FROM Systemeventtype s")
public class Systemeventtype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSystemEventType;

	private String action;

	private Object active;

	private Timestamp created;

	private String description;

	private Timestamp updated;

	private String updatedUser;

	//bi-directional many-to-one association to Systemevent
	@OneToMany(mappedBy="systemeventtype")
	private List<Systemevent> systemevents;

	public Systemeventtype() {
	}

	public int getIdSystemEventType() {
		return this.idSystemEventType;
	}

	public void setIdSystemEventType(int idSystemEventType) {
		this.idSystemEventType = idSystemEventType;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
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

	public List<Systemevent> getSystemevents() {
		return this.systemevents;
	}

	public void setSystemevents(List<Systemevent> systemevents) {
		this.systemevents = systemevents;
	}

	public Systemevent addSystemevent(Systemevent systemevent) {
		getSystemevents().add(systemevent);
		systemevent.setSystemeventtype(this);

		return systemevent;
	}

	public Systemevent removeSystemevent(Systemevent systemevent) {
		getSystemevents().remove(systemevent);
		systemevent.setSystemeventtype(null);

		return systemevent;
	}

}