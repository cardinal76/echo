package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the rd_hl7_patient database table.
 * 
 */
@Embeddable
public class Hl7PatientPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String idhl7patient;

	private String idauthoritynamespaceid;

	public Hl7PatientPK() {
	}
	public String getIdhl7patient() {
		return this.idhl7patient;
	}
	public void setIdhl7patient(String idhl7patient) {
		this.idhl7patient = idhl7patient;
	}
	public String getIdauthoritynamespaceid() {
		return this.idauthoritynamespaceid;
	}
	public void setIdauthoritynamespaceid(String idauthoritynamespaceid) {
		this.idauthoritynamespaceid = idauthoritynamespaceid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Hl7PatientPK)) {
			return false;
		}
		Hl7PatientPK castOther = (Hl7PatientPK)other;
		return 
			this.idhl7patient.equals(castOther.idhl7patient)
			&& this.idauthoritynamespaceid.equals(castOther.idauthoritynamespaceid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idhl7patient.hashCode();
		hash = hash * prime + this.idauthoritynamespaceid.hashCode();
		
		return hash;
	}
}