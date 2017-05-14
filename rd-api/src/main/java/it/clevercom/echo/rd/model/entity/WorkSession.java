package it.clevercom.echo.rd.model.entity;
// Generated 15-mag-2017 0.54.47 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
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
import it.clevercom.echo.common.jpa.entity.AbstractJpaEchoEntity;
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
public class WorkSession  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private Long idworksession;
     private Patient patient;
     private WorkPriority workPriority;
     private WorkStatus workStatus;
     private Date scheduleddate;
     private Date reporteddate;
     private Set<WorkReport> workReports = new HashSet<WorkReport>(0);
     private Set<WorkTask> workTasks = new HashSet<WorkTask>(0);
     private Set<Order> orders = new HashSet<Order>(0);

    public WorkSession() {
    }

	
    public WorkSession(Patient patient, WorkPriority workPriority, WorkStatus workStatus, Date scheduleddate) {
        this.patient = patient;
        this.workPriority = workPriority;
        this.workStatus = workStatus;
        this.scheduleddate = scheduleddate;
    }
    public WorkSession(Patient patient, WorkPriority workPriority, WorkStatus workStatus, Date scheduleddate, Date reporteddate, Set<WorkReport> workReports, Set<WorkTask> workTasks, Set<Order> orders) {
       this.patient = patient;
       this.workPriority = workPriority;
       this.workStatus = workStatus;
       this.scheduleddate = scheduleddate;
       this.reporteddate = reporteddate;
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
    @JoinColumn(name="idworkpriority", nullable=false)
    public WorkPriority getWorkPriority() {
        return this.workPriority;
    }
    
    public void setWorkPriority(WorkPriority workPriority) {
        this.workPriority = workPriority;
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
    @Column(name="scheduleddate", nullable=false, length=29)
    public Date getScheduleddate() {
        return this.scheduleddate;
    }
    
    public void setScheduleddate(Date scheduleddate) {
        this.scheduleddate = scheduleddate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="reporteddate", length=29)
    public Date getReporteddate() {
        return this.reporteddate;
    }
    
    public void setReporteddate(Date reporteddate) {
        this.reporteddate = reporteddate;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="workSession")
    public Set<WorkReport> getWorkReports() {
        return this.workReports;
    }
    
    public void setWorkReports(Set<WorkReport> workReports) {
        this.workReports = workReports;
    }

@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="workSession")
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


