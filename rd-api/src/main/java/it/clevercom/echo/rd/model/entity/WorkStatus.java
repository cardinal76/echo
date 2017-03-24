package it.clevercom.echo.rd.model.entity;
// Generated 24-mar-2017 8.31.09 by Hibernate Tools 5.2.2.Final


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * WorkStatus generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_work_status"
)
public class WorkStatus  implements java.io.Serializable {


     private Long idworkstatus;
     private String code;
     private String description;
     private Date created;
     private Date updated;
     private String userupdate;
     private Boolean active;
     private Set<Order> orders = new HashSet<Order>(0);
     private Set<WorkTask> workTasks = new HashSet<WorkTask>(0);
     private Set<WorkSession> workSessions = new HashSet<WorkSession>(0);
     private Set<WorkReport> workReports = new HashSet<WorkReport>(0);

    public WorkStatus() {
    }

	
    public WorkStatus(String code, Date created, Date updated, String userupdate, Boolean active) {
        this.code = code;
        this.created = created;
        this.updated = updated;
        this.userupdate = userupdate;
        this.active = active;
    }
    public WorkStatus(String code, String description, Date created, Date updated, String userupdate, Boolean active, Set<Order> orders, Set<WorkTask> workTasks, Set<WorkSession> workSessions, Set<WorkReport> workReports) {
       this.code = code;
       this.description = description;
       this.created = created;
       this.updated = updated;
       this.userupdate = userupdate;
       this.active = active;
       this.orders = orders;
       this.workTasks = workTasks;
       this.workSessions = workSessions;
       this.workReports = workReports;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="workstatus_idworkstatus_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idworkstatus", unique=true, nullable=false)
    public Long getIdworkstatus() {
        return this.idworkstatus;
    }
    
    public void setIdworkstatus(Long idworkstatus) {
        this.idworkstatus = idworkstatus;
    }

    
    @Column(name="code", nullable=false, length=100)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="workStatus")
    public Set<Order> getOrders() {
        return this.orders;
    }
    
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="workStatus")
    public Set<WorkTask> getWorkTasks() {
        return this.workTasks;
    }
    
    public void setWorkTasks(Set<WorkTask> workTasks) {
        this.workTasks = workTasks;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="workStatus")
    public Set<WorkSession> getWorkSessions() {
        return this.workSessions;
    }
    
    public void setWorkSessions(Set<WorkSession> workSessions) {
        this.workSessions = workSessions;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="workStatus")
    public Set<WorkReport> getWorkReports() {
        return this.workReports;
    }
    
    public void setWorkReports(Set<WorkReport> workReports) {
        this.workReports = workReports;
    }




}


