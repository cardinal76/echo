package it.clevercom.echo.tm.model.entity;
// Generated 11-apr-2017 13.43.21 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * DetectiontypeMeasurementtype generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="tm_detectiontype_measurementtype"
    , uniqueConstraints = @UniqueConstraint(columnNames="idmeasurementtype") 
)
public class DetectiontypeMeasurementtype  implements java.io.Serializable {


     private DetectiontypeMeasurementtypeId id;
     private DetectionType detectionType;
     private MeasurementType measurementType;
     private Date created;
     private Date updated;
     private boolean active;
     private String updateuser;

    public DetectiontypeMeasurementtype() {
    }

    public DetectiontypeMeasurementtype(DetectiontypeMeasurementtypeId id, DetectionType detectionType, MeasurementType measurementType, Date created, Date updated, boolean active, String updateuser) {
       this.id = id;
       this.detectionType = detectionType;
       this.measurementType = measurementType;
       this.created = created;
       this.updated = updated;
       this.active = active;
       this.updateuser = updateuser;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="iddetectiontype", column=@Column(name="iddetectiontype", nullable=false) ), 
        @AttributeOverride(name="idmeasurementtype", column=@Column(name="idmeasurementtype", unique=true, nullable=false) ) } )
    public DetectiontypeMeasurementtypeId getId() {
        return this.id;
    }
    
    public void setId(DetectiontypeMeasurementtypeId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="iddetectiontype", nullable=false, insertable=false, updatable=false)
    public DetectionType getDetectionType() {
        return this.detectionType;
    }
    
    public void setDetectionType(DetectionType detectionType) {
        this.detectionType = detectionType;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idmeasurementtype", unique=true, nullable=false, insertable=false, updatable=false)
    public MeasurementType getMeasurementType() {
        return this.measurementType;
    }
    
    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
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


