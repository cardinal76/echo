package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rd_region database table.
 * 
 */
@Entity
@Table(name="rd_region")
@NamedQuery(name="Region.findAll", query="SELECT r FROM Region r")
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idregion;

	private Boolean active;

	private Timestamp created;

	private String regionname;

	private String regionstdcode;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to Province
	@OneToMany(mappedBy="rdRegion")
	private List<Province> rdProvinces;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="idcountry")
	private Country rdCountry;

	public Region() {
	}

	public Long getIdregion() {
		return this.idregion;
	}

	public void setIdregion(Long idregion) {
		this.idregion = idregion;
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

	public String getRegionname() {
		return this.regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	public String getRegionstdcode() {
		return this.regionstdcode;
	}

	public void setRegionstdcode(String regionstdcode) {
		this.regionstdcode = regionstdcode;
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

	public List<Province> getRdProvinces() {
		return this.rdProvinces;
	}

	public void setRdProvinces(List<Province> rdProvinces) {
		this.rdProvinces = rdProvinces;
	}

	public Province addRdProvince(Province rdProvince) {
		getRdProvinces().add(rdProvince);
		rdProvince.setRdRegion(this);

		return rdProvince;
	}

	public Province removeRdProvince(Province rdProvince) {
		getRdProvinces().remove(rdProvince);
		rdProvince.setRdRegion(null);

		return rdProvince;
	}

	public Country getRdCountry() {
		return this.rdCountry;
	}

	public void setRdCountry(Country rdCountry) {
		this.rdCountry = rdCountry;
	}

}