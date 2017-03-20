package it.clevercom.echo.rd.model.entity;
// Generated 20-mar-2017 16.22.52 by Hibernate Tools 5.2.2.Final


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
 * Icd9Patology generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_icd9_patology"
)
public class Icd9Patology  implements java.io.Serializable {


     private Long idicd9patology;
     private Icd9PatologyGroup icd9PatologyGroup;
     private String code;
     private String description;
     private Date created;
     private Date updated;
     private String userupdate;
     private Boolean active;

    public Icd9Patology() {
    }

	
    public Icd9Patology(String code, String description, Date created, Date updated, String userupdate, Boolean active) {
        this.code = code;
        this.description = description;
        this.created = created;
        this.updated = updated;
        this.userupdate = userupdate;
        this.active = active;
    }
    public Icd9Patology(Icd9PatologyGroup icd9PatologyGroup, String code, String description, Date created, Date updated, String userupdate, Boolean active) {
       this.icd9PatologyGroup = icd9PatologyGroup;
       this.code = code;
       this.description = description;
       this.created = created;
       this.updated = updated;
       this.userupdate = userupdate;
       this.active = active;
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


