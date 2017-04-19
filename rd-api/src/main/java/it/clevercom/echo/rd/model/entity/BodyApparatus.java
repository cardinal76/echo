package it.clevercom.echo.rd.model.entity;
// Generated 19-apr-2017 15.19.48 by Hibernate Tools 5.2.2.Final


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
 * BodyApparatus generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_body_apparatus"
)
public class BodyApparatus  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private Long idbodyapparatus;
     private char code;
     private String description;
     private Set<Service> services = new HashSet<Service>(0);

    public BodyApparatus() {
    }

	
    public BodyApparatus(char code, String description) {
        this.code = code;
        this.description = description;
    }
    public BodyApparatus(char code, String description, Set<Service> services) {
       this.code = code;
       this.description = description;
       this.services = services;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="body_apparatus_idbodyapparatus_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idbodyapparatus", unique=true, nullable=false)
    public Long getIdbodyapparatus() {
        return this.idbodyapparatus;
    }
    
    public void setIdbodyapparatus(Long idbodyapparatus) {
        this.idbodyapparatus = idbodyapparatus;
    }

    
    @Column(name="code", nullable=false, length=1)
    public char getCode() {
        return this.code;
    }
    
    public void setCode(char code) {
        this.code = code;
    }

    
    @Column(name="description", nullable=false)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="bodyApparatus")
    public Set<Service> getServices() {
        return this.services;
    }
    
    public void setServices(Set<Service> services) {
        this.services = services;
    }




}


