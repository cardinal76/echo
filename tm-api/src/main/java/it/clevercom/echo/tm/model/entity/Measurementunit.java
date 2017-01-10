package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the measurementunit database table.
 * 
 */
@Entity
@Table(name="measurementunit")
@NamedQuery(name="Measurementunit.findAll", query="SELECT m FROM Measurementunit m")
public class Measurementunit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idMeasurementUnit;

	private Object active;

	private Timestamp created;

	private String description;

	private String name;

	private Timestamp updated;

	private String updateUser;

	public Measurementunit() {
	}

	public int getIdMeasurementUnit() {
		return this.idMeasurementUnit;
	}

	public void setIdMeasurementUnit(int idMeasurementUnit) {
		this.idMeasurementUnit = idMeasurementUnit;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

}