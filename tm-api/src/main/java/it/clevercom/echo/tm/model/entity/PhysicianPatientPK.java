package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the physician_patient database table.
 * 
 */
@Embeddable
public class PhysicianPatientPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idPhysician;

	@Column(insertable=false, updatable=false)
	private int idPatient;

	public PhysicianPatientPK() {
	}
	public int getIdPhysician() {
		return this.idPhysician;
	}
	public void setIdPhysician(int idPhysician) {
		this.idPhysician = idPhysician;
	}
	public int getIdPatient() {
		return this.idPatient;
	}
	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PhysicianPatientPK)) {
			return false;
		}
		PhysicianPatientPK castOther = (PhysicianPatientPK)other;
		return 
			(this.idPhysician == castOther.idPhysician)
			&& (this.idPatient == castOther.idPatient);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPhysician;
		hash = hash * prime + this.idPatient;
		
		return hash;
	}
}