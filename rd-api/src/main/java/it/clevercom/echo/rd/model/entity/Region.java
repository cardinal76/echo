package it.clevercom.echo.rd.model.entity;
// Generated 15-feb-2017 18.09.08 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Region generated by hbm2java
 */
@Entity
@Table(name = "REGION")
public class Region implements java.io.Serializable {

	private long idregion;
	private Country country;
	private String regionname;
	private String regionstdcode;
	private Date created;
	private Date updated;
	private String userupdate;
	private short active;
	private Set<Province> provinces = new HashSet<Province>(0);

	public Region() {
	}

	public Region(long idregion, Country country, Date created, Date updated, String userupdate, short active) {
		this.idregion = idregion;
		this.country = country;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
	}

	public Region(long idregion, Country country, String regionname, String regionstdcode, Date created, Date updated,
			String userupdate, short active, Set<Province> provinces) {
		this.idregion = idregion;
		this.country = country;
		this.regionname = regionname;
		this.regionstdcode = regionstdcode;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
		this.provinces = provinces;
	}

	@Id

	@Column(name = "IDREGION", unique = true, nullable = false, precision = 10, scale = 0)
	public long getIdregion() {
		return this.idregion;
	}

	public void setIdregion(long idregion) {
		this.idregion = idregion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDCOUNTRY", nullable = false)
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Column(name = "REGIONNAME", length = 100)
	public String getRegionname() {
		return this.regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	@Column(name = "REGIONSTDCODE", length = 10)
	public String getRegionstdcode() {
		return this.regionstdcode;
	}

	public void setRegionstdcode(String regionstdcode) {
		this.regionstdcode = regionstdcode;
	}

	@Column(name = "CREATED", nullable = false)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "UPDATED", nullable = false)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Column(name = "USERUPDATE", nullable = false, length = 100)
	public String getUserupdate() {
		return this.userupdate;
	}

	public void setUserupdate(String userupdate) {
		this.userupdate = userupdate;
	}

	@Column(name = "ACTIVE", nullable = false, precision = 3, scale = 0)
	public short getActive() {
		return this.active;
	}

	public void setActive(short active) {
		this.active = active;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "region")
	public Set<Province> getProvinces() {
		return this.provinces;
	}

	public void setProvinces(Set<Province> provinces) {
		this.provinces = provinces;
	}

}
