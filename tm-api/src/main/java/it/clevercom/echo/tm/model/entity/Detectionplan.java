package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the detectionplan database table.
 * 
 */
@Entity
@Table(name="detectionplan")
@NamedQuery(name="Detectionplan.findAll", query="SELECT d FROM Detectionplan d")
public class Detectionplan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idDetectionPlan;

	private Object active;

	private Timestamp created;

	private String description;

	private String name;

	private Timestamp updated;

	private String updateUser;

	@Temporal(TemporalType.DATE)
	private Date validFrom;

	@Temporal(TemporalType.DATE)
	private Date validTo;

	//bi-directional many-to-one association to Clinicreport
	@OneToMany(mappedBy="detectionplan")
	private List<Clinicreport> clinicreports;

	//bi-directional many-to-one association to Patient
	@ManyToOne
	@JoinColumn(name="idPatient")
	private Patient patient;

	//bi-directional many-to-one association to Detectiontask
	@OneToMany(mappedBy="detectionplan")
	private List<Detectiontask> detectiontasks;

	public Detectionplan() {
	}

	public int getIdDetectionPlan() {
		return this.idDetectionPlan;
	}

	public void setIdDetectionPlan(int idDetectionPlan) {
		this.idDetectionPlan = idDetectionPlan;
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

	public List<Clinicreport> getClinicreports() {
		return this.clinicreports;
	}

	public void setClinicreports(List<Clinicreport> clinicreports) {
		this.clinicreports = clinicreports;
	}

	public Clinicreport addClinicreport(Clinicreport clinicreport) {
		getClinicreports().add(clinicreport);
		clinicreport.setDetectionplan(this);

		return clinicreport;
	}

	public Clinicreport removeClinicreport(Clinicreport clinicreport) {
		getClinicreports().remove(clinicreport);
		clinicreport.setDetectionplan(null);

		return clinicreport;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<Detectiontask> getDetectiontasks() {
		return this.detectiontasks;
	}

	public void setDetectiontasks(List<Detectiontask> detectiontasks) {
		this.detectiontasks = detectiontasks;
	}

	public Detectiontask addDetectiontask(Detectiontask detectiontask) {
		getDetectiontasks().add(detectiontask);
		detectiontask.setDetectionplan(this);

		return detectiontask;
	}

	public Detectiontask removeDetectiontask(Detectiontask detectiontask) {
		getDetectiontasks().remove(detectiontask);
		detectiontask.setDetectionplan(null);

		return detectiontask;
	}

}