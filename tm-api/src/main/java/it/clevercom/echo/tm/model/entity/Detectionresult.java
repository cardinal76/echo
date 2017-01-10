package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the detectionresult database table.
 * 
 */
@Entity
@Table(name="detectionresult")
@NamedQuery(name="Detectionresult.findAll", query="SELECT d FROM Detectionresult d")
public class Detectionresult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idDetectionResult;

	private Object active;

	private Timestamp created;

	private Timestamp updated;

	private String updateUser;

	private float valueNum;

	private String valueString;

	//bi-directional many-to-one association to Detectiontask
	@ManyToOne
	@JoinColumn(name="idDetectionTask")
	private Detectiontask detectiontask;

	//bi-directional many-to-one association to Measurementtype
	@ManyToOne
	@JoinColumn(name="idMeasurementType")
	private Measurementtype measurementtype;

	public Detectionresult() {
	}

	public int getIdDetectionResult() {
		return this.idDetectionResult;
	}

	public void setIdDetectionResult(int idDetectionResult) {
		this.idDetectionResult = idDetectionResult;
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

	public float getValueNum() {
		return this.valueNum;
	}

	public void setValueNum(float valueNum) {
		this.valueNum = valueNum;
	}

	public String getValueString() {
		return this.valueString;
	}

	public void setValueString(String valueString) {
		this.valueString = valueString;
	}

	public Detectiontask getDetectiontask() {
		return this.detectiontask;
	}

	public void setDetectiontask(Detectiontask detectiontask) {
		this.detectiontask = detectiontask;
	}

	public Measurementtype getMeasurementtype() {
		return this.measurementtype;
	}

	public void setMeasurementtype(Measurementtype measurementtype) {
		this.measurementtype = measurementtype;
	}

}