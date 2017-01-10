package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the treatmentplan database table.
 * 
 */
@Entity
@Table(name="treatmentplan")
@NamedQuery(name="Treatmentplan.findAll", query="SELECT t FROM Treatmentplan t")
public class Treatmentplan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTreatmentPlan;

	private Object active;

	private Timestamp created;

	private String description;

	private String name;

	private Timestamp updated;

	private String updateUser;

	@Temporal(TemporalType.TIMESTAMP)
	private Date validFrom;

	@Temporal(TemporalType.TIMESTAMP)
	private Date validTo;

	//bi-directional many-to-one association to Clinicfolder
	@ManyToOne
	@JoinColumn(name="idClinicFolder")
	private Clinicfolder clinicfolder;

	//bi-directional many-to-one association to Patient
	@ManyToOne
	@JoinColumn(name="idPatient")
	private Patient patient;

	//bi-directional many-to-one association to Treatmenttask
	@OneToMany(mappedBy="treatmentplan")
	private List<Treatmenttask> treatmenttasks;

	public Treatmentplan() {
	}

	public int getIdTreatmentPlan() {
		return this.idTreatmentPlan;
	}

	public void setIdTreatmentPlan(int idTreatmentPlan) {
		this.idTreatmentPlan = idTreatmentPlan;
	}

	public Object getActive() {
		return this.active;
	}

	public void setActive(Object active) {
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

	public Date getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return this.validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Clinicfolder getClinicfolder() {
		return this.clinicfolder;
	}

	public void setClinicfolder(Clinicfolder clinicfolder) {
		this.clinicfolder = clinicfolder;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<Treatmenttask> getTreatmenttasks() {
		return this.treatmenttasks;
	}

	public void setTreatmenttasks(List<Treatmenttask> treatmenttasks) {
		this.treatmenttasks = treatmenttasks;
	}

	public Treatmenttask addTreatmenttask(Treatmenttask treatmenttask) {
		getTreatmenttasks().add(treatmenttask);
		treatmenttask.setTreatmentplan(this);

		return treatmenttask;
	}

	public Treatmenttask removeTreatmenttask(Treatmenttask treatmenttask) {
		getTreatmenttasks().remove(treatmenttask);
		treatmenttask.setTreatmentplan(null);

		return treatmenttask;
	}

}