package it.clevercom.echo.rd.model.entity;
// Generated 27-apr-2017 10.34.01 by Hibernate Tools 5.2.2.Final


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
 * Icd9Patology generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_icd9_patology"
)
public class Icd9Patology  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private Long idicd9patology;
     private Icd9PatologyGroup icd9PatologyGroup;
     private String code;
     private String description;

    public Icd9Patology() {
    }

	
    public Icd9Patology(String code, String description) {
        this.code = code;
        this.description = description;
    }
    public Icd9Patology(Icd9PatologyGroup icd9PatologyGroup, String code, String description) {
       this.icd9PatologyGroup = icd9PatologyGroup;
       this.code = code;
       this.description = description;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="icd9_patology_idicd9patology_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idicd9patology", unique=true, nullable=false)
    public Long getIdicd9patology() {
        return this.idicd9patology;
    }
    
    public void setIdicd9patology(Long idicd9patology) {
        this.idicd9patology = idicd9patology;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idicd9patologygroup")
    public Icd9PatologyGroup getIcd9PatologyGroup() {
        return this.icd9PatologyGroup;
    }
    
    public void setIcd9PatologyGroup(Icd9PatologyGroup icd9PatologyGroup) {
        this.icd9PatologyGroup = icd9PatologyGroup;
    }

    
    @Column(name="code", nullable=false, length=16)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="description", nullable=false)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}


