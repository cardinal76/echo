package it.clevercom.echo.rd.model.entity;
// Generated 18-apr-2017 16.29.28 by Hibernate Tools 5.2.2.Final


import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Icd9PatologyGroup generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_icd9_patology_group"
)
public class Icd9PatologyGroup  implements java.io.Serializable {


     private Long idicd9patologygroup;
     private String codeicd9group;
     private String description;
     private Date created;
     private Date updated;
     private String userupdate;
     private Boolean active;
     private Set<Icd9Patology> icd9Patologies = new HashSet<Icd9Patology>(0);

    public Icd9PatologyGroup() {
    }

	
    public Icd9PatologyGroup(String codeicd9group, String description, Date created, Date updated, String userupdate, Boolean active) {
        this.codeicd9group = codeicd9group;
        this.description = description;
        this.created = created;
        this.updated = updated;
        this.userupdate = userupdate;
        this.active = active;
    }
    public Icd9PatologyGroup(String codeicd9group, String description, Date created, Date updated, String userupdate, Boolean active, Set<Icd9Patology> icd9Patologies) {
       this.codeicd9group = codeicd9group;
       this.description = description;
       this.created = created;
       this.updated = updated;
       this.userupdate = userupdate;
       this.active = active;
       this.icd9Patologies = icd9Patologies;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="icd9_patology_group_idicd9patologygroup_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idicd9patologygroup", unique=true, nullable=false)
    public Long getIdicd9patologygroup() {
        return this.idicd9patologygroup;
    }
    
    public void setIdicd9patologygroup(Long idicd9patologygroup) {
        this.idicd9patologygroup = idicd9patologygroup;
    }

    
    @Column(name="codeicd9group", nullable=false, length=5)
    public String getCodeicd9group() {
        return this.codeicd9group;
    }
    
    public void setCodeicd9group(String codeicd9group) {
        this.codeicd9group = codeicd9group;
    }

    
    @Column(name="description", nullable=false, length=100)
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="icd9PatologyGroup")
    public Set<Icd9Patology> getIcd9Patologies() {
        return this.icd9Patologies;
    }
    
    public void setIcd9Patologies(Set<Icd9Patology> icd9Patologies) {
        this.icd9Patologies = icd9Patologies;
    }




}


