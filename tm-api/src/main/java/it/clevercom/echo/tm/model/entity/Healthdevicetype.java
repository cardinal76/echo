package it.clevercom.echo.tm.model.entity;
// Generated 10-gen-2017 15.17.23 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Healthdevicetype generated by hbm2java
 */
@Entity
@Table(name = "healthdevicetype", catalog = "tmdw")
public class Healthdevicetype implements java.io.Serializable {

	private Integer idHealthDeviceType;
	private String modelName;
	private String attachmentUrl;
	private String website;
	private Date created;
	private Date updated;
	private boolean active;
	private String updateUser;
	private Set<Healthdevice> healthdevices = new HashSet<Healthdevice>(0);

	public Healthdevicetype() {
	}

	public Healthdevicetype(Date created, Date updated, boolean active, String updateUser) {
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
	}

	public Healthdevicetype(String modelName, String attachmentUrl, String website, Date created, Date updated,
			boolean active, String updateUser, Set<Healthdevice> healthdevices) {
		this.modelName = modelName;
		this.attachmentUrl = attachmentUrl;
		this.website = website;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
		this.healthdevices = healthdevices;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idHealthDeviceType", unique = true, nullable = false)
	public Integer getIdHealthDeviceType() {
		return this.idHealthDeviceType;
	}

	public void setIdHealthDeviceType(Integer idHealthDeviceType) {
		this.idHealthDeviceType = idHealthDeviceType;
	}

	@Column(name = "modelName", length = 45)
	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	@Column(name = "attachmentUrl", length = 45)
	public String getAttachmentUrl() {
		return this.attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	@Column(name = "website", length = 45)
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
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

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name = "updateUser", nullable = false, length = 100)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "healthdevicetype")
	public Set<Healthdevice> getHealthdevices() {
		return this.healthdevices;
	}

	public void setHealthdevices(Set<Healthdevice> healthdevices) {
		this.healthdevices = healthdevices;
	}

}