package it.clevercom.echo.rd.model.entity;
// Generated 5-apr-2017 11.35.01 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * PatientCodingActor generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_patient_coding_actor"
    , uniqueConstraints = @UniqueConstraint(columnNames={"idpatient", "idcodingactor"}) 
)
public class PatientCodingActor  implements java.io.Serializable {


     private Long idpatientcodingactor;
     private CodingActor codingActor;
     private Patient patient;
     private String externalcode;
     private Date created;
     private Date updated;
     private String userupdate;
     private Boolean active;

    public PatientCodingActor() {
    }

	
    public PatientCodingActor(CodingActor codingActor, Patient patient, Date created, Date updated, String userupdate, Boolean active) {
        this.codingActor = codingActor;
        this.patient = patient;
        this.created = created;
        this.updated = updated;
        this.userupdate = userupdate;
        this.active = active;
    }
    public PatientCodingActor(CodingActor codingActor, Patient patient, String externalcode, Date created, Date updated, String userupdate, Boolean active) {
       this.codingActor = codingActor;
       this.patient = patient;
       this.externalcode = externalcode;
       this.created = created;
       this.updated = updated;
       this.userupdate = userupdate;
       this.active = active;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="patient_coding_actor_idpatientcodingactor_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idpatientcodingactor", unique=true, nullable=false)
    public Long getIdpatientcodingactor() {
        return this.idpatientcodingactor;
    }
    
    public void setIdpatientcodingactor(Long idpatientcodingactor) {
        this.idpatientcodingactor = idpatientcodingactor;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idcodingactor", nullable=false)
    public CodingActor getCodingActor() {
        return this.codingActor;
    }
    
    public void setCodingActor(CodingActor codingActor) {
        this.codingActor = codingActor;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idpatient", nullable=false)
    public Patient getPatient() {
        return this.patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    
    @Column(name="externalcode", length=100)
    public String getExternalcode() {
        return this.externalcode;
    }
    
    public void setExternalcode(String externalcode) {
        this.externalcode = externalcode;
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

    
    @Column(name="userupdate", nullable=false, length=100)
    public String getUserupdate() {
        return this.userupdate;
    }
    
    public void setUserupdate(String userupdate) {
        this.userupdate = userupdate;
    }

    
    @Column(name="active", nullable=false)
    public Boolean getActive() {
        return this.active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }




}


