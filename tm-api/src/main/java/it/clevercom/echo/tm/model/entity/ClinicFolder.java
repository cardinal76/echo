package it.clevercom.echo.tm.model.entity;
// Generated 11-apr-2017 13.43.21 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ClinicFolder generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="tm_clinic_folder"
)
public class ClinicFolder  implements java.io.Serializable {


     private Long idclinicfolder;
     private OrganizationUnit organizationUnit;
     private Patient patient;
     private Date dateopened;
     private Date dateclosed;
     private String hxfamilyascending;
     private String hxfamilysiblings;
     private String hxpersonalphysiological;
     private String hxrecentpathological;
     private String hxremotepathological;
     private String eoinspection;
     private String eopalpation;
     private String eopercussion;
     private String eoauscultation;
     private Date created;
     private Date updated;
     private boolean active;
     private String updateuser;
     private Set<ClinicReport> clinicReports = new HashSet<ClinicReport>(0);
     private Set<TreatmentPlan> treatmentPlans = new HashSet<TreatmentPlan>(0);

    public ClinicFolder() {
    }

	
    public ClinicFolder(Long idclinicfolder, OrganizationUnit organizationUnit, Patient patient, Date created, Date updated, boolean active, String updateuser) {
        this.idclinicfolder = idclinicfolder;
        this.organizationUnit = organizationUnit;
        this.patient = patient;
        this.created = created;
        this.updated = updated;
        this.active = active;
        this.updateuser = updateuser;
    }
    public ClinicFolder(Long idclinicfolder, OrganizationUnit organizationUnit, Patient patient, Date dateopened, Date dateclosed, String hxfamilyascending, String hxfamilysiblings, String hxpersonalphysiological, String hxrecentpathological, String hxremotepathological, String eoinspection, String eopalpation, String eopercussion, String eoauscultation, Date created, Date updated, boolean active, String updateuser, Set<ClinicReport> clinicReports, Set<TreatmentPlan> treatmentPlans) {
       this.idclinicfolder = idclinicfolder;
       this.organizationUnit = organizationUnit;
       this.patient = patient;
       this.dateopened = dateopened;
       this.dateclosed = dateclosed;
       this.hxfamilyascending = hxfamilyascending;
       this.hxfamilysiblings = hxfamilysiblings;
       this.hxpersonalphysiological = hxpersonalphysiological;
       this.hxrecentpathological = hxrecentpathological;
       this.hxremotepathological = hxremotepathological;
       this.eoinspection = eoinspection;
       this.eopalpation = eopalpation;
       this.eopercussion = eopercussion;
       this.eoauscultation = eoauscultation;
       this.created = created;
       this.updated = updated;
       this.active = active;
       this.updateuser = updateuser;
       this.clinicReports = clinicReports;
       this.treatmentPlans = treatmentPlans;
    }
   
     @Id 

    
    @Column(name="idclinicfolder", unique=true, nullable=false)
    public Long getIdclinicfolder() {
        return this.idclinicfolder;
    }
    
    public void setIdclinicfolder(Long idclinicfolder) {
        this.idclinicfolder = idclinicfolder;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idorganizationunit", nullable=false)
    public OrganizationUnit getOrganizationUnit() {
        return this.organizationUnit;
    }
    
    public void setOrganizationUnit(OrganizationUnit organizationUnit) {
        this.organizationUnit = organizationUnit;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idpatient", nullable=false)
    public Patient getPatient() {
        return this.patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="dateopened", length=13)
    public Date getDateopened() {
        return this.dateopened;
    }
    
    public void setDateopened(Date dateopened) {
        this.dateopened = dateopened;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="dateclosed", length=13)
    public Date getDateclosed() {
        return this.dateclosed;
    }
    
    public void setDateclosed(Date dateclosed) {
        this.dateclosed = dateclosed;
    }

    
    @Column(name="hxfamilyascending")
    public String getHxfamilyascending() {
        return this.hxfamilyascending;
    }
    
    public void setHxfamilyascending(String hxfamilyascending) {
        this.hxfamilyascending = hxfamilyascending;
    }

    
    @Column(name="hxfamilysiblings")
    public String getHxfamilysiblings() {
        return this.hxfamilysiblings;
    }
    
    public void setHxfamilysiblings(String hxfamilysiblings) {
        this.hxfamilysiblings = hxfamilysiblings;
    }

    
    @Column(name="hxpersonalphysiological")
    public String getHxpersonalphysiological() {
        return this.hxpersonalphysiological;
    }
    
    public void setHxpersonalphysiological(String hxpersonalphysiological) {
        this.hxpersonalphysiological = hxpersonalphysiological;
    }

    
    @Column(name="hxrecentpathological")
    public String getHxrecentpathological() {
        return this.hxrecentpathological;
    }
    
    public void setHxrecentpathological(String hxrecentpathological) {
        this.hxrecentpathological = hxrecentpathological;
    }

    
    @Column(name="hxremotepathological")
    public String getHxremotepathological() {
        return this.hxremotepathological;
    }
    
    public void setHxremotepathological(String hxremotepathological) {
        this.hxremotepathological = hxremotepathological;
    }

    
    @Column(name="eoinspection")
    public String getEoinspection() {
        return this.eoinspection;
    }
    
    public void setEoinspection(String eoinspection) {
        this.eoinspection = eoinspection;
    }

    
    @Column(name="eopalpation")
    public String getEopalpation() {
        return this.eopalpation;
    }
    
    public void setEopalpation(String eopalpation) {
        this.eopalpation = eopalpation;
    }

    
    @Column(name="eopercussion")
    public String getEopercussion() {
        return this.eopercussion;
    }
    
    public void setEopercussion(String eopercussion) {
        this.eopercussion = eopercussion;
    }

    
    @Column(name="eoauscultation")
    public String getEoauscultation() {
        return this.eoauscultation;
    }
    
    public void setEoauscultation(String eoauscultation) {
        this.eoauscultation = eoauscultation;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created", nullable=false, length=29)
    public Date getCreated() {
        return this.created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated", nullable=false, length=29)
    public Date getUpdated() {
        return this.updated;
    }
    
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    
    @Column(name="active", nullable=false)
    public boolean isActive() {
        return this.active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }

    
    @Column(name="updateuser", nullable=false, length=100)
    public String getUpdateuser() {
        return this.updateuser;
    }
    
    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="clinicFolder")
    public Set<ClinicReport> getClinicReports() {
        return this.clinicReports;
    }
    
    public void setClinicReports(Set<ClinicReport> clinicReports) {
        this.clinicReports = clinicReports;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="clinicFolder")
    public Set<TreatmentPlan> getTreatmentPlans() {
        return this.treatmentPlans;
    }
    
    public void setTreatmentPlans(Set<TreatmentPlan> treatmentPlans) {
        this.treatmentPlans = treatmentPlans;
    }




}


