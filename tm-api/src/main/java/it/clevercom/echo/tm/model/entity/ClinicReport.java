package it.clevercom.echo.tm.model.entity;
// Generated 17-feb-2017 16.34.42 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ClinicReport generated by hbm2java
 */
@Entity
@Table(name = "clinic_report")
public class ClinicReport implements java.io.Serializable {

	private long idclinicreport;
	private ClinicFolder clinicFolder;
	private DetectionPlan detectionPlan;
	private String name;
	private String description;
	private String reportcontent;
	private Date created;
	private Date updated;
	private boolean active;
	private String updateuser;

	public ClinicReport() {
	}

	public ClinicReport(long idclinicreport, ClinicFolder clinicFolder, String name, Date created, Date updated,
			boolean active, String updateuser) {
		this.idclinicreport = idclinicreport;
		this.clinicFolder = clinicFolder;
		this.name = name;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateuser = updateuser;
	}

	public ClinicReport(long idclinicreport, ClinicFolder clinicFolder, DetectionPlan detectionPlan, String name,
			String description, String reportcontent, Date created, Date updated, boolean active, String updateuser) {
		this.idclinicreport = idclinicreport;
		this.clinicFolder = clinicFolder;
		this.detectionPlan = detectionPlan;
		this.name = name;
		this.description = description;
		this.reportcontent = reportcontent;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateuser = updateuser;
	}

	@Id

	@Column(name = "idclinicreport", unique = true, nullable = false)
	public long getIdclinicreport() {
		return this.idclinicreport;
	}

	public void setIdclinicreport(long idclinicreport) {
		this.idclinicreport = idclinicreport;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idclinicfolder", nullable = false)
	public ClinicFolder getClinicFolder() {
		return this.clinicFolder;
	}

	public void setClinicFolder(ClinicFolder clinicFolder) {
		this.clinicFolder = clinicFolder;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iddetectionplan")
	public DetectionPlan getDetectionPlan() {
		return this.detectionPlan;
	}

	public void setDetectionPlan(DetectionPlan detectionPlan) {
		this.detectionPlan = detectionPlan;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "reportcontent")
	public String getReportcontent() {
		return this.reportcontent;
	}

	public void setReportcontent(String reportcontent) {
		this.reportcontent = reportcontent;
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

}