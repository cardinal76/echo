package it.clevercom.echo.tm.model.entity;
// Generated 10-gen-2017 15.17.23 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clinicfolder generated by hbm2java
 */
@Entity
@Table(name = "clinicfolder", catalog = "tmdw")
public class Clinicfolder implements java.io.Serializable {

	private Integer idClinicFolder;
	private Organizationunit organizationunit;
	private Patient patient;
	private Date dateOpened;
	private Date dateClosed;
	private String hxFamilyAscending;
	private String hxFamilySiblings;
	private String hxPersonalPhysiological;
	private String hxRecentPathological;
	private String hxRemotePathological;
	private String eoInspection;
	private String eoPalpation;
	private String eoPercussion;
	private String eoAuscultation;
	private Date created;
	private Date updated;
	private boolean active;
	private String updateUser;
	private Set<Treatmentplan> treatmentplans = new HashSet<Treatmentplan>(0);
	private Set<Clinicreport> clinicreports = new HashSet<Clinicreport>(0);

	public Clinicfolder() {
	}

	public Clinicfolder(Organizationunit organizationunit, Patient patient, Date created, Date updated, boolean active,
			String updateUser) {
		this.organizationunit = organizationunit;
		this.patient = patient;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
	}

	public Clinicfolder(Organizationunit organizationunit, Patient patient, Date dateOpened, Date dateClosed,
			String hxFamilyAscending, String hxFamilySiblings, String hxPersonalPhysiological,
			String hxRecentPathological, String hxRemotePathological, String eoInspection, String eoPalpation,
			String eoPercussion, String eoAuscultation, Date created, Date updated, boolean active, String updateUser,
			Set<Treatmentplan> treatmentplans, Set<Clinicreport> clinicreports) {
		this.organizationunit = organizationunit;
		this.patient = patient;
		this.dateOpened = dateOpened;
		this.dateClosed = dateClosed;
		this.hxFamilyAscending = hxFamilyAscending;
		this.hxFamilySiblings = hxFamilySiblings;
		this.hxPersonalPhysiological = hxPersonalPhysiological;
		this.hxRecentPathological = hxRecentPathological;
		this.hxRemotePathological = hxRemotePathological;
		this.eoInspection = eoInspection;
		this.eoPalpation = eoPalpation;
		this.eoPercussion = eoPercussion;
		this.eoAuscultation = eoAuscultation;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
		this.treatmentplans = treatmentplans;
		this.clinicreports = clinicreports;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idClinicFolder", unique = true, nullable = false)
	public Integer getIdClinicFolder() {
		return this.idClinicFolder;
	}

	public void setIdClinicFolder(Integer idClinicFolder) {
		this.idClinicFolder = idClinicFolder;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idOrganizationUnit", nullable = false)
	public Organizationunit getOrganizationunit() {
		return this.organizationunit;
	}

	public void setOrganizationunit(Organizationunit organizationunit) {
		this.organizationunit = organizationunit;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPatient", nullable = false)
	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateOpened", length = 10)
	public Date getDateOpened() {
		return this.dateOpened;
	}

	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateClosed", length = 10)
	public Date getDateClosed() {
		return this.dateClosed;
	}

	public void setDateClosed(Date dateClosed) {
		this.dateClosed = dateClosed;
	}

	@Column(name = "hxFamilyAscending")
	public String getHxFamilyAscending() {
		return this.hxFamilyAscending;
	}

	public void setHxFamilyAscending(String hxFamilyAscending) {
		this.hxFamilyAscending = hxFamilyAscending;
	}

	@Column(name = "hxFamilySiblings")
	public String getHxFamilySiblings() {
		return this.hxFamilySiblings;
	}

	public void setHxFamilySiblings(String hxFamilySiblings) {
		this.hxFamilySiblings = hxFamilySiblings;
	}

	@Column(name = "hxPersonalPhysiological")
	public String getHxPersonalPhysiological() {
		return this.hxPersonalPhysiological;
	}

	public void setHxPersonalPhysiological(String hxPersonalPhysiological) {
		this.hxPersonalPhysiological = hxPersonalPhysiological;
	}

	@Column(name = "hxRecentPathological")
	public String getHxRecentPathological() {
		return this.hxRecentPathological;
	}

	public void setHxRecentPathological(String hxRecentPathological) {
		this.hxRecentPathological = hxRecentPathological;
	}

	@Column(name = "hxRemotePathological")
	public String getHxRemotePathological() {
		return this.hxRemotePathological;
	}

	public void setHxRemotePathological(String hxRemotePathological) {
		this.hxRemotePathological = hxRemotePathological;
	}

	@Column(name = "eoInspection")
	public String getEoInspection() {
		return this.eoInspection;
	}

	public void setEoInspection(String eoInspection) {
		this.eoInspection = eoInspection;
	}

	@Column(name = "eoPalpation")
	public String getEoPalpation() {
		return this.eoPalpation;
	}

	public void setEoPalpation(String eoPalpation) {
		this.eoPalpation = eoPalpation;
	}

	@Column(name = "eoPercussion")
	public String getEoPercussion() {
		return this.eoPercussion;
	}

	public void setEoPercussion(String eoPercussion) {
		this.eoPercussion = eoPercussion;
	}

	@Column(name = "eoAuscultation")
	public String getEoAuscultation() {
		return this.eoAuscultation;
	}

	public void setEoAuscultation(String eoAuscultation) {
		this.eoAuscultation = eoAuscultation;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = false, length = 19)
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

	@Column(name = "updateUser", nullable = false, length = 100)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clinicfolder")
	public Set<Treatmentplan> getTreatmentplans() {
		return this.treatmentplans;
	}

	public void setTreatmentplans(Set<Treatmentplan> treatmentplans) {
		this.treatmentplans = treatmentplans;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clinicfolder")
	public Set<Clinicreport> getClinicreports() {
		return this.clinicreports;
	}

	public void setClinicreports(Set<Clinicreport> clinicreports) {
		this.clinicreports = clinicreports;
	}

}
