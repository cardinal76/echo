package it.clevercom.echo.rd.model.entity;
// Generated 24-mag-2017 22.43.53 by Hibernate Tools 5.2.2.Final


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
 * VModalitytypeAllocation generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_v_modalitytype_allocation"
)
public class VModalitytypeAllocation  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private String id;
     private Long idmodalitytype;
     private String description;
     private Date scheduledate;
     private Long serviceallocation;
     private Long patientallocation;
     private Long serviceexcess;
     private Long patientexcess;

    public VModalitytypeAllocation() {
    }

	
    public VModalitytypeAllocation(String id) {
        this.id = id;
    }
    public VModalitytypeAllocation(String id, Long idmodalitytype, String description, Date scheduledate, Long serviceallocation, Long patientallocation, Long serviceexcess, Long patientexcess) {
       this.id = id;
       this.idmodalitytype = idmodalitytype;
       this.description = description;
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

    
    @Column(name="idmodalitytype")
    public Long getIdmodalitytype() {
        return this.idmodalitytype;
    }
    
    public void setIdmodalitytype(Long idmodalitytype) {
        this.idmodalitytype = idmodalitytype;
    }

    
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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
    public Long getServiceallocation() {
        return this.serviceallocation;
    }
    
    public void setServiceallocation(Long serviceallocation) {
        this.serviceallocation = serviceallocation;
    }

    
    @Column(name="patientallocation")
    public Long getPatientallocation() {
        return this.patientallocation;
    }
    
    public void setPatientallocation(Long patientallocation) {
        this.patientallocation = patientallocation;
    }

    
    @Column(name="serviceexcess")
    public Long getServiceexcess() {
        return this.serviceexcess;
    }
    
    public void setServiceexcess(Long serviceexcess) {
        this.serviceexcess = serviceexcess;
    }

    
    @Column(name="patientexcess")
    public Long getPatientexcess() {
        return this.patientexcess;
    }
    
    public void setPatientexcess(Long patientexcess) {
        this.patientexcess = patientexcess;
    }




}


