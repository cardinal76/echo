package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the detectiontype_measurementtype database table.
 * 
 */
@Entity
@Table(name="detectiontype_measurementtype")
@NamedQuery(name="DetectiontypeMeasurementtype.findAll", query="SELECT d FROM DetectiontypeMeasurementtype d")
public class DetectiontypeMeasurementtype implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetectiontypeMeasurementtypePK id;

	private Object active;

	private Timestamp created;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Detectiontype
	@ManyToOne
	@JoinColumn(name="idDetectionType")
	private Detectiontype detectiontype;

	//bi-directional many-to-one association to Measurementtype
	@ManyToOne
	@JoinColumn(name="idMeasurementType")
	private Measurementtype measurementtype;

	public DetectiontypeMeasurementtype() {
	}

	public DetectiontypeMeasurementtypePK getId() {
		return this.id;
	}

	public void setId(DetectiontypeMeasurementtypePK id) {
		this.id = id;
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

	public Detectiontype getDetectiontype() {
		return this.detectiontype;
	}

	public void setDetectiontype(Detectiontype detectiontype) {
		this.detectiontype = detectiontype;
	}

	public Measurementtype getMeasurementtype() {
		return this.measurementtype;
	}

	public void setMeasurementtype(Measurementtype measurementtype) {
		this.measurementtype = measurementtype;
	}

}