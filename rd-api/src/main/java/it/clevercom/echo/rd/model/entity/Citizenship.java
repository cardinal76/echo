package it.clevercom.echo.rd.model.entity;
// Generated 2-mag-2017 14.43.59 by Hibernate Tools 5.2.2.Final


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
 * Citizenship generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_citizenship"
)
public class Citizenship  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private Long idcitizenship;
     private String description;
     private Set<Patient> patients = new HashSet<Patient>(0);

    public Citizenship() {
    }

	
    public Citizenship(String description) {
        this.description = description;
    }
    public Citizenship(String description, Set<Patient> patients) {
       this.description = description;
       this.patients = patients;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="citizenship_idcitizenship_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idcitizenship", unique=true, nullable=false)
    public Long getIdcitizenship() {
        return this.idcitizenship;
    }
    
    public void setIdcitizenship(Long idcitizenship) {
        this.idcitizenship = idcitizenship;
    }

    
    @Column(name="description", nullable=false, length=100)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="citizenship")
    public Set<Patient> getPatients() {
        return this.patients;
    }
    
    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }




}


