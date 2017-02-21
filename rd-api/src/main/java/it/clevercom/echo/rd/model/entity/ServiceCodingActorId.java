package it.clevercom.echo.rd.model.entity;
// Generated 21-feb-2017 16.05.29 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ServiceCodingActorId generated by hbm2java
 */
@Embeddable
public class ServiceCodingActorId implements java.io.Serializable {

	private long idservice;
	private long idcodingactor;
	private String externalcode;
	private Date created;
	private Date updated;
	private String userupdate;
	private boolean active;

	public ServiceCodingActorId() {
	}

	public ServiceCodingActorId(long idservice, long idcodingactor, String externalcode, Date created, Date updated,
			String userupdate, boolean active) {
		this.idservice = idservice;
		this.idcodingactor = idcodingactor;
		this.externalcode = externalcode;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
	}

	@Column(name = "idservice", nullable = false)
	public long getIdservice() {
		return this.idservice;
	}

	public void setIdservice(long idservice) {
		this.idservice = idservice;
	}

	@Column(name = "idcodingactor", nullable = false)
	public long getIdcodingactor() {
		return this.idcodingactor;
	}

	public void setIdcodingactor(long idcodingactor) {
		this.idcodingactor = idcodingactor;
	}

	@Column(name = "externalcode", nullable = false, length = 100)
	public String getExternalcode() {
		return this.externalcode;
	}

	public void setExternalcode(String externalcode) {
		this.externalcode = externalcode;
	}

	@Column(name = "created", nullable = false, length = 29)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "updated", nullable = false, length = 29)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Column(name = "userupdate", nullable = false, length = 100)
	public String getUserupdate() {
		return this.userupdate;
	}

	public void setUserupdate(String userupdate) {
		this.userupdate = userupdate;
	}

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ServiceCodingActorId))
			return false;
		ServiceCodingActorId castOther = (ServiceCodingActorId) other;

		return (this.getIdservice() == castOther.getIdservice())
				&& (this.getIdcodingactor() == castOther.getIdcodingactor())
				&& ((this.getExternalcode() == castOther.getExternalcode())
						|| (this.getExternalcode() != null && castOther.getExternalcode() != null
								&& this.getExternalcode().equals(castOther.getExternalcode())))
				&& ((this.getCreated() == castOther.getCreated()) || (this.getCreated() != null
						&& castOther.getCreated() != null && this.getCreated().equals(castOther.getCreated())))
				&& ((this.getUpdated() == castOther.getUpdated()) || (this.getUpdated() != null
						&& castOther.getUpdated() != null && this.getUpdated().equals(castOther.getUpdated())))
				&& ((this.getUserupdate() == castOther.getUserupdate()) || (this.getUserupdate() != null
						&& castOther.getUserupdate() != null && this.getUserupdate().equals(castOther.getUserupdate())))
				&& (this.isActive() == castOther.isActive());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getIdservice();
		result = 37 * result + (int) this.getIdcodingactor();
		result = 37 * result + (getExternalcode() == null ? 0 : this.getExternalcode().hashCode());
		result = 37 * result + (getCreated() == null ? 0 : this.getCreated().hashCode());
		result = 37 * result + (getUpdated() == null ? 0 : this.getUpdated().hashCode());
		result = 37 * result + (getUserupdate() == null ? 0 : this.getUserupdate().hashCode());
		result = 37 * result + (this.isActive() ? 1 : 0);
		return result;
	}

}
