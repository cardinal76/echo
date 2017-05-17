package it.clevercom.echo.rd.model.entity;
// Generated 16-mag-2017 14.24.13 by Hibernate Tools 5.2.2.Final


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
import it.clevercom.echo.common.jpa.entity.AbstractJpaEchoEntity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * WorkReport generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_work_report"
)
public class WorkReport  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private Long idworkreport;
     private WorkSession workSession;
     private WorkStatus workStatus;
     private WorkTask workTask;
     private Long accessionnumber;
     private Date creationdate;
     private Date completiondate;
     private String body;
     private Set<WorkReportLog> workReportLogs = new HashSet<WorkReportLog>(0);
     private Set<WorkReportUser> workReportUsers = new HashSet<WorkReportUser>(0);

    public WorkReport() {
    }

	
    public WorkReport(WorkSession workSession, WorkStatus workStatus, WorkTask workTask, Date creationdate, String body) {
        this.workSession = workSession;
        this.workStatus = workStatus;
        this.workTask = workTask;
        this.creationdate = creationdate;
        this.body = body;
    }
    public WorkReport(WorkSession workSession, WorkStatus workStatus, WorkTask workTask, Long accessionnumber, Date creationdate, Date completiondate, String body, Set<WorkReportLog> workReportLogs, Set<WorkReportUser> workReportUsers) {
       this.workSession = workSession;
       this.workStatus = workStatus;
       this.workTask = workTask;
       this.accessionnumber = accessionnumber;
       this.creationdate = creationdate;
       this.completiondate = completiondate;
       this.body = body;
       this.workReportLogs = workReportLogs;
       this.workReportUsers = workReportUsers;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="workreport_idworkreport_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idworkreport", unique=true, nullable=false)
    public Long getIdworkreport() {
        return this.idworkreport;
    }
    
    public void setIdworkreport(Long idworkreport) {
        this.idworkreport = idworkreport;
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idworktask", nullable=false)
    public WorkTask getWorkTask() {
        return this.workTask;
    }
    
    public void setWorkTask(WorkTask workTask) {
        this.workTask = workTask;
    }

    
    @Column(name="accessionnumber")
    public Long getAccessionnumber() {
        return this.accessionnumber;
    }
    
    public void setAccessionnumber(Long accessionnumber) {
        this.accessionnumber = accessionnumber;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="creationdate", nullable=false, length=29)
    public Date getCreationdate() {
        return this.creationdate;
    }
    
    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="completiondate", length=29)
    public Date getCompletiondate() {
        return this.completiondate;
    }
    
    public void setCompletiondate(Date completiondate) {
        this.completiondate = completiondate;
    }

    
    @Column(name="body", nullable=false)
    public String getBody() {
        return this.body;
    }
    
    public void setBody(String body) {
        this.body = body;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="workReport")
    public Set<WorkReportLog> getWorkReportLogs() {
        return this.workReportLogs;
    }
    
    public void setWorkReportLogs(Set<WorkReportLog> workReportLogs) {
        this.workReportLogs = workReportLogs;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="workReport")
    public Set<WorkReportUser> getWorkReportUsers() {
        return this.workReportUsers;
    }
    
    public void setWorkReportUsers(Set<WorkReportUser> workReportUsers) {
        this.workReportUsers = workReportUsers;
    }




}


