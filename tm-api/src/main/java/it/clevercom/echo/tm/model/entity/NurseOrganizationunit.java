package it.clevercom.echo.tm.model.entity;
// Generated 10-gen-2017 15.17.23 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NurseOrganizationunit generated by hbm2java
 */
@Entity
@Table(name = "nurse_organizationunit", catalog = "tmdw")
public class NurseOrganizationunit implements java.io.Serializable {

	private NurseOrganizationunitId id;
	private Nurse nurse;
	private Organizationunit organizationunit;
	private Date created;
	private Date updated;
	private Boolean active;
	private String updateUser;

	public NurseOrganizationunit() {
	}

	public NurseOrganizationunit(NurseOrganizationunitId id, Nurse nurse, Organizationunit organizationunit) {
		this.id = id;
		this.nurse = nurse;
		this.organizationunit = organizationunit;
	}

	public NurseOrganizationunit(NurseOrganizationunitId id, Nurse nurse, Organizationunit organizationunit,
			Date created, Date updated, Boolean active, String updateUser) {
		this.id = id;
		this.nurse = nurse;
		this.organizationunit = organizationunit;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "idNurse", column = @Column(name = "idNurse", nullable = false)),
			@AttributeOverride(name = "idOrganizationUnit", column = @Column(name = "idOrganizationUnit", nullable = false)) })
	public NurseOrganizationunitId getId() {
		return this.id;
	}

	public void setId(NurseOrganizationunitId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idNurse", nullable = false, insertable = false, updatable = false)
	public Nurse getNurse() {
		return this.nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idOrganizationUnit", nullable = false, insertable = false, updatable = false)
	public Organizationunit getOrganizationunit() {
		return this.organizationunit;
	}

	public void setOrganizationunit(Organizationunit organizationunit) {
		this.organizationunit = organizationunit;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Column(name = "active")
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Column(name = "updateUser", length = 100)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}
