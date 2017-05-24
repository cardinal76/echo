package it.clevercom.echo.rd.model.entity;
// Generated 24-mag-2017 14.48.13 by Hibernate Tools 5.2.2.Final


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import it.clevercom.echo.common.jpa.entity.AbstractJpaEchoEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * ModalityType generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_modality_type"
)
public class ModalityType  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private Long idmodalitytype;
     private String type;
     private Set<Modality> modalities = new HashSet<Modality>(0);
     private Set<Service> services = new HashSet<Service>(0);

    public ModalityType() {
    }

	
    public ModalityType(String type) {
        this.type = type;
    }
    public ModalityType(String type, Set<Modality> modalities, Set<Service> services) {
       this.type = type;
       this.modalities = modalities;
       this.services = services;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="modality_type_idmodalitytype_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idmodalitytype", unique=true, nullable=false)
    public Long getIdmodalitytype() {
        return this.idmodalitytype;
    }
    
    public void setIdmodalitytype(Long idmodalitytype) {
        this.idmodalitytype = idmodalitytype;
    }

    
    @Column(name="type", nullable=false)
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="modalityType")
    public Set<Modality> getModalities() {
        return this.modalities;
    }
    
    public void setModalities(Set<Modality> modalities) {
        this.modalities = modalities;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="modalityType")
    public Set<Service> getServices() {
        return this.services;
    }
    
    public void setServices(Set<Service> services) {
        this.services = services;
    }




}


