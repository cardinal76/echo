package it.clevercom.echo.tm.model.entity;
// Generated 11-apr-2017 13.43.21 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CustomThreshold generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="tm_custom_threshold"
)
public class CustomThreshold  implements java.io.Serializable {


     private Long idcustomthreshold;
     private Patient patient;
     private StandardThreshold standardThreshold;
     private Double valuefrom;
     private Double valueto;
     private Boolean mustgeneratealert;
     private String commaenumvalues;
     private Date created;
     private Date updated;
     private boolean active;
     private String updateuser;

    public CustomThreshold() {
    }

	
    public CustomThreshold(Long idcustomthreshold, Patient patient, StandardThreshold standardThreshold, Date created, Date updated, boolean active, String updateuser) {
        this.idcustomthreshold = idcustomthreshold;
        this.patient = patient;
        this.standardThreshold = standardThreshold;
        this.created = created;
        this.updated = updated;
        this.active = active;
        this.updateuser = updateuser;
    }
    public CustomThreshold(Long idcustomthreshold, Patient patient, StandardThreshold standardThreshold, Double valuefrom, Double valueto, Boolean mustgeneratealert, String commaenumvalues, Date created, Date updated, boolean active, String updateuser) {
       this.idcustomthreshold = idcustomthreshold;
       this.patient = patient;
       this.standardThreshold = standardThreshold;
       this.valuefrom = valuefrom;
       this.valueto = valueto;
       this.mustgeneratealert = mustgeneratealert;
       this.commaenumvalues = commaenumvalues;
       this.created = created;
       this.updated = updated;
       this.active = active;
       this.updateuser = updateuser;
    }
   
     @Id 

    
    @Column(name="idcustomthreshold", unique=true, nullable=false)
    public Long getIdcustomthreshold() {
        return this.idcustomthreshold;
    }
    
    public void setIdcustomthreshold(Long idcustomthreshold) {
        this.idcustomthreshold = idcustomthreshold;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idpatient", nullable=false)
    public Patient getPatient() {
        return this.patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idstandardthreshold", nullable=false)
    public StandardThreshold getStandardThreshold() {
        return this.standardThreshold;
    }
    
    public void setStandardThreshold(StandardThreshold standardThreshold) {
        this.standardThreshold = standardThreshold;
    }

    
    @Column(name="valuefrom", precision=17, scale=17)
    public Double getValuefrom() {
        return this.valuefrom;
    }
    
    public void setValuefrom(Double valuefrom) {
        this.valuefrom = valuefrom;
    }

    
    @Column(name="valueto", precision=17, scale=17)
    public Double getValueto() {
        return this.valueto;
    }
    
    public void setValueto(Double valueto) {
        this.valueto = valueto;
    }

    
    @Column(name="mustgeneratealert")
    public Boolean getMustgeneratealert() {
        return this.mustgeneratealert;
    }
    
    public void setMustgeneratealert(Boolean mustgeneratealert) {
        this.mustgeneratealert = mustgeneratealert;
    }

    
    @Column(name="commaenumvalues")
    public String getCommaenumvalues() {
        return this.commaenumvalues;
    }
    
    public void setCommaenumvalues(String commaenumvalues) {
        this.commaenumvalues = commaenumvalues;
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




}


