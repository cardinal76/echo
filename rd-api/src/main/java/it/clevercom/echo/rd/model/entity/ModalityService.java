package it.clevercom.echo.rd.model.entity;
// Generated 9-mag-2017 16.58.12 by Hibernate Tools 5.2.2.Final


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
public class ModalityService  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private Long idmodalityservice;
     private Modality modality;
     private Service service;

    public ModalityService() {
    }

    public ModalityService(Modality modality, Service service) {
       this.modality = modality;
       this.service = service;
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




}


