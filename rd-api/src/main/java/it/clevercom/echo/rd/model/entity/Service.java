package it.clevercom.echo.rd.model.entity;
// Generated 5-apr-2017 11.35.01 by Hibernate Tools 5.2.2.Final


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
 * Service generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_service"
)
public class Service  implements java.io.Serializable {


     private Long idservice;
     private BodyApparatus bodyApparatus;
     private ModalityType modalityType;
     private String description;
     private Date created;
     private Date updated;
     private String userupdate;
     private Boolean active;
     private Long prepcode;
     private Long duration;
     private Boolean schedulable;
     private Set<ServiceCodingActor> serviceCodingActors = new HashSet<ServiceCodingActor>(0);
     private Set<WorkTask> workTasks = new HashSet<WorkTask>(0);
     private Set<ModalityService> modalityServices = new HashSet<ModalityService>(0);
     private Set<OrderService> orderServices = new HashSet<OrderService>(0);

    public Service() {
    }

	
    public Service(Date created, Date updated, String userupdate, Boolean active) {
        this.created = created;
        this.updated = updated;
        this.userupdate = userupdate;
        this.active = active;
    }
    public Service(BodyApparatus bodyApparatus, ModalityType modalityType, String description, Date created, Date updated, String userupdate, Boolean active, Long prepcode, Long duration, Boolean schedulable, Set<ServiceCodingActor> serviceCodingActors, Set<WorkTask> workTasks, Set<ModalityService> modalityServices, Set<OrderService> orderServices) {
       this.bodyApparatus = bodyApparatus;
       this.modalityType = modalityType;
       this.description = description;
       this.created = created;
       this.updated = updated;
       this.userupdate = userupdate;
       this.active = active;
       this.prepcode = prepcode;
       this.duration = duration;
       this.schedulable = schedulable;
       this.serviceCodingActors = serviceCodingActors;
       this.workTasks = workTasks;
       this.modalityServices = modalityServices;
       this.orderServices = orderServices;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="service_idservice_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idservice", unique=true, nullable=false)
    public Long getIdservice() {
        return this.idservice;
    }
    
    public void setIdservice(Long idservice) {
        this.idservice = idservice;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idbodyapparatus")
    public BodyApparatus getBodyApparatus() {
        return this.bodyApparatus;
    }
    
    public void setBodyApparatus(BodyApparatus bodyApparatus) {
        this.bodyApparatus = bodyApparatus;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idmodalitytype")
    public ModalityType getModalityType() {
        return this.modalityType;
    }
    
    public void setModalityType(ModalityType modalityType) {
        this.modalityType = modalityType;
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

    
    @Column(name="prepcode")
    public Long getPrepcode() {
        return this.prepcode;
    }
    
    public void setPrepcode(Long prepcode) {
        this.prepcode = prepcode;
    }

    
    @Column(name="duration")
    public Long getDuration() {
        return this.duration;
    }
    
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    
    @Column(name="schedulable")
    public Boolean getSchedulable() {
        return this.schedulable;
    }
    
    public void setSchedulable(Boolean schedulable) {
        this.schedulable = schedulable;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="service")
    public Set<ServiceCodingActor> getServiceCodingActors() {
        return this.serviceCodingActors;
    }
    
    public void setServiceCodingActors(Set<ServiceCodingActor> serviceCodingActors) {
        this.serviceCodingActors = serviceCodingActors;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="service")
    public Set<WorkTask> getWorkTasks() {
        return this.workTasks;
    }
    
    public void setWorkTasks(Set<WorkTask> workTasks) {
        this.workTasks = workTasks;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="service")
    public Set<ModalityService> getModalityServices() {
        return this.modalityServices;
    }
    
    public void setModalityServices(Set<ModalityService> modalityServices) {
        this.modalityServices = modalityServices;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="service")
    public Set<OrderService> getOrderServices() {
        return this.orderServices;
    }
    
    public void setOrderServices(Set<OrderService> orderServices) {
        this.orderServices = orderServices;
    }




}


