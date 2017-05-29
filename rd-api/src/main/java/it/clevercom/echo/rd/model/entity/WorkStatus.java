package it.clevercom.echo.rd.model.entity;
// Generated 24-mag-2017 22.43.53 by Hibernate Tools 5.2.2.Final


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
import it.clevercom.echo.common.jpa.entity.AbstractJpaEchoEntity;
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
public class WorkStatus  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private Long idworkstatus;
     private String code;
     private String description;
     private Set<Order> orders = new HashSet<Order>(0);
     private Set<WorkTask> workTasks = new HashSet<WorkTask>(0);
     private Set<WorkSession> workSessions = new HashSet<WorkSession>(0);
     private Set<WorkReport> workReports = new HashSet<WorkReport>(0);

    public WorkStatus() {
    }

	
    public WorkStatus(String code) {
        this.code = code;
    }
    public WorkStatus(String code, String description, Set<Order> orders, Set<WorkTask> workTasks, Set<WorkSession> workSessions, Set<WorkReport> workReports) {
       this.code = code;
       this.description = description;
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


