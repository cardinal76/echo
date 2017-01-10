package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the clinicfolder database table.
 * 
 */
@Entity
@Table(name="clinicfolder")
@NamedQuery(name="Clinicfolder.findAll", query="SELECT c FROM Clinicfolder c")
public class Clinicfolder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idClinicFolder;

	private Object active;

	private Timestamp created;

	@Temporal(TemporalType.DATE)
	private Date dateClosed;

	@Temporal(TemporalType.DATE)
	private Date dateOpened;

	private Object eoAuscultation;

	private Object eoInspection;

	private Object eoPalpation;

	private Object eoPercussion;

	private Object hxFamilyAscending;

	private Object hxFamilySiblings;

	private Object hxPersonalPhysiological;

	private Object hxRecentPathological;

	private Object hxRemotePathological;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Organizationunit
	@ManyToOne
	@JoinColumn(name="idOrganizationUnit")
	private Organizationunit organizationunit;

	//bi-directional many-to-one association to Patient
	@ManyToOne
	@JoinColumn(name="idPatient")
	private Patient patient;

	//bi-directional many-to-one association to Clinicreport
	@OneToMany(mappedBy="clinicfolder")
	private List<Clinicreport> clinicreports;

	//bi-directional many-to-one association to Treatmentplan
	@OneToMany(mappedBy="clinicfolder")
	private List<Treatmentplan> treatmentplans;

	public Clinicfolder() {
	}

	public int getIdClinicFolder() {
		return this.idClinicFolder;
	}

	public void setIdClinicFolder(int idClinicFolder) {
		this.idClinicFolder = idClinicFolder;
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

	public Date getDateClosed() {
		return this.dateClosed;
	}

	public void setDateClosed(Date dateClosed) {
		this.dateClosed = dateClosed;
	}

	public Date getDateOpened() {
		return this.dateOpened;
	}

	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}

	public Object getEoAuscultation() {
		return this.eoAuscultation;
	}

	public void setEoAuscultation(Object eoAuscultation) {
		this.eoAuscultation = eoAuscultation;
	}

	public Object getEoInspection() {
		return this.eoInspection;
	}

	public void setEoInspection(Object eoInspection) {
		this.eoInspection = eoInspection;
	}

	public Object getEoPalpation() {
		return this.eoPalpation;
	}

	public void setEoPalpation(Object eoPalpation) {
		this.eoPalpation = eoPalpation;
	}

	public Object getEoPercussion() {
		return this.eoPercussion;
	}

	public void setEoPercussion(Object eoPercussion) {
		this.eoPercussion = eoPercussion;
	}

	public Object getHxFamilyAscending() {
		return this.hxFamilyAscending;
	}

	public void setHxFamilyAscending(Object hxFamilyAscending) {
		this.hxFamilyAscending = hxFamilyAscending;
	}

	public Object getHxFamilySiblings() {
		return this.hxFamilySiblings;
	}

	public void setHxFamilySiblings(Object hxFamilySiblings) {
		this.hxFamilySiblings = hxFamilySiblings;
	}

	public Object getHxPersonalPhysiological() {
		return this.hxPersonalPhysiological;
	}

	public void setHxPersonalPhysiological(Object hxPersonalPhysiological) {
		this.hxPersonalPhysiological = hxPersonalPhysiological;
	}

	public Object getHxRecentPathological() {
		return this.hxRecentPathological;
	}

	public void setHxRecentPathological(Object hxRecentPathological) {
		this.hxRecentPathological = hxRecentPathological;
	}

	public Object getHxRemotePathological() {
		return this.hxRemotePathological;
	}

	public void setHxRemotePathological(Object hxRemotePathological) {
		this.hxRemotePathological = hxRemotePathological;
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

	public Organizationunit getOrganizationunit() {
		return this.organizationunit;
	}

	public void setOrganizationunit(Organizationunit organizationunit) {
		this.organizationunit = organizationunit;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<Clinicreport> getClinicreports() {
		return this.clinicreports;
	}

	public void setClinicreports(List<Clinicreport> clinicreports) {
		this.clinicreports = clinicreports;
	}

	public Clinicreport addClinicreport(Clinicreport clinicreport) {
		getClinicreports().add(clinicreport);
		clinicreport.setClinicfolder(this);

		return clinicreport;
	}

	public Clinicreport removeClinicreport(Clinicreport clinicreport) {
		getClinicreports().remove(clinicreport);
		clinicreport.setClinicfolder(null);

		return clinicreport;
	}

	public List<Treatmentplan> getTreatmentplans() {
		return this.treatmentplans;
	}

	public void setTreatmentplans(List<Treatmentplan> treatmentplans) {
		this.treatmentplans = treatmentplans;
	}

	public Treatmentplan addTreatmentplan(Treatmentplan treatmentplan) {
		getTreatmentplans().add(treatmentplan);
		treatmentplan.setClinicfolder(this);

		return treatmentplan;
	}

	public Treatmentplan removeTreatmentplan(Treatmentplan treatmentplan) {
		getTreatmentplans().remove(treatmentplan);
		treatmentplan.setClinicfolder(null);

		return treatmentplan;
	}

}