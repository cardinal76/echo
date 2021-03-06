package it.clevercom.echo.tm.model.entity;
// Generated 11-apr-2017 13.43.21 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * MeasurementType generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="tm_measurement_type"
)
public class MeasurementType  implements java.io.Serializable {


     private Long idmeasurementtype;
     private String name;
     private String description;
     private Boolean nan;
     private String commaenumvalues;
     private Long idmeasurementunit;
     private Date created;
     private Date updated;
     private boolean active;
     private String updateuser;
     private Set<StandardThreshold> standardThresholds = new HashSet<StandardThreshold>(0);
     private Set<DetectionResult> detectionResults = new HashSet<DetectionResult>(0);
     private Set<DetectiontypeMeasurementtype> detectiontypeMeasurementtypes = new HashSet<DetectiontypeMeasurementtype>(0);

    public MeasurementType() {
    }

	
    public MeasurementType(Long idmeasurementtype, String name, Date created, Date updated, boolean active, String updateuser) {
        this.idmeasurementtype = idmeasurementtype;
        this.name = name;
        this.created = created;
        this.updated = updated;
        this.active = active;
        this.updateuser = updateuser;
    }
    public MeasurementType(Long idmeasurementtype, String name, String description, Boolean nan, String commaenumvalues, Long idmeasurementunit, Date created, Date updated, boolean active, String updateuser, Set<StandardThreshold> standardThresholds, Set<DetectionResult> detectionResults, Set<DetectiontypeMeasurementtype> detectiontypeMeasurementtypes) {
       this.idmeasurementtype = idmeasurementtype;
       this.name = name;
       this.description = description;
       this.nan = nan;
       this.commaenumvalues = commaenumvalues;
       this.idmeasurementunit = idmeasurementunit;
       this.created = created;
       this.updated = updated;
       this.active = active;
       this.updateuser = updateuser;
       this.standardThresholds = standardThresholds;
       this.detectionResults = detectionResults;
       this.detectiontypeMeasurementtypes = detectiontypeMeasurementtypes;
    }
   
     @Id 

    
    @Column(name="idmeasurementtype", unique=true, nullable=false)
    public Long getIdmeasurementtype() {
        return this.idmeasurementtype;
    }
    
    public void setIdmeasurementtype(Long idmeasurementtype) {
        this.idmeasurementtype = idmeasurementtype;
    }

    
    @Column(name="name", nullable=false, length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="nan")
    public Boolean getNan() {
        return this.nan;
    }
    
    public void setNan(Boolean nan) {
        this.nan = nan;
    }

    
    @Column(name="commaenumvalues")
    public String getCommaenumvalues() {
        return this.commaenumvalues;
    }
    
    public void setCommaenumvalues(String commaenumvalues) {
        this.commaenumvalues = commaenumvalues;
    }

    
    @Column(name="idmeasurementunit")
    public Long getIdmeasurementunit() {
        return this.idmeasurementunit;
    }
    
    public void setIdmeasurementunit(Long idmeasurementunit) {
        this.idmeasurementunit = idmeasurementunit;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="measurementType")
    public Set<StandardThreshold> getStandardThresholds() {
        return this.standardThresholds;
    }
    
    public void setStandardThresholds(Set<StandardThreshold> standardThresholds) {
        this.standardThresholds = standardThresholds;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="measurementType")
    public Set<DetectionResult> getDetectionResults() {
        return this.detectionResults;
    }
    
    public void setDetectionResults(Set<DetectionResult> detectionResults) {
        this.detectionResults = detectionResults;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="measurementType")
    public Set<DetectiontypeMeasurementtype> getDetectiontypeMeasurementtypes() {
        return this.detectiontypeMeasurementtypes;
    }
    
    public void setDetectiontypeMeasurementtypes(Set<DetectiontypeMeasurementtype> detectiontypeMeasurementtypes) {
        this.detectiontypeMeasurementtypes = detectiontypeMeasurementtypes;
    }




}


