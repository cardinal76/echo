package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rd_municipality database table.
 * 
 */
@Entity
@Table(name="rd_municipality")
@NamedQuery(name="Municipality.findAll", query="SELECT m FROM Municipality m")
public class Municipality implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idmunicipality;

	private Boolean active;

	private Timestamp created;

	private String municipalityname;

	private String municipalitystdcode;

	private String postalcode;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to Province
	@ManyToOne
	@JoinColumn(name="idprovince")
	private Province rdProvince;

	//bi-directional many-to-one association to OrganizationUnit
	@OneToMany(mappedBy="rdMunicipality")
	private List<OrganizationUnit> rdOrganizationUnits;

	//bi-directional many-to-one association to Patient
	@OneToMany(mappedBy="rdMunicipality1")
	private List<Patient> rdPatients1;

	//bi-directional many-to-one association to Patient
	@OneToMany(mappedBy="rdMunicipality2")
	private List<Patient> rdPatients2;

	//bi-directional many-to-one association to Patient
	@OneToMany(mappedBy="rdMunicipality3")
	private List<Patient> rdPatients3;

	public Municipality() {
	}

	public Long getIdmunicipality() {
		return this.idmunicipality;
	}

	public void setIdmunicipality(Long idmunicipality) {
		this.idmunicipality = idmunicipality;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getMunicipalityname() {
		return this.municipalityname;
	}

	public void setMunicipalityname(String municipalityname) {
		this.municipalityname = municipalityname;
	}

	public String getMunicipalitystdcode() {
		return this.municipalitystdcode;
	}

	public void setMunicipalitystdcode(String municipalitystdcode) {
		this.municipalitystdcode = municipalitystdcode;
	}

	public String getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getUserupdate() {
		return this.userupdate;
	}

	public void setUserupdate(String userupdate) {
		this.userupdate = userupdate;
	}

	public Province getRdProvince() {
		return this.rdProvince;
	}

	public void setRdProvince(Province rdProvince) {
		this.rdProvince = rdProvince;
	}

	public List<OrganizationUnit> getRdOrganizationUnits() {
		return this.rdOrganizationUnits;
	}

	public void setRdOrganizationUnits(List<OrganizationUnit> rdOrganizationUnits) {
		this.rdOrganizationUnits = rdOrganizationUnits;
	}

	public OrganizationUnit addRdOrganizationUnit(OrganizationUnit rdOrganizationUnit) {
		getRdOrganizationUnits().add(rdOrganizationUnit);
		rdOrganizationUnit.setRdMunicipality(this);

		return rdOrganizationUnit;
	}

	public OrganizationUnit removeRdOrganizationUnit(OrganizationUnit rdOrganizationUnit) {
		getRdOrganizationUnits().remove(rdOrganizationUnit);
		rdOrganizationUnit.setRdMunicipality(null);

		return rdOrganizationUnit;
	}

	public List<Patient> getRdPatients1() {
		return this.rdPatients1;
	}

	public void setRdPatients1(List<Patient> rdPatients1) {
		this.rdPatients1 = rdPatients1;
	}

	public Patient addRdPatients1(Patient rdPatients1) {
		getRdPatients1().add(rdPatients1);
		rdPatients1.setRdMunicipality1(this);

		return rdPatients1;
	}

	public Patient removeRdPatients1(Patient rdPatients1) {
		getRdPatients1().remove(rdPatients1);
		rdPatients1.setRdMunicipality1(null);

		return rdPatients1;
	}

	public List<Patient> getRdPatients2() {
		return this.rdPatients2;
	}

	public void setRdPatients2(List<Patient> rdPatients2) {
		this.rdPatients2 = rdPatients2;
	}

	public Patient addRdPatients2(Patient rdPatients2) {
		getRdPatients2().add(rdPatients2);
		rdPatients2.setRdMunicipality2(this);

		return rdPatients2;
	}

	public Patient removeRdPatients2(Patient rdPatients2) {
		getRdPatients2().remove(rdPatients2);
		rdPatients2.setRdMunicipality2(null);

		return rdPatients2;
	}

	public List<Patient> getRdPatients3() {
		return this.rdPatients3;
	}

	public void setRdPatients3(List<Patient> rdPatients3) {
		this.rdPatients3 = rdPatients3;
	}

	public Patient addRdPatients3(Patient rdPatients3) {
		getRdPatients3().add(rdPatients3);
		rdPatients3.setRdMunicipality3(this);

		return rdPatients3;
	}

	public Patient removeRdPatients3(Patient rdPatients3) {
		getRdPatients3().remove(rdPatients3);
		rdPatients3.setRdMunicipality3(null);

		return rdPatients3;
	}

}