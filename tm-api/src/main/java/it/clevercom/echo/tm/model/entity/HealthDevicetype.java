package it.clevercom.echo.tm.model.entity;
// Generated 17-feb-2017 16.34.42 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * HealthDevicetype generated by hbm2java
 */
@Entity
@Table(name = "health_devicetype")
public class HealthDevicetype implements java.io.Serializable {

	private long idhealthdevicetype;
	private String modelname;
	private String attachmenturl;
	private String website;
	private Date created;
	private Date updated;
	private boolean active;
	private String updateuser;
	private Set<HealthDevice> healthDevices = new HashSet<HealthDevice>(0);

	public HealthDevicetype() {
	}

	public HealthDevicetype(long idhealthdevicetype, Date created, Date updated, boolean active, String updateuser) {
		this.idhealthdevicetype = idhealthdevicetype;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateuser = updateuser;
	}

	public HealthDevicetype(long idhealthdevicetype, String modelname, String attachmenturl, String website,
			Date created, Date updated, boolean active, String updateuser, Set<HealthDevice> healthDevices) {
		this.idhealthdevicetype = idhealthdevicetype;
		this.modelname = modelname;
		this.attachmenturl = attachmenturl;
		this.website = website;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateuser = updateuser;
		this.healthDevices = healthDevices;
	}

	@Id

	@Column(name = "idhealthdevicetype", unique = true, nullable = false)
	public long getIdhealthdevicetype() {
		return this.idhealthdevicetype;
	}

	public void setIdhealthdevicetype(long idhealthdevicetype) {
		this.idhealthdevicetype = idhealthdevicetype;
	}

	@Column(name = "modelname", length = 45)
	public String getModelname() {
		return this.modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}

	@Column(name = "attachmenturl", length = 45)
	public String getAttachmenturl() {
		return this.attachmenturl;
	}

	public void setAttachmenturl(String attachmenturl) {
		this.attachmenturl = attachmenturl;
	}

	@Column(name = "website", length = 45)
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
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

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name = "updateuser", nullable = false, length = 100)
	public String getUpdateuser() {
		return this.updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "healthDevicetype")
	public Set<HealthDevice> getHealthDevices() {
		return this.healthDevices;
	}

	public void setHealthDevices(Set<HealthDevice> healthDevices) {
		this.healthDevices = healthDevices;
	}

}
