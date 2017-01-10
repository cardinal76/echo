package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the clinicreport database table.
 * 
 */
@Entity
@Table(name="clinicreport")
@NamedQuery(name="Clinicreport.findAll", query="SELECT c FROM Clinicreport c")
public class Clinicreport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idClinicReport;

	private Object active;

	private Timestamp created;

	private String description;

	private String name;

	private Object reportContent;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Clinicfolder
	@ManyToOne
	@JoinColumn(name="idClinicFolder")
	private Clinicfolder clinicfolder;

	//bi-directional many-to-one association to Detectionplan
	@ManyToOne
	@JoinColumn(name="idDetectionPlan")
	private Detectionplan detectionplan;

	public Clinicreport() {
	}

	public int getIdClinicReport() {
		return this.idClinicReport;
	}

	public void setIdClinicReport(int idClinicReport) {
		this.idClinicReport = idClinicReport;
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

	public Object getReportContent() {
		return this.reportContent;
	}

	public void setReportContent(Object reportContent) {
		this.reportContent = reportContent;
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

	public Clinicfolder getClinicfolder() {
		return this.clinicfolder;
	}

	public void setClinicfolder(Clinicfolder clinicfolder) {
		this.clinicfolder = clinicfolder;
	}

	public Detectionplan getDetectionplan() {
		return this.detectionplan;
	}

	public void setDetectionplan(Detectionplan detectionplan) {
		this.detectionplan = detectionplan;
	}

}