package it.clevercom.echo.tm.model.entity;
// Generated 10-gen-2017 15.17.23 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Organizationunit generated by hbm2java
 */
@Entity
@Table(name = "organizationunit", catalog = "tmdw")
public class Organizationunit implements java.io.Serializable {

	private Integer idOrganizationUnit;
	private Organizationunit organizationunit;
	private String name;
	private String description;
	private String address;
	private String telephone;
	private String email;
	private String fax;
	private String website;
	private String type;
	private Date created;
	private Date updated;
	private boolean active;
	private String updateUser;
	private Set<NurseOrganizationunit> nurseOrganizationunits = new HashSet<NurseOrganizationunit>(0);
	private Set<Organizationunit> organizationunits = new HashSet<Organizationunit>(0);
	private Set<Clinicfolder> clinicfolders = new HashSet<Clinicfolder>(0);
	private Set<PhysicianOrganizationunit> physicianOrganizationunits = new HashSet<PhysicianOrganizationunit>(0);

	public Organizationunit() {
	}

	public Organizationunit(Date created, Date updated, boolean active, String updateUser) {
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
	}

	public Organizationunit(Organizationunit organizationunit, String name, String description, String address,
			String telephone, String email, String fax, String website, String type, Date created, Date updated,
			boolean active, String updateUser, Set<NurseOrganizationunit> nurseOrganizationunits,
			Set<Organizationunit> organizationunits, Set<Clinicfolder> clinicfolders,
			Set<PhysicianOrganizationunit> physicianOrganizationunits) {
		this.organizationunit = organizationunit;
		this.name = name;
		this.description = description;
		this.address = address;
		this.telephone = telephone;
		this.email = email;
		this.fax = fax;
		this.website = website;
		this.type = type;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
		this.nurseOrganizationunits = nurseOrganizationunits;
		this.organizationunits = organizationunits;
		this.clinicfolders = clinicfolders;
		this.physicianOrganizationunits = physicianOrganizationunits;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idOrganizationUnit", unique = true, nullable = false)
	public Integer getIdOrganizationUnit() {
		return this.idOrganizationUnit;
	}

	public void setIdOrganizationUnit(Integer idOrganizationUnit) {
		this.idOrganizationUnit = idOrganizationUnit;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	public Organizationunit getOrganizationunit() {
		return this.organizationunit;
	}

	public void setOrganizationunit(Organizationunit organizationunit) {
		this.organizationunit = organizationunit;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "address", length = 45)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "telephone", length = 100)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "fax", length = 100)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "website", length = 100)
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "type", length = 14)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = false, length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name = "updateUser", nullable = false, length = 100)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationunit")
	public Set<NurseOrganizationunit> getNurseOrganizationunits() {
		return this.nurseOrganizationunits;
	}

	public void setNurseOrganizationunits(Set<NurseOrganizationunit> nurseOrganizationunits) {
		this.nurseOrganizationunits = nurseOrganizationunits;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationunit")
	public Set<Organizationunit> getOrganizationunits() {
		return this.organizationunits;
	}

	public void setOrganizationunits(Set<Organizationunit> organizationunits) {
		this.organizationunits = organizationunits;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationunit")
	public Set<Clinicfolder> getClinicfolders() {
		return this.clinicfolders;
	}

	public void setClinicfolders(Set<Clinicfolder> clinicfolders) {
		this.clinicfolders = clinicfolders;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationunit")
	public Set<PhysicianOrganizationunit> getPhysicianOrganizationunits() {
		return this.physicianOrganizationunits;
	}

	public void setPhysicianOrganizationunits(Set<PhysicianOrganizationunit> physicianOrganizationunits) {
		this.physicianOrganizationunits = physicianOrganizationunits;
	}

}