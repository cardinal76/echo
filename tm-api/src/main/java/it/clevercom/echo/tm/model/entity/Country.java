package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@Table(name="country")
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idCountry;

	private Object active;

	private String countryIso2;

	private String countryIso3;

	private int countryIsoNumCode;

	private String countryName;

	private String countryNicename;

	private Timestamp created;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Region
	@OneToMany(mappedBy="country")
	private List<Region> regions;

	public Country() {
	}

	public int getIdCountry() {
		return this.idCountry;
	}

	public void setIdCountry(int idCountry) {
		this.idCountry = idCountry;
	}

	public Object getActive() {
		return this.active;
	}

	public void setActive(Object active) {
		this.active = active;
	}

	public String getCountryIso2() {
		return this.countryIso2;
	}

	public void setCountryIso2(String countryIso2) {
		this.countryIso2 = countryIso2;
	}

	public String getCountryIso3() {
		return this.countryIso3;
	}

	public void setCountryIso3(String countryIso3) {
		this.countryIso3 = countryIso3;
	}

	public int getCountryIsoNumCode() {
		return this.countryIsoNumCode;
	}

	public void setCountryIsoNumCode(int countryIsoNumCode) {
		this.countryIsoNumCode = countryIsoNumCode;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryNicename() {
		return this.countryNicename;
	}

	public void setCountryNicename(String countryNicename) {
		this.countryNicename = countryNicename;
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

	public List<Region> getRegions() {
		return this.regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public Region addRegion(Region region) {
		getRegions().add(region);
		region.setCountry(this);

		return region;
	}

	public Region removeRegion(Region region) {
		getRegions().remove(region);
		region.setCountry(null);

		return region;
	}

}