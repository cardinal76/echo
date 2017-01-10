package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the customthreshold database table.
 * 
 */
@Entity
@Table(name="customthreshold")
@NamedQuery(name="Customthreshold.findAll", query="SELECT c FROM Customthreshold c")
public class Customthreshold implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idCustomThreshold;

	private Object active;

	private String commaEnumValues;

	private Timestamp created;

	private Object mustGenerateAlert;

	private Timestamp updated;

	private String updateUser;

	private float valueFrom;

	private float valueTo;

	//bi-directional many-to-one association to Patient
	@ManyToOne
	@JoinColumn(name="idPatient")
	private Patient patient;

	//bi-directional many-to-one association to Standardthreshold
	@ManyToOne
	@JoinColumn(name="idStandardThreshold")
	private Standardthreshold standardthreshold;

	public Customthreshold() {
	}

	public int getIdCustomThreshold() {
		return this.idCustomThreshold;
	}

	public void setIdCustomThreshold(int idCustomThreshold) {
		this.idCustomThreshold = idCustomThreshold;
	}

	public Object getActive() {
		return this.active;
	}

	public void setActive(Object active) {
		this.active = active;
	}

	public String getCommaEnumValues() {
		return this.commaEnumValues;
	}

	public void setCommaEnumValues(String commaEnumValues) {
		this.commaEnumValues = commaEnumValues;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Object getMustGenerateAlert() {
		return this.mustGenerateAlert;
	}

	public void setMustGenerateAlert(Object mustGenerateAlert) {
		this.mustGenerateAlert = mustGenerateAlert;
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

	public float getValueFrom() {
		return this.valueFrom;
	}

	public void setValueFrom(float valueFrom) {
		this.valueFrom = valueFrom;
	}

	public float getValueTo() {
		return this.valueTo;
	}

	public void setValueTo(float valueTo) {
		this.valueTo = valueTo;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Standardthreshold getStandardthreshold() {
		return this.standardthreshold;
	}

	public void setStandardthreshold(Standardthreshold standardthreshold) {
		this.standardthreshold = standardthreshold;
	}

}