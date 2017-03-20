package it.clevercom.echo.rd.model.entity;
// Generated 20-mar-2017 16.22.52 by Hibernate Tools 5.2.2.Final


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
 * WorkSession generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_work_session"
)
public class WorkSession  implements java.io.Serializable {


     private Long idworksession;
     private Patient patient;
     private WorkStatus workStatus;
     private Date created;
     private Date updated;
     private String userupdate;
     private Boolean active;
     private Set<WorkReport> workReports = new HashSet<WorkReport>(0);
     private Set<WorkTask> workTasks = new HashSet<WorkTask>(0);
     private Set<Order> orders = new HashSet<Order>(0);

    public WorkSession() {
    }

	
    public WorkSession(Patient patient, WorkStatus workStatus, Date created, Date updated, String userupdate, Boolean active) {
        this.patient = patient;
        this.workStatus = workStatus;
        this.created = created;
        this.updated = updated;
        this.userupdate = userupdate;
        this.active = active;
    }
    public WorkSession(Patient patient, WorkStatus workStatus, Date created, Date updated, String userupdate, Boolean active, Set<WorkReport> workReports, Set<WorkTask> workTasks, Set<Order> orders) {
       this.patient = patient;
       this.workStatus = workStatus;
       this.created = created;
       this.updated = updated;
       this.userupdate = userupdate;
       this.active = active;
       this.workReports = workReports;
       this.workTasks = workTasks;
       this.orders = orders;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="worksession_idworksession_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idworksession", unique=true, nullable=false)
    public Long getIdworksession() {
        return this.idworksession;
    }
    
    public void setIdworksession(Long idworksession) {
        this.idworksession = idworksession;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idpatient", nullable=false)
    public Patient getPatient() {
        return this.patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idworkstatus", nullable=false)
    public WorkStatus getWorkStatus() {
        return this.workStatus;
    }
    
    public void setWorkStatus(WorkStatus workStatus) {
        this.workStatus = workStatus;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="workSession")
    public Set<WorkReport> getWorkReports() {
        return this.workReports;
    }
    
    public void setWorkReports(Set<WorkReport> workReports) {
        this.workReports = workReports;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="workSession")
    public Set<WorkTask> getWorkTasks() {
        return this.workTasks;
    }
    
    public void setWorkTasks(Set<WorkTask> workTasks) {
        this.workTasks = workTasks;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="workSession")
    public Set<Order> getOrders() {
        return this.orders;
    }
    
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }




}


