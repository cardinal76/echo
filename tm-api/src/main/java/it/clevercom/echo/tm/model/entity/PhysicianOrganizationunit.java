package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the physician_organizationunit database table.
 * 
 */
@Entity
@Table(name="physician_organizationunit")
@NamedQuery(name="PhysicianOrganizationunit.findAll", query="SELECT p FROM PhysicianOrganizationunit p")
public class PhysicianOrganizationunit implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PhysicianOrganizationunitPK id;

	private Object active;

	private Timestamp created;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Organizationunit
	@ManyToOne
	@JoinColumn(name="idOrganizationUnit")
	private Organizationunit organizationunit;

	//bi-directional many-to-one association to Physician
	@ManyToOne
	@JoinColumn(name="idPhysician")
	private Physician physician;

	public PhysicianOrganizationunit() {
	}

	public PhysicianOrganizationunitPK getId() {
		return this.id;
	}

	public void setId(PhysicianOrganizationunitPK id) {
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

	public Organizationunit getOrganizationunit() {
		return this.organizationunit;
	}

	public void setOrganizationunit(Organizationunit organizationunit) {
		this.organizationunit = organizationunit;
	}

	public Physician getPhysician() {
		return this.physician;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}

}