package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the detectiontype_measurementtype database table.
 * 
 */
@Embeddable
public class DetectiontypeMeasurementtypePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idDetectionType;

	@Column(insertable=false, updatable=false)
	private int idMeasurementType;

	public DetectiontypeMeasurementtypePK() {
	}
	public int getIdDetectionType() {
		return this.idDetectionType;
	}
	public void setIdDetectionType(int idDetectionType) {
		this.idDetectionType = idDetectionType;
	}
	public int getIdMeasurementType() {
		return this.idMeasurementType;
	}
	public void setIdMeasurementType(int idMeasurementType) {
		this.idMeasurementType = idMeasurementType;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetectiontypeMeasurementtypePK)) {
			return false;
		}
		DetectiontypeMeasurementtypePK castOther = (DetectiontypeMeasurementtypePK)other;
		return 
			(this.idDetectionType == castOther.idDetectionType)
			&& (this.idMeasurementType == castOther.idMeasurementType);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idDetectionType;
		hash = hash * prime + this.idMeasurementType;
		
		return hash;
	}
}