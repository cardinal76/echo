package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the region database table.
 * 
 */
@Entity
@Table(name="region")
@NamedQuery(name="Region.findAll", query="SELECT r FROM Region r")
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idRegion;

	private Object active;

	private Timestamp created;

	private String regionName;

	private String regionStdCode;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Province
	@OneToMany(mappedBy="region")
	private List<Province> provinces;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="idCountry")
	private Country country;

	public Region() {
	}

	public int getIdRegion() {
		return this.idRegion;
	}

	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
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

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getRegionStdCode() {
		return this.regionStdCode;
	}

	public void setRegionStdCode(String regionStdCode) {
		this.regionStdCode = regionStdCode;
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

	public List<Province> getProvinces() {
		return this.provinces;
	}

	public void setProvinces(List<Province> provinces) {
		this.provinces = provinces;
	}

	public Province addProvince(Province province) {
		getProvinces().add(province);
		province.setRegion(this);

		return province;
	}

	public Province removeProvince(Province province) {
		getProvinces().remove(province);
		province.setRegion(null);

		return province;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}