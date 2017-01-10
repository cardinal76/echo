package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the patient_caregiver database table.
 * 
 */
@Embeddable
public class PatientCaregiverPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idPatient;

	@Column(insertable=false, updatable=false)
	private int idCareGiver;

	public PatientCaregiverPK() {
	}
	public int getIdPatient() {
		return this.idPatient;
	}
	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}
	public int getIdCareGiver() {
		return this.idCareGiver;
	}
	public void setIdCareGiver(int idCareGiver) {
		this.idCareGiver = idCareGiver;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PatientCaregiverPK)) {
			return false;
		}
		PatientCaregiverPK castOther = (PatientCaregiverPK)other;
		return 
			(this.idPatient == castOther.idPatient)
			&& (this.idCareGiver == castOther.idCareGiver);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPatient;
		hash = hash * prime + this.idCareGiver;
		
		return hash;
	}
}