package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the healthdevicetype database table.
 * 
 */
@Entity
@Table(name="healthdevicetype")
@NamedQuery(name="Healthdevicetype.findAll", query="SELECT h FROM Healthdevicetype h")
public class Healthdevicetype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idHealthDeviceType;

	private byte active;

	private String attachmentUrl;

	private Timestamp created;

	private String modelName;

	private Timestamp updated;

	private String updateUser;

	private String website;

	//bi-directional many-to-one association to Healthdevice
	@OneToMany(mappedBy="healthdevicetype")
	private List<Healthdevice> healthdevices;

	public Healthdevicetype() {
	}

	public int getIdHealthDeviceType() {
		return this.idHealthDeviceType;
	}

	public void setIdHealthDeviceType(int idHealthDeviceType) {
		this.idHealthDeviceType = idHealthDeviceType;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getAttachmentUrl() {
		return this.attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
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

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List<Healthdevice> getHealthdevices() {
		return this.healthdevices;
	}

	public void setHealthdevices(List<Healthdevice> healthdevices) {
		this.healthdevices = healthdevices;
	}

	public Healthdevice addHealthdevice(Healthdevice healthdevice) {
		getHealthdevices().add(healthdevice);
		healthdevice.setHealthdevicetype(this);

		return healthdevice;
	}

	public Healthdevice removeHealthdevice(Healthdevice healthdevice) {
		getHealthdevices().remove(healthdevice);
		healthdevice.setHealthdevicetype(null);

		return healthdevice;
	}

}