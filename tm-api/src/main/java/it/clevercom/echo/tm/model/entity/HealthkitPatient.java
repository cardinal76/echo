package it.clevercom.echo.tm.model.entity;
// Generated 11-apr-2017 13.43.21 by Hibernate Tools 5.2.2.Final


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

/**
 * HealthkitPatient generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="tm_healthkit_patient"
)
public class HealthkitPatient  implements java.io.Serializable {


     private HealthkitPatientId id;
     private HealthKit healthKit;
     private Patient patient;

    public HealthkitPatient() {
    }

    public HealthkitPatient(HealthkitPatientId id, HealthKit healthKit, Patient patient) {
       this.id = id;
       this.healthKit = healthKit;
       this.patient = patient;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="idhealthkit", column=@Column(name="idhealthkit", nullable=false) ), 
        @AttributeOverride(name="idpatient", column=@Column(name="idpatient", nullable=false) ), 
        @AttributeOverride(name="assignmentdate", column=@Column(name="assignmentdate", length=29) ), 
        @AttributeOverride(name="restitutiondate", column=@Column(name="restitutiondate", length=29) ), 
        @AttributeOverride(name="created", column=@Column(name="created", nullable=false, length=29) ), 
        @AttributeOverride(name="updated", column=@Column(name="updated", nullable=false, length=29) ), 
        @AttributeOverride(name="active", column=@Column(name="active", nullable=false) ), 
        @AttributeOverride(name="updateuser", column=@Column(name="updateuser", nullable=false, length=100) ) } )
    public HealthkitPatientId getId() {
        return this.id;
    }
    
    public void setId(HealthkitPatientId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idhealthkit", nullable=false, insertable=false, updatable=false)
    public HealthKit getHealthKit() {
        return this.healthKit;
    }
    
    public void setHealthKit(HealthKit healthKit) {
        this.healthKit = healthKit;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idpatient", nullable=false, insertable=false, updatable=false)
    public Patient getPatient() {
        return this.patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }




}


