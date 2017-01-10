package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the physician_organizationunit database table.
 * 
 */
@Embeddable
public class PhysicianOrganizationunitPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idPhysician;

	@Column(insertable=false, updatable=false)
	private int idOrganizationUnit;

	public PhysicianOrganizationunitPK() {
	}
	public int getIdPhysician() {
		return this.idPhysician;
	}
	public void setIdPhysician(int idPhysician) {
		this.idPhysician = idPhysician;
	}
	public int getIdOrganizationUnit() {
		return this.idOrganizationUnit;
	}
	public void setIdOrganizationUnit(int idOrganizationUnit) {
		this.idOrganizationUnit = idOrganizationUnit;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PhysicianOrganizationunitPK)) {
			return false;
		}
		PhysicianOrganizationunitPK castOther = (PhysicianOrganizationunitPK)other;
		return 
			(this.idPhysician == castOther.idPhysician)
			&& (this.idOrganizationUnit == castOther.idOrganizationUnit);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPhysician;
		hash = hash * prime + this.idOrganizationUnit;
		
		return hash;
	}
}