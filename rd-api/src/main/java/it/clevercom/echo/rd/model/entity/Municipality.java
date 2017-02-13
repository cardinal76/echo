package it.clevercom.echo.rd.model.entity;
// Generated 13-feb-2017 9.08.37 by Hibernate Tools 5.1.0.Final

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
 * Municipality generated by hbm2java
 */
@Entity
@Table(name = "municipality", catalog = "rmdw")
public class Municipality implements java.io.Serializable {

	private Integer idMunicipality;
	private Province province;
	private String municipalityName;
	private String municipalityStdCode;
	private Date created;
	private Date updated;
	private String updateUser;
	private boolean active;
	private Set<Patient> patients = new HashSet<Patient>(0);
	private Set<User> users = new HashSet<User>(0);

	public Municipality() {
	}

	public Municipality(Province province, Date created, Date updated, String updateUser, boolean active) {
		this.province = province;
		this.created = created;
		this.updated = updated;
		this.updateUser = updateUser;
		this.active = active;
	}

	public Municipality(Province province, String municipalityName, String municipalityStdCode, Date created,
			Date updated, String updateUser, boolean active, Set<Patient> patients, Set<User> users) {
		this.province = province;
		this.municipalityName = municipalityName;
		this.municipalityStdCode = municipalityStdCode;
		this.created = created;
		this.updated = updated;
		this.updateUser = updateUser;
		this.active = active;
		this.patients = patients;
		this.users = users;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idMunicipality", unique = true, nullable = false)
	public Integer getIdMunicipality() {
		return this.idMunicipality;
	}

	public void setIdMunicipality(Integer idMunicipality) {
		this.idMunicipality = idMunicipality;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idProvince", nullable = false)
	public Province getProvince() {
		return this.province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	@Column(name = "municipalityName", length = 100)
	public String getMunicipalityName() {
		return this.municipalityName;
	}

	public void setMunicipalityName(String municipalityName) {
		this.municipalityName = municipalityName;
	}

	@Column(name = "municipalityStdCode", length = 10)
	public String getMunicipalityStdCode() {
		return this.municipalityStdCode;
	}

	public void setMunicipalityStdCode(String municipalityStdCode) {
		this.municipalityStdCode = municipalityStdCode;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "municipality")
	public Set<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "municipality")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}