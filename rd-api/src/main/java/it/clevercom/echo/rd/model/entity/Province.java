package it.clevercom.echo.rd.model.entity;
// Generated 10-feb-2017 17.38.23 by Hibernate Tools 5.1.0.Final

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
 * Province generated by hbm2java
 */
@Entity
@Table(name = "province", catalog = "rmdw")
public class Province implements java.io.Serializable {

	private Integer idProvince;
	private Region region;
	private String provinceName;
	private String provinceStdCode;
	private Date created;
	private Date updated;
	private String updateUser;
	private boolean active;
	private Set<Municipality> municipalities = new HashSet<Municipality>(0);

	public Province() {
	}

	public Province(Region region, Date created, Date updated, String updateUser, boolean active) {
		this.region = region;
		this.created = created;
		this.updated = updated;
		this.updateUser = updateUser;
		this.active = active;
	}

	public Province(Region region, String provinceName, String provinceStdCode, Date created, Date updated,
			String updateUser, boolean active, Set<Municipality> municipalities) {
		this.region = region;
		this.provinceName = provinceName;
		this.provinceStdCode = provinceStdCode;
		this.created = created;
		this.updated = updated;
		this.updateUser = updateUser;
		this.active = active;
		this.municipalities = municipalities;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idProvince", unique = true, nullable = false)
	public Integer getIdProvince() {
		return this.idProvince;
	}

	public void setIdProvince(Integer idProvince) {
		this.idProvince = idProvince;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idRegion", nullable = false)
	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@Column(name = "provinceName", length = 100)
	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Column(name = "provinceStdCode", length = 10)
	public String getProvinceStdCode() {
		return this.provinceStdCode;
	}

	public void setProvinceStdCode(String provinceStdCode) {
		this.provinceStdCode = provinceStdCode;
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

	@Column(name = "updateUser", nullable = false, length = 100)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "province")
	public Set<Municipality> getMunicipalities() {
		return this.municipalities;
	}

	public void setMunicipalities(Set<Municipality> municipalities) {
		this.municipalities = municipalities;
	}

}
