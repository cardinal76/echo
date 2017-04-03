package it.clevercom.echo.rd.model.entity;
// Generated 3-apr-2017 11.29.02 by Hibernate Tools 5.2.2.Final


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
 * ModalityService generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_modality_service"
    , uniqueConstraints = @UniqueConstraint(columnNames={"idmodality", "idservice"}) 
)
public class ModalityService  implements java.io.Serializable {


     private Long idmodalityservice;
     private Modality modality;
     private Service service;
     private Date created;
     private Date updated;
     private String userupdate;
     private Boolean active;

    public ModalityService() {
    }

    public ModalityService(Modality modality, Service service, Date created, Date updated, String userupdate, Boolean active) {
       this.modality = modality;
       this.service = service;
       this.created = created;
       this.updated = updated;
       this.userupdate = userupdate;
       this.active = active;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="modalityservice_idmodalityservice_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idmodalityservice", unique=true, nullable=false)
    public Long getIdmodalityservice() {
        return this.idmodalityservice;
    }
    
    public void setIdmodalityservice(Long idmodalityservice) {
        this.idmodalityservice = idmodalityservice;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idmodality", nullable=false)
    public Modality getModality() {
        return this.modality;
    }
    
    public void setModality(Modality modality) {
        this.modality = modality;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idservice", nullable=false)
    public Service getService() {
        return this.service;
    }
    
    public void setService(Service service) {
        this.service = service;
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


