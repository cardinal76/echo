package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rd_country database table.
 * 
 */
@Entity
@Table(name="rd_country")
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idcountry;

	private Boolean active;

	private String countryiso2;

	private String countryiso3;

	private Long countryisonumcode;

	private String countryname;

	private String countrynicename;

	private Timestamp created;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to Patient
	@OneToMany(mappedBy="rdCountry1")
	private List<Patient> rdPatients1;

	//bi-directional many-to-one association to Patient
	@OneToMany(mappedBy="rdCountry2")
	private List<Patient> rdPatients2;

	//bi-directional many-to-one association to Patient
	@OneToMany(mappedBy="rdCountry3")
	private List<Patient> rdPatients3;

	//bi-directional many-to-one association to Region
	@OneToMany(mappedBy="rdCountry")
	private List<Region> rdRegions;

	public Country() {
	}

	public Long getIdcountry() {
		return this.idcountry;
	}

	public void setIdcountry(Long idcountry) {
		this.idcountry = idcountry;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getCountryiso2() {
		return this.countryiso2;
	}

	public void setCountryiso2(String countryiso2) {
		this.countryiso2 = countryiso2;
	}

	public String getCountryiso3() {
		return this.countryiso3;
	}

	public void setCountryiso3(String countryiso3) {
		this.countryiso3 = countryiso3;
	}

	public Long getCountryisonumcode() {
		return this.countryisonumcode;
	}

	public void setCountryisonumcode(Long countryisonumcode) {
		this.countryisonumcode = countryisonumcode;
	}

	public String getCountryname() {
		return this.countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	public String getCountrynicename() {
		return this.countrynicename;
	}

	public void setCountrynicename(String countrynicename) {
		this.countrynicename = countrynicename;
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

	public String getUserupdate() {
		return this.userupdate;
	}

	public void setUserupdate(String userupdate) {
		this.userupdate = userupdate;
	}

	public List<Patient> getRdPatients1() {
		return this.rdPatients1;
	}

	public void setRdPatients1(List<Patient> rdPatients1) {
		this.rdPatients1 = rdPatients1;
	}

	public Patient addRdPatients1(Patient rdPatients1) {
		getRdPatients1().add(rdPatients1);
		rdPatients1.setRdCountry1(this);

		return rdPatients1;
	}

	public Patient removeRdPatients1(Patient rdPatients1) {
		getRdPatients1().remove(rdPatients1);
		rdPatients1.setRdCountry1(null);

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
		rdPatients2.setRdCountry2(this);

		return rdPatients2;
	}

	public Patient removeRdPatients2(Patient rdPatients2) {
		getRdPatients2().remove(rdPatients2);
		rdPatients2.setRdCountry2(null);

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
		rdPatients3.setRdCountry3(this);

		return rdPatients3;
	}

	public Patient removeRdPatients3(Patient rdPatients3) {
		getRdPatients3().remove(rdPatients3);
		rdPatients3.setRdCountry3(null);

		return rdPatients3;
	}

	public List<Region> getRdRegions() {
		return this.rdRegions;
	}

	public void setRdRegions(List<Region> rdRegions) {
		this.rdRegions = rdRegions;
	}

	public Region addRdRegion(Region rdRegion) {
		getRdRegions().add(rdRegion);
		rdRegion.setRdCountry(this);

		return rdRegion;
	}

	public Region removeRdRegion(Region rdRegion) {
		getRdRegions().remove(rdRegion);
		rdRegion.setRdCountry(null);

		return rdRegion;
	}

}