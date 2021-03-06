package it.clevercom.echo.tm.model.entity;
// Generated 11-apr-2017 13.43.21 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Municipality generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="tm_municipality"
)
public class Municipality  implements java.io.Serializable {


     private Long idmunicipality;
     private Province province;
     private String municipalityname;
     private String municipalitystdcode;
     private Date created;
     private Date updated;
     private boolean active;
     private String updateuser;
     private Set<CareGiver> careGivers = new HashSet<CareGiver>(0);
     private Set<Patient> patients = new HashSet<Patient>(0);

    public Municipality() {
    }

	
    public Municipality(Long idmunicipality, Province province, Date created, Date updated, boolean active, String updateuser) {
        this.idmunicipality = idmunicipality;
        this.province = province;
        this.created = created;
        this.updated = updated;
        this.active = active;
        this.updateuser = updateuser;
    }
    public Municipality(Long idmunicipality, Province province, String municipalityname, String municipalitystdcode, Date created, Date updated, boolean active, String updateuser, Set<CareGiver> careGivers, Set<Patient> patients) {
       this.idmunicipality = idmunicipality;
       this.province = province;
       this.municipalityname = municipalityname;
       this.municipalitystdcode = municipalitystdcode;
       this.created = created;
       this.updated = updated;
       this.active = active;
       this.updateuser = updateuser;
       this.careGivers = careGivers;
       this.patients = patients;
    }
   
     @Id 

    
    @Column(name="idmunicipality", unique=true, nullable=false)
    public Long getIdmunicipality() {
        return this.idmunicipality;
    }
    
    public void setIdmunicipality(Long idmunicipality) {
        this.idmunicipality = idmunicipality;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idprovince", nullable=false)
    public Province getProvince() {
        return this.province;
    }
    
    public void setProvince(Province province) {
        this.province = province;
    }

    
    @Column(name="municipalityname", length=100)
    public String getMunicipalityname() {
        return this.municipalityname;
    }
    
    public void setMunicipalityname(String municipalityname) {
        this.municipalityname = municipalityname;
    }

    
    @Column(name="municipalitystdcode", length=10)
    public String getMunicipalitystdcode() {
        return this.municipalitystdcode;
    }
    
    public void setMunicipalitystdcode(String municipalitystdcode) {
        this.municipalitystdcode = municipalitystdcode;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="municipality")
    public Set<CareGiver> getCareGivers() {
        return this.careGivers;
    }
    
    public void setCareGivers(Set<CareGiver> careGivers) {
        this.careGivers = careGivers;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="municipality")
    public Set<Patient> getPatients() {
        return this.patients;
    }
    
    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }




}


