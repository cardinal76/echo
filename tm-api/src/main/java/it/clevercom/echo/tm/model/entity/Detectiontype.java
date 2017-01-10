package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the detectiontype database table.
 * 
 */
@Entity
@Table(name="detectiontype")
@NamedQuery(name="Detectiontype.findAll", query="SELECT d FROM Detectiontype d")
public class Detectiontype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idDetectionType;

	private Object active;

	private Timestamp created;

	private String description;

	private String name;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Detectiontask
	@OneToMany(mappedBy="detectiontype")
	private List<Detectiontask> detectiontasks;

	//bi-directional many-to-one association to DetectiontypeMeasurementtype
	@OneToMany(mappedBy="detectiontype")
	private List<DetectiontypeMeasurementtype> detectiontypeMeasurementtypes;

	public Detectiontype() {
	}

	public int getIdDetectionType() {
		return this.idDetectionType;
	}

	public void setIdDetectionType(int idDetectionType) {
		this.idDetectionType = idDetectionType;
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

	public List<Detectiontask> getDetectiontasks() {
		return this.detectiontasks;
	}

	public void setDetectiontasks(List<Detectiontask> detectiontasks) {
		this.detectiontasks = detectiontasks;
	}

	public Detectiontask addDetectiontask(Detectiontask detectiontask) {
		getDetectiontasks().add(detectiontask);
		detectiontask.setDetectiontype(this);

		return detectiontask;
	}

	public Detectiontask removeDetectiontask(Detectiontask detectiontask) {
		getDetectiontasks().remove(detectiontask);
		detectiontask.setDetectiontype(null);

		return detectiontask;
	}

	public List<DetectiontypeMeasurementtype> getDetectiontypeMeasurementtypes() {
		return this.detectiontypeMeasurementtypes;
	}

	public void setDetectiontypeMeasurementtypes(List<DetectiontypeMeasurementtype> detectiontypeMeasurementtypes) {
		this.detectiontypeMeasurementtypes = detectiontypeMeasurementtypes;
	}

	public DetectiontypeMeasurementtype addDetectiontypeMeasurementtype(DetectiontypeMeasurementtype detectiontypeMeasurementtype) {
		getDetectiontypeMeasurementtypes().add(detectiontypeMeasurementtype);
		detectiontypeMeasurementtype.setDetectiontype(this);

		return detectiontypeMeasurementtype;
	}

	public DetectiontypeMeasurementtype removeDetectiontypeMeasurementtype(DetectiontypeMeasurementtype detectiontypeMeasurementtype) {
		getDetectiontypeMeasurementtypes().remove(detectiontypeMeasurementtype);
		detectiontypeMeasurementtype.setDetectiontype(null);

		return detectiontypeMeasurementtype;
	}

}