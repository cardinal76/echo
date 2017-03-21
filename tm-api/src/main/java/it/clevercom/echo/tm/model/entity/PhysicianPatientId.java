package it.clevercom.echo.tm.model.entity;
// Generated 17-feb-2017 16.34.42 by Hibernate Tools 5.2.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PhysicianPatientId generated by hbm2java
 */
@Embeddable
public class PhysicianPatientId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5167350138996688470L;
	private long idphysician;
	private long idpatient;

	public PhysicianPatientId() {
	}

	public PhysicianPatientId(long idphysician, long idpatient) {
		this.idphysician = idphysician;
		this.idpatient = idpatient;
	}

	@Column(name = "idphysician", nullable = false)
	public long getIdphysician() {
		return this.idphysician;
	}

	public void setIdphysician(long idphysician) {
		this.idphysician = idphysician;
	}

	@Column(name = "idpatient", nullable = false)
	public long getIdpatient() {
		return this.idpatient;
	}

	public void setIdpatient(long idpatient) {
		this.idpatient = idpatient;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PhysicianPatientId))
			return false;
		PhysicianPatientId castOther = (PhysicianPatientId) other;

		return (this.getIdphysician() == castOther.getIdphysician())
				&& (this.getIdpatient() == castOther.getIdpatient());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getIdphysician();
		result = 37 * result + (int) this.getIdpatient();
		return result;
	}

}
