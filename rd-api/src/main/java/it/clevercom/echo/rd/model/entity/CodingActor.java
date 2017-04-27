package it.clevercom.echo.rd.model.entity;
// Generated 27-apr-2017 11.40.51 by Hibernate Tools 5.2.2.Final


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
 * CodingActor generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_coding_actor"
)
public class CodingActor  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private Long idcodingactor;
     private String name;
     private Set<ServiceCodingActor> serviceCodingActors = new HashSet<ServiceCodingActor>(0);
     private Set<PatientCodingActor> patientCodingActors = new HashSet<PatientCodingActor>(0);

    public CodingActor() {
    }

	
    public CodingActor(String name) {
        this.name = name;
    }
    public CodingActor(String name, Set<ServiceCodingActor> serviceCodingActors, Set<PatientCodingActor> patientCodingActors) {
       this.name = name;
       this.serviceCodingActors = serviceCodingActors;
       this.patientCodingActors = patientCodingActors;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="coding_actor_idcodingactor_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idcodingactor", unique=true, nullable=false)
    public Long getIdcodingactor() {
        return this.idcodingactor;
    }
    
    public void setIdcodingactor(Long idcodingactor) {
        this.idcodingactor = idcodingactor;
    }

    
    @Column(name="name", nullable=false, length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="codingActor")
    public Set<ServiceCodingActor> getServiceCodingActors() {
        return this.serviceCodingActors;
    }
    
    public void setServiceCodingActors(Set<ServiceCodingActor> serviceCodingActors) {
        this.serviceCodingActors = serviceCodingActors;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="codingActor")
    public Set<PatientCodingActor> getPatientCodingActors() {
        return this.patientCodingActors;
    }
    
    public void setPatientCodingActors(Set<PatientCodingActor> patientCodingActors) {
        this.patientCodingActors = patientCodingActors;
    }




}


