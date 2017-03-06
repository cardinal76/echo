package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the rd_service_coding_actor database table.
 * 
 */
@Embeddable
public class ServiceCodingActorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private Long idservice;

	@Column(insertable=false, updatable=false)
	private Long idcodingactor;

	public ServiceCodingActorPK() {
	}
	public Long getIdservice() {
		return this.idservice;
	}
	public void setIdservice(Long idservice) {
		this.idservice = idservice;
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
		if (!(other instanceof ServiceCodingActorPK)) {
			return false;
		}
		ServiceCodingActorPK castOther = (ServiceCodingActorPK)other;
		return 
			this.idservice.equals(castOther.idservice)
			&& this.idcodingactor.equals(castOther.idcodingactor);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idservice.hashCode();
		hash = hash * prime + this.idcodingactor.hashCode();
		
		return hash;
	}
}