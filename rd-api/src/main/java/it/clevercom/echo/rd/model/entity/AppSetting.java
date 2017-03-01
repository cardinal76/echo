package it.clevercom.echo.rd.model.entity;
// Generated 28-feb-2017 10.00.08 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AppSetting generated by hbm2java
 */
@Entity
@Table(name = "app_setting")
public class AppSetting implements java.io.Serializable {

	private long idsetting;
	private User user;
	private String key;
	private String value;
	private Date created;
	private Date updated;
	private String userupdate;
	private boolean active;
	private String group;

	public AppSetting() {
	}

	public AppSetting(long idsetting, User user, String key, String value, Date created, Date updated, boolean active,
			String group) {
		this.idsetting = idsetting;
		this.user = user;
		this.key = key;
		this.value = value;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.group = group;
	}

	public AppSetting(long idsetting, User user, String key, String value, Date created, Date updated,
			String userupdate, boolean active, String group) {
		this.idsetting = idsetting;
		this.user = user;
		this.key = key;
		this.value = value;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
		this.group = group;
	}

	@Id

	@Column(name = "idsetting", unique = true, nullable = false)
	public long getIdsetting() {
		return this.idsetting;
	}

	public void setIdsetting(long idsetting) {
		this.idsetting = idsetting;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iduser")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "key", nullable = false)
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "value", nullable = false, length = 1000)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, length = 29)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = false, length = 29)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Column(name = "userupdate", length = 100)
	public String getUserupdate() {
		return this.userupdate;
	}

	public void setUserupdate(String userupdate) {
		this.userupdate = userupdate;
	}

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name = "group")
	public String getGroup() {
		return this.group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

}
