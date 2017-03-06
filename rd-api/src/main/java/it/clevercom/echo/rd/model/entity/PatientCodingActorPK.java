package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the rd_patient_coding_actor database table.
 * 
 */
@Embeddable
public class PatientCodingActorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private Long idpatient;

	@Column(insertable=false, updatable=false)
	private Long idcodingactor;

	public PatientCodingActorPK() {
	}
	public Long getIdpatient() {
		return this.idpatient;
	}
	public void setIdpatient(Long idpatient) {
		this.idpatient = idpatient;
	}
	public Long getIdcodingactor() {
		return this.idcodingactor;
	}
	public void setIdcodingactor(Long idcodingactor) {
		this.idcodingactor = idcodingactor;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PatientCodingActorPK)) {
			return false;
		}
		PatientCodingActorPK castOther = (PatientCodingActorPK)other;
		return 
			this.idpatient.equals(castOther.idpatient)
			&& this.idcodingactor.equals(castOther.idcodingactor);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idpatient.hashCode();
		hash = hash * prime + this.idcodingactor.hashCode();
		
		return hash;
	}
}