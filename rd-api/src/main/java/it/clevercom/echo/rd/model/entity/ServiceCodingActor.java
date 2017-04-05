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
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * ServiceCodingActor generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_service_coding_actor"
)
public class ServiceCodingActor  implements java.io.Serializable {


     private Long idservicecodingactor;
     private CodingActor codingActor;
     private Service service;
     private String externalcode;
     private Date created;
     private Date updated;
     private String userupdate;
     private Boolean active;

    public ServiceCodingActor() {
    }

    public ServiceCodingActor(CodingActor codingActor, Service service, String externalcode, Date created, Date updated, String userupdate, Boolean active) {
       this.codingActor = codingActor;
       this.service = service;
       this.externalcode = externalcode;
       this.created = created;
       this.updated = updated;
       this.userupdate = userupdate;
       this.active = active;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="service_coding_actor_idservicecodingactor_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idservicecodingactor", unique=true, nullable=false)
    public Long getIdservicecodingactor() {
        return this.idservicecodingactor;
    }
    
    public void setIdservicecodingactor(Long idservicecodingactor) {
        this.idservicecodingactor = idservicecodingactor;
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
    @JoinColumn(name="idservice", nullable=false)
    public Service getService() {
        return this.service;
    }
    
    public void setService(Service service) {
        this.service = service;
    }

    
    @Column(name="externalcode", nullable=false, length=100)
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


