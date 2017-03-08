package it.clevercom.echo.rd.model.entity;
// Generated 8-mar-2017 10.59.59 by Hibernate Tools 5.2.2.Final


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
     private String statuscode;
     private String prioritycode;
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
     private boolean active;

    public OrderLog() {
    }

	
    public OrderLog(Order order, Long idworksession, String statuscode, String prioritycode, String acquisitionchannel, Date creationdate, Date created, Date updated, String userupdate, boolean active) {
        this.order = order;
        this.idworksession = idworksession;
        this.statuscode = statuscode;
        this.prioritycode = prioritycode;
        this.acquisitionchannel = acquisitionchannel;
        this.creationdate = creationdate;
        this.created = created;
        this.updated = updated;
        this.userupdate = userupdate;
        this.active = active;
    }
    public OrderLog(Order order, Long idworksession, String statuscode, String prioritycode, String acquisitionchannel, Date creationdate, Date scheduleddate, Date acceptancedate, Long duration, Long originorganizationunitid, Long targetorganizationunitid, String requestingphysician, String orderreason, String rejectreason, String clinicalhistory, String notes, Date created, Date updated, String userupdate, boolean active) {
       this.order = order;
       this.idworksession = idworksession;
       this.statuscode = statuscode;
       this.prioritycode = prioritycode;
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
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="order_log_idorderlog_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
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

    
    @Column(name="idworksession", nullable=false)
    public Long getIdworksession() {
        return this.idworksession;
    }
    
    public void setIdworksession(Long idworksession) {
        this.idworksession = idworksession;
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

    
    @Column(name="acquisitionchannel", nullable=false, length=20)
    public String getAcquisitionchannel() {
        return this.acquisitionchannel;
    }
    
    public void setAcquisitionchannel(String acquisitionchannel) {
        this.acquisitionchannel = acquisitionchannel;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="creationdate", nullable=false, length=13)
    public Date getCreationdate() {
        return this.creationdate;
    }
    
    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="scheduleddate", length=13)
    public Date getScheduleddate() {
        return this.scheduleddate;
    }
    
    public void setScheduleddate(Date scheduleddate) {
        this.scheduleddate = scheduleddate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="acceptancedate", length=13)
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
    public boolean isActive() {
        return this.active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }




}


