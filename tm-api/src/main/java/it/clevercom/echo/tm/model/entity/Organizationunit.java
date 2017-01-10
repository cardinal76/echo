package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the organizationunit database table.
 * 
 */
@Entity
@Table(name="organizationunit")
@NamedQuery(name="Organizationunit.findAll", query="SELECT o FROM Organizationunit o")
public class Organizationunit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idOrganizationUnit;

	private Object active;

	private String address;

	private Timestamp created;

	private String description;

	private String email;

	private String fax;

	private String name;

	private String telephone;

	private String type;

	private Timestamp updated;

	private String updateUser;

	private String website;

	//bi-directional many-to-one association to Clinicfolder
	@OneToMany(mappedBy="organizationunit")
	private List<Clinicfolder> clinicfolders;

	//bi-directional many-to-one association to NurseOrganizationunit
	@OneToMany(mappedBy="organizationunit")
	private List<NurseOrganizationunit> nurseOrganizationunits;

	//bi-directional many-to-one association to Organizationunit
	@ManyToOne
	@JoinColumn(name="parentId")
	private Organizationunit organizationunit;

	//bi-directional many-to-one association to Organizationunit
	@OneToMany(mappedBy="organizationunit")
	private List<Organizationunit> organizationunits;

	//bi-directional many-to-one association to PhysicianOrganizationunit
	@OneToMany(mappedBy="organizationunit")
	private List<PhysicianOrganizationunit> physicianOrganizationunits;

	public Organizationunit() {
	}

	public int getIdOrganizationUnit() {
		return this.idOrganizationUnit;
	}

	public void setIdOrganizationUnit(int idOrganizationUnit) {
		this.idOrganizationUnit = idOrganizationUnit;
	}

	public Object getActive() {
		return this.active;
	}

	public void setActive(Object active) {
		this.active = active;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List<Clinicfolder> getClinicfolders() {
		return this.clinicfolders;
	}

	public void setClinicfolders(List<Clinicfolder> clinicfolders) {
		this.clinicfolders = clinicfolders;
	}

	public Clinicfolder addClinicfolder(Clinicfolder clinicfolder) {
		getClinicfolders().add(clinicfolder);
		clinicfolder.setOrganizationunit(this);

		return clinicfolder;
	}

	public Clinicfolder removeClinicfolder(Clinicfolder clinicfolder) {
		getClinicfolders().remove(clinicfolder);
		clinicfolder.setOrganizationunit(null);

		return clinicfolder;
	}

	public List<NurseOrganizationunit> getNurseOrganizationunits() {
		return this.nurseOrganizationunits;
	}

	public void setNurseOrganizationunits(List<NurseOrganizationunit> nurseOrganizationunits) {
		this.nurseOrganizationunits = nurseOrganizationunits;
	}

	public NurseOrganizationunit addNurseOrganizationunit(NurseOrganizationunit nurseOrganizationunit) {
		getNurseOrganizationunits().add(nurseOrganizationunit);
		nurseOrganizationunit.setOrganizationunit(this);

		return nurseOrganizationunit;
	}

	public NurseOrganizationunit removeNurseOrganizationunit(NurseOrganizationunit nurseOrganizationunit) {
		getNurseOrganizationunits().remove(nurseOrganizationunit);
		nurseOrganizationunit.setOrganizationunit(null);

		return nurseOrganizationunit;
	}

	public Organizationunit getOrganizationunit() {
		return this.organizationunit;
	}

	public void setOrganizationunit(Organizationunit organizationunit) {
		this.organizationunit = organizationunit;
	}

	public List<Organizationunit> getOrganizationunits() {
		return this.organizationunits;
	}

	public void setOrganizationunits(List<Organizationunit> organizationunits) {
		this.organizationunits = organizationunits;
	}

	public Organizationunit addOrganizationunit(Organizationunit organizationunit) {
		getOrganizationunits().add(organizationunit);
		organizationunit.setOrganizationunit(this);

		return organizationunit;
	}

	public Organizationunit removeOrganizationunit(Organizationunit organizationunit) {
		getOrganizationunits().remove(organizationunit);
		organizationunit.setOrganizationunit(null);

		return organizationunit;
	}

	public List<PhysicianOrganizationunit> getPhysicianOrganizationunits() {
		return this.physicianOrganizationunits;
	}

	public void setPhysicianOrganizationunits(List<PhysicianOrganizationunit> physicianOrganizationunits) {
		this.physicianOrganizationunits = physicianOrganizationunits;
	}

	public PhysicianOrganizationunit addPhysicianOrganizationunit(PhysicianOrganizationunit physicianOrganizationunit) {
		getPhysicianOrganizationunits().add(physicianOrganizationunit);
		physicianOrganizationunit.setOrganizationunit(this);

		return physicianOrganizationunit;
	}

	public PhysicianOrganizationunit removePhysicianOrganizationunit(PhysicianOrganizationunit physicianOrganizationunit) {
		getPhysicianOrganizationunits().remove(physicianOrganizationunit);
		physicianOrganizationunit.setOrganizationunit(null);

		return physicianOrganizationunit;
	}

}