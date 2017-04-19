package it.clevercom.echo.rd.model.entity;
// Generated 19-apr-2017 12.25.52 by Hibernate Tools 5.2.2.Final


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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * OrderLog generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_order_log"
)
public class OrderLog  implements java.io.Serializable {


     private Long idorderlog;
     private Order order;
     private Long idworksession;
     private Long idworkstatus;
     private Long idworkpriority;
     private String acquisitionchannel;
     private Date creationdate;
     private Date scheduleddate;
     private Date acceptancedate;
     private Long duration;
     private Long originorganizationunitid;
     private Long targetorganizationunitid;
     private String requestingphysician;
     private String orderreason;
     private String rejectreason;
     private String clinicalhistory;
     private String notes;
     private Date created;
     private Date updated;
     private String userupdate;
     private Boolean active;
     private Date executingdate;
     private Date executeddate;
     private Date reportingdate;
     private Date reporteddate;
     private Date signeddate;
     private Date delivereddate;
     private Date archiveddate;
     private Date canceleddate;

    public OrderLog() {
    }

	
    public OrderLog(Order order, Date creationdate, Date created, Date updated, String userupdate, Boolean active) {
        this.order = order;
        this.creationdate = creationdate;
        this.created = created;
        this.updated = updated;
        this.userupdate = userupdate;
        this.active = active;
    }
    public OrderLog(Order order, Long idworksession, Long idworkstatus, Long idworkpriority, String acquisitionchannel, Date creationdate, Date scheduleddate, Date acceptancedate, Long duration, Long originorganizationunitid, Long targetorganizationunitid, String requestingphysician, String orderreason, String rejectreason, String clinicalhistory, String notes, Date created, Date updated, String userupdate, Boolean active, Date executingdate, Date executeddate, Date reportingdate, Date reporteddate, Date signeddate, Date delivereddate, Date archiveddate, Date canceleddate) {
       this.order = order;
       this.idworksession = idworksession;
       this.idworkstatus = idworkstatus;
       this.idworkpriority = idworkpriority;
       this.acquisitionchannel = acquisitionchannel;
       this.creationdate = creationdate;
       this.scheduleddate = scheduleddate;
       this.acceptancedate = acceptancedate;
       this.duration = duration;
       this.originorganizationunitid = originorganizationunitid;
       this.targetorganizationunitid = targetorganizationunitid;
       this.requestingphysician = requestingphysician;
       this.orderreason = orderreason;
       this.rejectreason = rejectreason;
       this.clinicalhistory = clinicalhistory;
       this.notes = notes;
       this.created = created;
       this.updated = updated;
       this.userupdate = userupdate;
       this.active = active;
       this.executingdate = executingdate;
       this.executeddate = executeddate;
       this.reportingdate = reportingdate;
       this.reporteddate = reporteddate;
       this.signeddate = signeddate;
       this.delivereddate = delivereddate;
       this.archiveddate = archiveddate;
       this.canceleddate = canceleddate;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="orderlog_idorderlog_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idorderlog", unique=true, nullable=false)
    public Long getIdorderlog() {
        return this.idorderlog;
    }
    
    public void setIdorderlog(Long idorderlog) {
        this.idorderlog = idorderlog;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idorder", nullable=false)
    public Order getOrder() {
        return this.order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }

    
    @Column(name="idworksession")
    public Long getIdworksession() {
        return this.idworksession;
    }
    
    public void setIdworksession(Long idworksession) {
        this.idworksession = idworksession;
    }

    
    @Column(name="idworkstatus")
    public Long getIdworkstatus() {
        return this.idworkstatus;
    }
    
    public void setIdworkstatus(Long idworkstatus) {
        this.idworkstatus = idworkstatus;
    }

    
    @Column(name="idworkpriority")
    public Long getIdworkpriority() {
        return this.idworkpriority;
    }
    
    public void setIdworkpriority(Long idworkpriority) {
        this.idworkpriority = idworkpriority;
    }

    
    @Column(name="acquisitionchannel", length=20)
    public String getAcquisitionchannel() {
        return this.acquisitionchannel;
    }
    
    public void setAcquisitionchannel(String acquisitionchannel) {
        this.acquisitionchannel = acquisitionchannel;
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
    @Column(name="scheduleddate", length=29)
    public Date getScheduleddate() {
        return this.scheduleddate;
    }
    
    public void setScheduleddate(Date scheduleddate) {
        this.scheduleddate = scheduleddate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="acceptancedate", length=29)
    public Date getAcceptancedate() {
        return this.acceptancedate;
    }
    
    public void setAcceptancedate(Date acceptancedate) {
        this.acceptancedate = acceptancedate;
    }

    
    @Column(name="duration")
    public Long getDuration() {
        return this.duration;
    }
    
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    
    @Column(name="originorganizationunitid")
    public Long getOriginorganizationunitid() {
        return this.originorganizationunitid;
    }
    
    public void setOriginorganizationunitid(Long originorganizationunitid) {
        this.originorganizationunitid = originorganizationunitid;
    }

    
    @Column(name="targetorganizationunitid")
    public Long getTargetorganizationunitid() {
        return this.targetorganizationunitid;
    }
    
    public void setTargetorganizationunitid(Long targetorganizationunitid) {
        this.targetorganizationunitid = targetorganizationunitid;
    }

    
    @Column(name="requestingphysician")
    public String getRequestingphysician() {
        return this.requestingphysician;
    }
    
    public void setRequestingphysician(String requestingphysician) {
        this.requestingphysician = requestingphysician;
    }

    
    @Column(name="orderreason")
    public String getOrderreason() {
        return this.orderreason;
    }
    
    public void setOrderreason(String orderreason) {
        this.orderreason = orderreason;
    }

    
    @Column(name="rejectreason")
    public String getRejectreason() {
        return this.rejectreason;
    }
    
    public void setRejectreason(String rejectreason) {
        this.rejectreason = rejectreason;
    }

    
    @Column(name="clinicalhistory")
    public String getClinicalhistory() {
        return this.clinicalhistory;
    }
    
    public void setClinicalhistory(String clinicalhistory) {
        this.clinicalhistory = clinicalhistory;
    }

    
    @Column(name="notes")
    public String getNotes() {
        return this.notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="executingdate", length=29)
    public Date getExecutingdate() {
        return this.executingdate;
    }
    
    public void setExecutingdate(Date executingdate) {
        this.executingdate = executingdate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="executeddate", length=29)
    public Date getExecuteddate() {
        return this.executeddate;
    }
    
    public void setExecuteddate(Date executeddate) {
        this.executeddate = executeddate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="reportingdate", length=29)
    public Date getReportingdate() {
        return this.reportingdate;
    }
    
    public void setReportingdate(Date reportingdate) {
        this.reportingdate = reportingdate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="reporteddate", length=29)
    public Date getReporteddate() {
        return this.reporteddate;
    }
    
    public void setReporteddate(Date reporteddate) {
        this.reporteddate = reporteddate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="signeddate", length=29)
    public Date getSigneddate() {
        return this.signeddate;
    }
    
    public void setSigneddate(Date signeddate) {
        this.signeddate = signeddate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="delivereddate", length=29)
    public Date getDelivereddate() {
        return this.delivereddate;
    }
    
    public void setDelivereddate(Date delivereddate) {
        this.delivereddate = delivereddate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="archiveddate", length=29)
    public Date getArchiveddate() {
        return this.archiveddate;
    }
    
    public void setArchiveddate(Date archiveddate) {
        this.archiveddate = archiveddate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="canceleddate", length=29)
    public Date getCanceleddate() {
        return this.canceleddate;
    }
    
    public void setCanceleddate(Date canceleddate) {
        this.canceleddate = canceleddate;
    }




}


