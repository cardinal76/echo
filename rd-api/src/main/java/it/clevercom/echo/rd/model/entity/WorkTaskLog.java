package it.clevercom.echo.rd.model.entity;
// Generated 11-mag-2017 9.49.22 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import it.clevercom.echo.common.jpa.entity.AbstractJpaEchoEntity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * WorkTaskLog generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_work_task_log"
)
public class WorkTaskLog  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private Long idworktasklog;
     private WorkTask workTask;
     private Long idworksession;
     private Long accessionnumber;
     private String statuscode;
     private String prioritycode;
     private Long idservice;
     private Long idmodality;
     private Date scheduleddate;
     private Date executiondate;
     private String studyuuid;
     private Long studyid;
     private Long iduser;

    public WorkTaskLog() {
    }

	
    public WorkTaskLog(WorkTask workTask, Long idworksession, Long accessionnumber, String statuscode, String prioritycode, Long idservice, Long idmodality, Date scheduleddate, String studyuuid, Long iduser) {
        this.workTask = workTask;
        this.idworksession = idworksession;
        this.accessionnumber = accessionnumber;
        this.statuscode = statuscode;
        this.prioritycode = prioritycode;
        this.idservice = idservice;
        this.idmodality = idmodality;
        this.scheduleddate = scheduleddate;
        this.studyuuid = studyuuid;
        this.iduser = iduser;
    }
    public WorkTaskLog(WorkTask workTask, Long idworksession, Long accessionnumber, String statuscode, String prioritycode, Long idservice, Long idmodality, Date scheduleddate, Date executiondate, String studyuuid, Long studyid, Long iduser) {
       this.workTask = workTask;
       this.idworksession = idworksession;
       this.accessionnumber = accessionnumber;
       this.statuscode = statuscode;
       this.prioritycode = prioritycode;
       this.idservice = idservice;
       this.idmodality = idmodality;
       this.scheduleddate = scheduleddate;
       this.executiondate = executiondate;
       this.studyuuid = studyuuid;
       this.studyid = studyid;
       this.iduser = iduser;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="worktasklog_idworktasklog_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idworktasklog", unique=true, nullable=false)
    public Long getIdworktasklog() {
        return this.idworktasklog;
    }
    
    public void setIdworktasklog(Long idworktasklog) {
        this.idworktasklog = idworktasklog;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idworktask", nullable=false)
    public WorkTask getWorkTask() {
        return this.workTask;
    }
    
    public void setWorkTask(WorkTask workTask) {
        this.workTask = workTask;
    }

    
    @Column(name="idworksession", nullable=false)
    public Long getIdworksession() {
        return this.idworksession;
    }
    
    public void setIdworksession(Long idworksession) {
        this.idworksession = idworksession;
    }

    
    @Column(name="accessionnumber", nullable=false)
    public Long getAccessionnumber() {
        return this.accessionnumber;
    }
    
    public void setAccessionnumber(Long accessionnumber) {
        this.accessionnumber = accessionnumber;
    }

    
    @Column(name="statuscode", nullable=false, length=100)
    public String getStatuscode() {
        return this.statuscode;
    }
    
    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    
    @Column(name="prioritycode", nullable=false, length=100)
    public String getPrioritycode() {
        return this.prioritycode;
    }
    
    public void setPrioritycode(String prioritycode) {
        this.prioritycode = prioritycode;
    }

    
    @Column(name="idservice", nullable=false)
    public Long getIdservice() {
        return this.idservice;
    }
    
    public void setIdservice(Long idservice) {
        this.idservice = idservice;
    }

    
    @Column(name="idmodality", nullable=false)
    public Long getIdmodality() {
        return this.idmodality;
    }
    
    public void setIdmodality(Long idmodality) {
        this.idmodality = idmodality;
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

    
    @Column(name="studyid")
    public Long getStudyid() {
        return this.studyid;
    }
    
    public void setStudyid(Long studyid) {
        this.studyid = studyid;
    }

    
    @Column(name="iduser", nullable=false)
    public Long getIduser() {
        return this.iduser;
    }
    
    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }




}


