package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rd_app_setting database table.
 * 
 */
@Entity
@Table(name="rd_app_setting")
@NamedQuery(name="AppSetting.findAll", query="SELECT a FROM AppSetting a")
public class AppSetting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idsetting;

	private Boolean active;

	private Timestamp created;

	private String group;

	private String key;

	private Timestamp updated;

	private String userupdate;

	private String value;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="iduser")
	private User rdUser;

	public AppSetting() {
	}

	public Long getIdsetting() {
		return this.idsetting;
	}

	public void setIdsetting(Long idsetting) {
		this.idsetting = idsetting;
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

	public String getGroup() {
		return this.group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
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

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public User getRdUser() {
		return this.rdUser;
	}

	public void setRdUser(User rdUser) {
		this.rdUser = rdUser;
	}

}