package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the nurse_organizationunit database table.
 * 
 */
@Embeddable
public class NurseOrganizationunitPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idNurse;

	@Column(insertable=false, updatable=false)
	private int idOrganizationUnit;

	public NurseOrganizationunitPK() {
	}
	public int getIdNurse() {
		return this.idNurse;
	}
	public void setIdNurse(int idNurse) {
		this.idNurse = idNurse;
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
		if (!(other instanceof NurseOrganizationunitPK)) {
			return false;
		}
		NurseOrganizationunitPK castOther = (NurseOrganizationunitPK)other;
		return 
			(this.idNurse == castOther.idNurse)
			&& (this.idOrganizationUnit == castOther.idOrganizationUnit);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idNurse;
		hash = hash * prime + this.idOrganizationUnit;
		
		return hash;
	}
}