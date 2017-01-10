package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the languagetype database table.
 * 
 */
@Entity
@Table(name="languagetype")
@NamedQuery(name="Languagetype.findAll", query="SELECT l FROM Languagetype l")
public class Languagetype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idLanguageType;

	private Object active;

	private String code;

	private Timestamp created;

	private String description;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Login
	@OneToMany(mappedBy="languagetype")
	private List<Login> logins;

	public Languagetype() {
	}

	public int getIdLanguageType() {
		return this.idLanguageType;
	}

	public void setIdLanguageType(int idLanguageType) {
		this.idLanguageType = idLanguageType;
	}

	public Object getActive() {
		return this.active;
	}

	public void setActive(Object active) {
		this.active = active;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public List<Login> getLogins() {
		return this.logins;
	}

	public void setLogins(List<Login> logins) {
		this.logins = logins;
	}

	public Login addLogin(Login login) {
		getLogins().add(login);
		login.setLanguagetype(this);

		return login;
	}

	public Login removeLogin(Login login) {
		getLogins().remove(login);
		login.setLanguagetype(null);

		return login;
	}

}