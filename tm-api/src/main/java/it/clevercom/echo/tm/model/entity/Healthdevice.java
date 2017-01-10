package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the healthdevice database table.
 * 
 */
@Entity
@Table(name="healthdevice")
@NamedQuery(name="Healthdevice.findAll", query="SELECT h FROM Healthdevice h")
public class Healthdevice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idHealthDevice;

	private String serialNumber;

	//bi-directional many-to-one association to Healthdevicetype
	@ManyToOne
	@JoinColumn(name="idHealthDeviceType")
	private Healthdevicetype healthdevicetype;

	//bi-directional many-to-one association to Healthkit
	@ManyToOne
	@JoinColumn(name="idHealthKit")
	private Healthkit healthkit;

	public Healthdevice() {
	}

	public int getIdHealthDevice() {
		return this.idHealthDevice;
	}

	public void setIdHealthDevice(int idHealthDevice) {
		this.idHealthDevice = idHealthDevice;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Healthdevicetype getHealthdevicetype() {
		return this.healthdevicetype;
	}

	public void setHealthdevicetype(Healthdevicetype healthdevicetype) {
		this.healthdevicetype = healthdevicetype;
	}

	public Healthkit getHealthkit() {
		return this.healthkit;
	}

	public void setHealthkit(Healthkit healthkit) {
		this.healthkit = healthkit;
	}

}