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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "rmdw")
public class User implements java.io.Serializable {

	private Integer idUser;
	private Municipality municipality;
	private String username;
	private String type;
	private String name;
	private String surname;
	private Character gender;
	private String taxCode;
	private Date birthDate;
	private String nationality;
	private String homeAddress;
	private String phoneNumber;
	private Date created;
	private Date updated;
	private String userUpdate;
	private boolean active;
	private Set<Phrasebook> phrasebooks = new HashSet<Phrasebook>(0);
	private Set<Worklist> worklists = new HashSet<Worklist>(0);
	private Set<Report> reports = new HashSet<Report>(0);

	public User() {
	}

	public User(String username, String type, String name, String surname, Date created, Date updated,
			String userUpdate, boolean active) {
		this.username = username;
		this.type = type;
		this.name = name;
		this.surname = surname;
		this.created = created;
		this.updated = updated;
		this.userUpdate = userUpdate;
		this.active = active;
	}

	public User(Municipality municipality, String username, String type, String name, String surname, Character gender,
			String taxCode, Date birthDate, String nationality, String homeAddress, String phoneNumber, Date created,
			Date updated, String userUpdate, boolean active, Set<Phrasebook> phrasebooks, Set<Worklist> worklists,
			Set<Report> reports) {
		this.municipality = municipality;
		this.username = username;
		this.type = type;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.taxCode = taxCode;
		this.birthDate = birthDate;
		this.nationality = nationality;
		this.homeAddress = homeAddress;
		this.phoneNumber = phoneNumber;
		this.created = created;
		this.updated = updated;
		this.userUpdate = userUpdate;
		this.active = active;
		this.phrasebooks = phrasebooks;
		this.worklists = worklists;
		this.reports = reports;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idUser", unique = true, nullable = false)
	public Integer getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idMunicipality")
	public Municipality getMunicipality() {
		return this.municipality;
	}

	public void setMunicipality(Municipality municipality) {
		this.municipality = municipality;
	}

	@Column(name = "username", nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "type", nullable = false, length = 21)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "surname", nullable = false, length = 100)
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Column(name = "gender", length = 1)
	public Character getGender() {
		return this.gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	@Column(name = "taxCode", length = 50)
	public String getTaxCode() {
		return this.taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthDate", length = 10)
	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name = "nationality", length = 100)
	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name = "homeAddress")
	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	@Column(name = "phoneNumber", length = 100)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	@Column(name = "userUpdate", nullable = false, length = 100)
	public String getUserUpdate() {
		return this.userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Phrasebook> getPhrasebooks() {
		return this.phrasebooks;
	}

	public void setPhrasebooks(Set<Phrasebook> phrasebooks) {
		this.phrasebooks = phrasebooks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Worklist> getWorklists() {
		return this.worklists;
	}

	public void setWorklists(Set<Worklist> worklists) {
		this.worklists = worklists;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_report", catalog = "rmdw", joinColumns = {
			@JoinColumn(name = "idUser", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "idReport", nullable = false, updatable = false) })
	public Set<Report> getReports() {
		return this.reports;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

}
