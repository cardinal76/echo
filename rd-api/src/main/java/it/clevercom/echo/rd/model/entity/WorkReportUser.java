package it.clevercom.echo.rd.model.entity;
// Generated 26-apr-2017 17.35.35 by Hibernate Tools 5.2.2.Final


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
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * WorkReportUser generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_work_report_user"
    , uniqueConstraints = @UniqueConstraint(columnNames={"username", "idworkreport"}) 
)
public class WorkReportUser  extends AbstractJpaEchoEntity implements java.io.Serializable {


     private Long idworkreportuser;
     private User user;
     private WorkReport workReport;

    public WorkReportUser() {
    }

    public WorkReportUser(User user, WorkReport workReport) {
       this.user = user;
       this.workReport = workReport;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="workreportuser_idworkreportuser_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idworkreportuser", unique=true, nullable=false)
    public Long getIdworkreportuser() {
        return this.idworkreportuser;
    }
    
    public void setIdworkreportuser(Long idworkreportuser) {
        this.idworkreportuser = idworkreportuser;
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
    @JoinColumn(name="idworkreport", nullable=false)
    public WorkReport getWorkReport() {
        return this.workReport;
    }
    
    public void setWorkReport(WorkReport workReport) {
        this.workReport = workReport;
    }




}


