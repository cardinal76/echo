package it.clevercom.echo.tm.model.entity;
// Generated 11-apr-2017 13.43.21 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * LanguageType generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="tm_language_type"
)
public class LanguageType  implements java.io.Serializable {


     private Long idlanguagetype;
     private String code;
     private String description;
     private Date created;
     private Date updated;
     private boolean active;
     private String updateuser;

    public LanguageType() {
    }

    public LanguageType(Long idlanguagetype, String code, String description, Date created, Date updated, boolean active, String updateuser) {
       this.idlanguagetype = idlanguagetype;
       this.code = code;
       this.description = description;
       this.created = created;
       this.updated = updated;
       this.active = active;
       this.updateuser = updateuser;
    }
   
     @Id 

    
    @Column(name="idlanguagetype", unique=true, nullable=false)
    public Long getIdlanguagetype() {
        return this.idlanguagetype;
    }
    
    public void setIdlanguagetype(Long idlanguagetype) {
        this.idlanguagetype = idlanguagetype;
    }

    
    @Column(name="code", nullable=false, length=5)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
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

    
    @Column(name="active", nullable=false)
    public boolean isActive() {
        return this.active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }

    
    @Column(name="updateuser", nullable=false, length=100)
    public String getUpdateuser() {
        return this.updateuser;
    }
    
    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }




}


