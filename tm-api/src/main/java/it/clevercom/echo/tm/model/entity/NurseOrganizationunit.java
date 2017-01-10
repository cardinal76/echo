package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the nurse_organizationunit database table.
 * 
 */
@Entity
@Table(name="nurse_organizationunit")
@NamedQuery(name="NurseOrganizationunit.findAll", query="SELECT n FROM NurseOrganizationunit n")
public class NurseOrganizationunit implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private NurseOrganizationunitPK id;

	private Object active;

	private Timestamp created;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Nurse
	@ManyToOne
	@JoinColumn(name="idNurse")
	private Nurse nurse;

	//bi-directional many-to-one association to Organizationunit
	@ManyToOne
	@JoinColumn(name="idOrganizationUnit")
	private Organizationunit organizationunit;

	public NurseOrganizationunit() {
	}

	public NurseOrganizationunitPK getId() {
		return this.id;
	}

	public void setId(NurseOrganizationunitPK id) {
		this.id = id;
	}

	public Object getActive() {
		return this.active;
	}

	public void setActive(Object active) {
		this.active = active;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Nurse getNurse() {
		return this.nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

	public Organizationunit getOrganizationunit() {
		return this.organizationunit;
	}

	public void setOrganizationunit(Organizationunit organizationunit) {
		this.organizationunit = organizationunit;
	}

}