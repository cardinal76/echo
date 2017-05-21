package it.clevercom.echo.rd.model.entity;
// Generated 21-mag-2017 23.16.18 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import it.clevercom.echo.common.jpa.entity.AbstractJpaEchoEntity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * VModalityAllocation generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_v_modality_allocation"
)
public class VModalityAllocation  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private String id;
     private Long idmodality;
     private String name;
     private Date scheduledate;
     private Integer serviceallocation;
     private Integer patientallocation;
     private Integer serviceexcess;
     private Integer patientexcess;

    public VModalityAllocation() {
    }

	
    public VModalityAllocation(String id) {
        this.id = id;
    }
    public VModalityAllocation(String id, Long idmodality, String name, Date scheduledate, Integer serviceallocation, Integer patientallocation, Integer serviceexcess, Integer patientexcess) {
       this.id = id;
       this.idmodality = idmodality;
       this.name = name;
       this.scheduledate = scheduledate;
       this.serviceallocation = serviceallocation;
       this.patientallocation = patientallocation;
       this.serviceexcess = serviceexcess;
       this.patientexcess = patientexcess;
    }
   
     @Id 

    
    @Column(name="id", nullable=false)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    
    @Column(name="idmodality")
    public Long getIdmodality() {
        return this.idmodality;
    }
    
    public void setIdmodality(Long idmodality) {
        this.idmodality = idmodality;
    }

    
    @Column(name="name", length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="scheduledate", length=13)
    public Date getScheduledate() {
        return this.scheduledate;
    }
    
    public void setScheduledate(Date scheduledate) {
        this.scheduledate = scheduledate;
    }

    
    @Column(name="serviceallocation")
    public Integer getServiceallocation() {
        return this.serviceallocation;
    }
    
    public void setServiceallocation(Integer serviceallocation) {
        this.serviceallocation = serviceallocation;
    }

    
    @Column(name="patientallocation")
    public Integer getPatientallocation() {
        return this.patientallocation;
    }
    
    public void setPatientallocation(Integer patientallocation) {
        this.patientallocation = patientallocation;
    }

    
    @Column(name="serviceexcess")
    public Integer getServiceexcess() {
        return this.serviceexcess;
    }
    
    public void setServiceexcess(Integer serviceexcess) {
        this.serviceexcess = serviceexcess;
    }

    
    @Column(name="patientexcess")
    public Integer getPatientexcess() {
        return this.patientexcess;
    }
    
    public void setPatientexcess(Integer patientexcess) {
        this.patientexcess = patientexcess;
    }




}


