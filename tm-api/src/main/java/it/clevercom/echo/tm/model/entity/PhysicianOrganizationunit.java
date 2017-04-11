package it.clevercom.echo.tm.model.entity;
// Generated 11-apr-2017 13.43.21 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PhysicianOrganizationunit generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="tm_physician_organizationunit"
)
public class PhysicianOrganizationunit  implements java.io.Serializable {


     private PhysicianOrganizationunitId id;
     private OrganizationUnit organizationUnit;
     private Physician physician;
     private Date created;
     private Date updated;
     private boolean active;
     private String updateuser;

    public PhysicianOrganizationunit() {
    }

    public PhysicianOrganizationunit(PhysicianOrganizationunitId id, OrganizationUnit organizationUnit, Physician physician, Date created, Date updated, boolean active, String updateuser) {
       this.id = id;
       this.organizationUnit = organizationUnit;
       this.physician = physician;
       this.created = created;
       this.updated = updated;
       this.active = active;
       this.updateuser = updateuser;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="idphysician", column=@Column(name="idphysician", nullable=false) ), 
        @AttributeOverride(name="idorganizationunit", column=@Column(name="idorganizationunit", nullable=false) ) } )
    public PhysicianOrganizationunitId getId() {
        return this.id;
    }
    
    public void setId(PhysicianOrganizationunitId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idorganizationunit", nullable=false, insertable=false, updatable=false)
    public OrganizationUnit getOrganizationUnit() {
        return this.organizationUnit;
    }
    
    public void setOrganizationUnit(OrganizationUnit organizationUnit) {
        this.organizationUnit = organizationUnit;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idphysician", nullable=false, insertable=false, updatable=false)
    public Physician getPhysician() {
        return this.physician;
    }
    
    public void setPhysician(Physician physician) {
        this.physician = physician;
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


