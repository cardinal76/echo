package it.clevercom.echo.rd.model.entity;
// Generated 21-mag-2017 23.16.18 by Hibernate Tools 5.2.2.Final


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
import it.clevercom.echo.common.jpa.entity.AbstractJpaEchoEntity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * ModalityDailyAllocation generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_modality_daily_allocation"
    , uniqueConstraints = @UniqueConstraint(columnNames={"idmodality", "day"}) 
)
public class ModalityDailyAllocation  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private Long idmodalitydailyallocation;
     private Modality modality;
     private Date day;
     private Integer serviceallocation;
     private Integer serviceexcess;
     private Integer patientallocation;
     private Integer patientexcess;

    public ModalityDailyAllocation() {
    }

    public ModalityDailyAllocation(Modality modality, Date day, Integer serviceallocation, Integer serviceexcess, Integer patientallocation, Integer patientexcess) {
       this.modality = modality;
       this.day = day;
       this.serviceallocation = serviceallocation;
       this.serviceexcess = serviceexcess;
       this.patientallocation = patientallocation;
       this.patientexcess = patientexcess;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="modalitydailyallocation_idmodalitydailyallocation_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idmodalitydailyallocation", unique=true, nullable=false)
    public Long getIdmodalitydailyallocation() {
        return this.idmodalitydailyallocation;
    }
    
    public void setIdmodalitydailyallocation(Long idmodalitydailyallocation) {
        this.idmodalitydailyallocation = idmodalitydailyallocation;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idmodality")
    public Modality getModality() {
        return this.modality;
    }
    
    public void setModality(Modality modality) {
        this.modality = modality;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="day", length=13)
    public Date getDay() {
        return this.day;
    }
    
    public void setDay(Date day) {
        this.day = day;
    }

    
    @Column(name="serviceallocation")
    public Integer getServiceallocation() {
        return this.serviceallocation;
    }
    
    public void setServiceallocation(Integer serviceallocation) {
        this.serviceallocation = serviceallocation;
    }

    
    @Column(name="serviceexcess")
    public Integer getServiceexcess() {
        return this.serviceexcess;
    }
    
    public void setServiceexcess(Integer serviceexcess) {
        this.serviceexcess = serviceexcess;
    }

    
    @Column(name="patientallocation")
    public Integer getPatientallocation() {
        return this.patientallocation;
    }
    
    public void setPatientallocation(Integer patientallocation) {
        this.patientallocation = patientallocation;
    }

    
    @Column(name="patientexcess")
    public Integer getPatientexcess() {
        return this.patientexcess;
    }
    
    public void setPatientexcess(Integer patientexcess) {
        this.patientexcess = patientexcess;
    }




}


