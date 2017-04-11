package it.clevercom.echo.rd.model.entity;
// Generated 11-apr-2017 13.42.44 by Hibernate Tools 5.2.2.Final


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
 * WorkTask generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_work_task"
)
public class WorkTask  implements java.io.Serializable {


     private Long idworktask;
     private Modality modality;
     private Service service;
     private User user;
     private WorkPriority workPriority;
     private WorkSession workSession;
     private WorkStatus workStatus;
     private Long accessionnumber;
     private Date scheduleddate;
     private Date executiondate;
     private String studyuuid;
     private String studyid;
     private Date updated;
     private String userupdate;
     private Boolean active;
     private Date created;
     private Set<WorkReport> workReports = new HashSet<WorkReport>(0);
     private Set<WorkTaskLog> workTaskLogs = new HashSet<WorkTaskLog>(0);

    public WorkTask() {
    }

	
    public WorkTask(Modality modality, Service service, User user, WorkPriority workPriority, WorkSession workSession, WorkStatus workStatus, Long accessionnumber, Date scheduleddate, String studyuuid, Date updated, String userupdate, Boolean active, Date created) {
        this.modality = modality;
        this.service = service;
        this.user = user;
        this.workPriority = workPriority;
        this.workSession = workSession;
        this.workStatus = workStatus;
        this.accessionnumber = accessionnumber;
        this.scheduleddate = scheduleddate;
        this.studyuuid = studyuuid;
        this.updated = updated;
        this.userupdate = userupdate;
        this.active = active;
        this.created = created;
    }
    public WorkTask(Modality modality, Service service, User user, WorkPriority workPriority, WorkSession workSession, WorkStatus workStatus, Long accessionnumber, Date scheduleddate, Date executiondate, String studyuuid, String studyid, Date updated, String userupdate, Boolean active, Date created, Set<WorkReport> workReports, Set<WorkTaskLog> workTaskLogs) {
       this.modality = modality;
       this.service = service;
       this.user = user;
       this.workPriority = workPriority;
       this.workSession = workSession;
       this.workStatus = workStatus;
       this.accessionnumber = accessionnumber;
       this.scheduleddate = scheduleddate;
       this.executiondate = executiondate;
       this.studyuuid = studyuuid;
       this.studyid = studyid;
       this.updated = updated;
       this.userupdate = userupdate;
       this.active = active;
       this.created = created;
       this.workReports = workReports;
       this.workTaskLogs = workTaskLogs;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="worktask_idworktask_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idworktask", unique=true, nullable=false)
    public Long getIdworktask() {
        return this.idworktask;
    }
    
    public void setIdworktask(Long idworktask) {
        this.idworktask = idworktask;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idmodality", nullable=false)
    public Modality getModality() {
        return this.modality;
    }
    
    public void setModality(Modality modality) {
        this.modality = modality;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idservice", nullable=false)
    public Service getService() {
        return this.service;
    }
    
    public void setService(Service service) {
        this.service = service;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="username", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
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
    @JoinColumn(name="idworksession", nullable=false)
    public WorkSession getWorkSession() {
        return this.workSession;
    }
    
    public void setWorkSession(WorkSession workSession) {
        this.workSession = workSession;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idworkstatus", nullable=false)
    public WorkStatus getWorkStatus() {
        return this.workStatus;
    }
    
    public void setWorkStatus(WorkStatus workStatus) {
        this.workStatus = workStatus;
    }

    
    @Column(name="accessionnumber", nullable=false)
    public Long getAccessionnumber() {
        return this.accessionnumber;
    }
    
    public void setAccessionnumber(Long accessionnumber) {
        this.accessionnumber = accessionnumber;
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
    @Column(name="executiondate", length=29)
    public Date getExecutiondate() {
        return this.executiondate;
    }
    
    public void setExecutiondate(Date executiondate) {
        this.executiondate = executiondate;
    }

    
    @Column(name="studyuuid", nullable=false, length=100)
    public String getStudyuuid() {
        return this.studyuuid;
    }
    
    public void setStudyuuid(String studyuuid) {
        this.studyuuid = studyuuid;
    }

    
    @Column(name="studyid", length=100)
    public String getStudyid() {
        return this.studyid;
    }
    
    public void setStudyid(String studyid) {
        this.studyid = studyid;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created", nullable=false, length=29)
    public Date getCreated() {
        return this.created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="workTask")
    public Set<WorkReport> getWorkReports() {
        return this.workReports;
    }
    
    public void setWorkReports(Set<WorkReport> workReports) {
        this.workReports = workReports;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="workTask")
    public Set<WorkTaskLog> getWorkTaskLogs() {
        return this.workTaskLogs;
    }
    
    public void setWorkTaskLogs(Set<WorkTaskLog> workTaskLogs) {
        this.workTaskLogs = workTaskLogs;
    }




}


