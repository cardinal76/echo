package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the measurementtype database table.
 * 
 */
@Entity
@Table(name="measurementtype")
@NamedQuery(name="Measurementtype.findAll", query="SELECT m FROM Measurementtype m")
public class Measurementtype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idMeasurementType;

	private Object active;

	private String commaEnumValues;

	private Timestamp created;

	private String description;

	private int idMeasurementUnit;

	private String name;

	@Column(name="NaN")
	private Object naN;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Detectionresult
	@OneToMany(mappedBy="measurementtype")
	private List<Detectionresult> detectionresults;

	//bi-directional many-to-one association to DetectiontypeMeasurementtype
	@OneToMany(mappedBy="measurementtype")
	private List<DetectiontypeMeasurementtype> detectiontypeMeasurementtypes;

	//bi-directional many-to-one association to Standardthreshold
	@OneToMany(mappedBy="measurementtype")
	private List<Standardthreshold> standardthresholds;

	public Measurementtype() {
	}

	public int getIdMeasurementType() {
		return this.idMeasurementType;
	}

	public void setIdMeasurementType(int idMeasurementType) {
		this.idMeasurementType = idMeasurementType;
	}

	public Object getActive() {
		return this.active;
	}

	public void setActive(Object active) {
		this.active = active;
	}

	public String getCommaEnumValues() {
		return this.commaEnumValues;
	}

	public void setCommaEnumValues(String commaEnumValues) {
		this.commaEnumValues = commaEnumValues;
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

	public int getIdMeasurementUnit() {
		return this.idMeasurementUnit;
	}

	public void setIdMeasurementUnit(int idMeasurementUnit) {
		this.idMeasurementUnit = idMeasurementUnit;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getNaN() {
		return this.naN;
	}

	public void setNaN(Object naN) {
		this.naN = naN;
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

	public List<Detectionresult> getDetectionresults() {
		return this.detectionresults;
	}

	public void setDetectionresults(List<Detectionresult> detectionresults) {
		this.detectionresults = detectionresults;
	}

	public Detectionresult addDetectionresult(Detectionresult detectionresult) {
		getDetectionresults().add(detectionresult);
		detectionresult.setMeasurementtype(this);

		return detectionresult;
	}

	public Detectionresult removeDetectionresult(Detectionresult detectionresult) {
		getDetectionresults().remove(detectionresult);
		detectionresult.setMeasurementtype(null);

		return detectionresult;
	}

	public List<DetectiontypeMeasurementtype> getDetectiontypeMeasurementtypes() {
		return this.detectiontypeMeasurementtypes;
	}

	public void setDetectiontypeMeasurementtypes(List<DetectiontypeMeasurementtype> detectiontypeMeasurementtypes) {
		this.detectiontypeMeasurementtypes = detectiontypeMeasurementtypes;
	}

	public DetectiontypeMeasurementtype addDetectiontypeMeasurementtype(DetectiontypeMeasurementtype detectiontypeMeasurementtype) {
		getDetectiontypeMeasurementtypes().add(detectiontypeMeasurementtype);
		detectiontypeMeasurementtype.setMeasurementtype(this);

		return detectiontypeMeasurementtype;
	}

	public DetectiontypeMeasurementtype removeDetectiontypeMeasurementtype(DetectiontypeMeasurementtype detectiontypeMeasurementtype) {
		getDetectiontypeMeasurementtypes().remove(detectiontypeMeasurementtype);
		detectiontypeMeasurementtype.setMeasurementtype(null);

		return detectiontypeMeasurementtype;
	}

	public List<Standardthreshold> getStandardthresholds() {
		return this.standardthresholds;
	}

	public void setStandardthresholds(List<Standardthreshold> standardthresholds) {
		this.standardthresholds = standardthresholds;
	}

	public Standardthreshold addStandardthreshold(Standardthreshold standardthreshold) {
		getStandardthresholds().add(standardthreshold);
		standardthreshold.setMeasurementtype(this);

		return standardthreshold;
	}

	public Standardthreshold removeStandardthreshold(Standardthreshold standardthreshold) {
		getStandardthresholds().remove(standardthreshold);
		standardthreshold.setMeasurementtype(null);

		return standardthreshold;
	}

}