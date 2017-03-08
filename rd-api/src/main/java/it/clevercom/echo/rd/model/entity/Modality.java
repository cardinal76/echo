package it.clevercom.echo.rd.model.entity;
// Generated 8-mar-2017 17.12.23 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Modality generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_modality"
)
public class Modality  implements java.io.Serializable {


     private Long idmodality;
     private OrganizationUnit organizationUnit;
     private String name;
     private String description;
     private Date created;
     private Date updated;
     private String userupdate;
     private Boolean active;
     private Set<ModalityService> modalityServices = new HashSet<ModalityService>(0);
     private Set<WorkTask> workTasks = new HashSet<WorkTask>(0);

    public Modality() {
    }

	
    public Modality(OrganizationUnit organizationUnit, String name, Date created, Date updated, String userupdate, Boolean active) {
        this.organizationUnit = organizationUnit;
        this.name = name;
        this.created = created;
        this.updated = updated;
        this.userupdate = userupdate;
        this.active = active;
    }
    public Modality(OrganizationUnit organizationUnit, String name, String description, Date created, Date updated, String userupdate, Boolean active, Set<ModalityService> modalityServices, Set<WorkTask> workTasks) {
       this.organizationUnit = organizationUnit;
       this.name = name;
       this.description = description;
       this.created = created;
       this.updated = updated;
       this.userupdate = userupdate;
       this.active = active;
       this.modalityServices = modalityServices;
       this.workTasks = workTasks;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="modality_idmodality_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idmodality", unique=true, nullable=false)
    public Long getIdmodality() {
        return this.idmodality;
    }
    
    public void setIdmodality(Long idmodality) {
        this.idmodality = idmodality;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idorganizationunit", nullable=false)
    public OrganizationUnit getOrganizationUnit() {
        return this.organizationUnit;
    }
    
    public void setOrganizationUnit(OrganizationUnit organizationUnit) {
        this.organizationUnit = organizationUnit;
    }

    
    @Column(name="name", nullable=false, length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="description")
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="modality")
    public Set<ModalityService> getModalityServices() {
        return this.modalityServices;
    }
    
    public void setModalityServices(Set<ModalityService> modalityServices) {
        this.modalityServices = modalityServices;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="modality")
    public Set<WorkTask> getWorkTasks() {
        return this.workTasks;
    }
    
    public void setWorkTasks(Set<WorkTask> workTasks) {
        this.workTasks = workTasks;
    }




}


