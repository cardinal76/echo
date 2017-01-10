package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the standardthreshold database table.
 * 
 */
@Entity
@Table(name="standardthreshold")
@NamedQuery(name="Standardthreshold.findAll", query="SELECT s FROM Standardthreshold s")
public class Standardthreshold implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idStandardThreshold;

	private Object active;

	private String commaEnumValues;

	private Timestamp created;

	private Object mustGenerateAlert;

	private Timestamp updated;

	private String updateUser;

	private float valueFrom;

	private float valueTo;

	//bi-directional many-to-one association to Customthreshold
	@OneToMany(mappedBy="standardthreshold")
	private List<Customthreshold> customthresholds;

	//bi-directional many-to-one association to Measurementtype
	@ManyToOne
	@JoinColumn(name="idMeasurementType")
	private Measurementtype measurementtype;

	public Standardthreshold() {
	}

	public int getIdStandardThreshold() {
		return this.idStandardThreshold;
	}

	public void setIdStandardThreshold(int idStandardThreshold) {
		this.idStandardThreshold = idStandardThreshold;
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

	public List<Customthreshold> getCustomthresholds() {
		return this.customthresholds;
	}

	public void setCustomthresholds(List<Customthreshold> customthresholds) {
		this.customthresholds = customthresholds;
	}

	public Customthreshold addCustomthreshold(Customthreshold customthreshold) {
		getCustomthresholds().add(customthreshold);
		customthreshold.setStandardthreshold(this);

		return customthreshold;
	}

	public Customthreshold removeCustomthreshold(Customthreshold customthreshold) {
		getCustomthresholds().remove(customthreshold);
		customthreshold.setStandardthreshold(null);

		return customthreshold;
	}

	public Measurementtype getMeasurementtype() {
		return this.measurementtype;
	}

	public void setMeasurementtype(Measurementtype measurementtype) {
		this.measurementtype = measurementtype;
	}

}