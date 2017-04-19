package it.clevercom.echo.rd.model.entity;
// Generated 19-apr-2017 15.19.48 by Hibernate Tools 5.2.2.Final


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
 * WorkPriority generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_work_priority"
)
public class WorkPriority  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private Long idworkpriority;
     private String code;
     private String description;
     private Set<Order> orders = new HashSet<Order>(0);
     private Set<WorkTask> workTasks = new HashSet<WorkTask>(0);

    public WorkPriority() {
    }

	
    public WorkPriority(String code) {
        this.code = code;
    }
    public WorkPriority(String code, String description, Set<Order> orders, Set<WorkTask> workTasks) {
       this.code = code;
       this.description = description;
       this.orders = orders;
       this.workTasks = workTasks;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="workpriority_idworkpriority_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idworkpriority", unique=true, nullable=false)
    public Long getIdworkpriority() {
        return this.idworkpriority;
    }
    
    public void setIdworkpriority(Long idworkpriority) {
        this.idworkpriority = idworkpriority;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="workPriority")
    public Set<Order> getOrders() {
        return this.orders;
    }
    
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="workPriority")
    public Set<WorkTask> getWorkTasks() {
        return this.workTasks;
    }
    
    public void setWorkTasks(Set<WorkTask> workTasks) {
        this.workTasks = workTasks;
    }




}


