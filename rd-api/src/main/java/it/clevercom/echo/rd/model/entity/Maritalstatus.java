package it.clevercom.echo.rd.model.entity;
// Generated 7-mar-2017 16.29.46 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Maritalstatus generated by hbm2java
 */
@Entity
@Table(name="rd_maritalstatus"
)
public class Maritalstatus  implements java.io.Serializable {


     private String code;
     private String description;
     private Date created;
     private Date updated;
     private String userupdate;
     private boolean active;
     private Set<Patient> patients = new HashSet<Patient>(0);

    public Maritalstatus() {
    }

	
    public Maritalstatus(String code, String description, Date created, Date updated, String userupdate, boolean active) {
        this.code = code;
        this.description = description;
        this.created = created;
        this.updated = updated;
        this.userupdate = userupdate;
        this.active = active;
    }
    public Maritalstatus(String code, String description, Date created, Date updated, String userupdate, boolean active, Set<Patient> patients) {
       this.code = code;
       this.description = description;
       this.created = created;
       this.updated = updated;
       this.userupdate = userupdate;
       this.active = active;
       this.patients = patients;
    }
   
     @Id 

    
    @Column(name="code", unique=true, nullable=false, length=5)
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

    
    @Column(name="userupdate", nullable=false, length=100)
    public String getUserupdate() {
        return this.userupdate;
    }
    
    public void setUserupdate(String userupdate) {
        this.userupdate = userupdate;
    }

    
    @Column(name="active", nullable=false)
    public boolean isActive() {
        return this.active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="maritalstatus")
    public Set<Patient> getPatients() {
        return this.patients;
    }
    
    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }




}


