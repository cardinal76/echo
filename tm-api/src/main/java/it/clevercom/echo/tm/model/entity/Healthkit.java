package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the healthkit database table.
 * 
 */
@Entity
@Table(name="healthkit")
@NamedQuery(name="Healthkit.findAll", query="SELECT h FROM Healthkit h")
public class Healthkit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idHealthKit;

	private byte active;

	private Timestamp created;

	private String description;

	private String name;

	private String serialNumber;

	private Timestamp updated;

	private String userUpdate;

	//bi-directional many-to-one association to Healthdevice
	@OneToMany(mappedBy="healthkit")
	private List<Healthdevice> healthdevices;

	//bi-directional many-to-one association to HealthkitPatient
	@OneToMany(mappedBy="healthkit")
	private List<HealthkitPatient> healthkitPatients;

	public Healthkit() {
	}

	public int getIdHealthKit() {
		return this.idHealthKit;
	}

	public void setIdHealthKit(int idHealthKit) {
		this.idHealthKit = idHealthKit;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getUserUpdate() {
		return this.userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	public List<Healthdevice> getHealthdevices() {
		return this.healthdevices;
	}

	public void setHealthdevices(List<Healthdevice> healthdevices) {
		this.healthdevices = healthdevices;
	}

	public Healthdevice addHealthdevice(Healthdevice healthdevice) {
		getHealthdevices().add(healthdevice);
		healthdevice.setHealthkit(this);

		return healthdevice;
	}

	public Healthdevice removeHealthdevice(Healthdevice healthdevice) {
		getHealthdevices().remove(healthdevice);
		healthdevice.setHealthkit(null);

		return healthdevice;
	}

	public List<HealthkitPatient> getHealthkitPatients() {
		return this.healthkitPatients;
	}

	public void setHealthkitPatients(List<HealthkitPatient> healthkitPatients) {
		this.healthkitPatients = healthkitPatients;
	}

	public HealthkitPatient addHealthkitPatient(HealthkitPatient healthkitPatient) {
		getHealthkitPatients().add(healthkitPatient);
		healthkitPatient.setHealthkit(this);

		return healthkitPatient;
	}

	public HealthkitPatient removeHealthkitPatient(HealthkitPatient healthkitPatient) {
		getHealthkitPatients().remove(healthkitPatient);
		healthkitPatient.setHealthkit(null);

		return healthkitPatient;
	}

}