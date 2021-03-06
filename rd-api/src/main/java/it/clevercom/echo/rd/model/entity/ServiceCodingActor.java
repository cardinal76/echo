package it.clevercom.echo.rd.model.entity;
// Generated 24-mag-2017 22.43.53 by Hibernate Tools 5.2.2.Final


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
public class ServiceCodingActor  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private Long idservicecodingactor;
     private CodingActor codingActor;
     private Service service;
     private String externalcode;

    public ServiceCodingActor() {
    }

    public ServiceCodingActor(CodingActor codingActor, Service service, String externalcode) {
       this.codingActor = codingActor;
       this.service = service;
       this.externalcode = externalcode;
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




}


