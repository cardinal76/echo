package it.clevercom.echo.tm.model.entity;
// Generated 10-gen-2017 15.17.23 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Measurementtype generated by hbm2java
 */
@Entity
@Table(name = "measurementtype", catalog = "tmdw")
public class Measurementtype implements java.io.Serializable {

	private Integer idMeasurementType;
	private String name;
	private String description;
	private Boolean naN;
	private String commaEnumValues;
	private Integer idMeasurementUnit;
	private Date created;
	private Date updated;
	private boolean active;
	private String updateUser;
	private Set<Detectionresult> detectionresults = new HashSet<Detectionresult>(0);
	private Set<Standardthreshold> standardthresholds = new HashSet<Standardthreshold>(0);
	private Set<DetectiontypeMeasurementtype> detectiontypeMeasurementtypes = new HashSet<DetectiontypeMeasurementtype>(
			0);

	public Measurementtype() {
	}

	public Measurementtype(String name, Date created, Date updated, boolean active, String updateUser) {
		this.name = name;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
	}

	public Measurementtype(String name, String description, Boolean naN, String commaEnumValues,
			Integer idMeasurementUnit, Date created, Date updated, boolean active, String updateUser,
			Set<Detectionresult> detectionresults, Set<Standardthreshold> standardthresholds,
			Set<DetectiontypeMeasurementtype> detectiontypeMeasurementtypes) {
		this.name = name;
		this.description = description;
		this.naN = naN;
		this.commaEnumValues = commaEnumValues;
		this.idMeasurementUnit = idMeasurementUnit;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
		this.detectionresults = detectionresults;
		this.standardthresholds = standardthresholds;
		this.detectiontypeMeasurementtypes = detectiontypeMeasurementtypes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idMeasurementType", unique = true, nullable = false)
	public Integer getIdMeasurementType() {
		return this.idMeasurementType;
	}

	public void setIdMeasurementType(Integer idMeasurementType) {
		this.idMeasurementType = idMeasurementType;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "NaN")
	public Boolean getNaN() {
		return this.naN;
	}

	public void setNaN(Boolean naN) {
		this.naN = naN;
	}

	@Column(name = "commaEnumValues")
	public String getCommaEnumValues() {
		return this.commaEnumValues;
	}

	public void setCommaEnumValues(String commaEnumValues) {
		this.commaEnumValues = commaEnumValues;
	}

	@Column(name = "idMeasurementUnit")
	public Integer getIdMeasurementUnit() {
		return this.idMeasurementUnit;
	}

	public void setIdMeasurementUnit(Integer idMeasurementUnit) {
		this.idMeasurementUnit = idMeasurementUnit;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "measurementtype")
	public Set<Detectionresult> getDetectionresults() {
		return this.detectionresults;
	}

	public void setDetectionresults(Set<Detectionresult> detectionresults) {
		this.detectionresults = detectionresults;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "measurementtype")
	public Set<Standardthreshold> getStandardthresholds() {
		return this.standardthresholds;
	}

	public void setStandardthresholds(Set<Standardthreshold> standardthresholds) {
		this.standardthresholds = standardthresholds;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "measurementtype")
	public Set<DetectiontypeMeasurementtype> getDetectiontypeMeasurementtypes() {
		return this.detectiontypeMeasurementtypes;
	}

	public void setDetectiontypeMeasurementtypes(Set<DetectiontypeMeasurementtype> detectiontypeMeasurementtypes) {
		this.detectiontypeMeasurementtypes = detectiontypeMeasurementtypes;
	}

}
