package it.clevercom.echo.rd.model.entity;
// Generated 19-apr-2017 12.25.52 by Hibernate Tools 5.2.2.Final


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
 * Order generated by hbm2java
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="rd_order"
)
public class Order  implements java.io.Serializable {


     private Long idorder;
     private OrganizationUnit organizationUnitByOriginorganizationunitid;
     private OrganizationUnit organizationUnitByTargetorganizationunitid;
     private Patient patient;
     private WorkPriority workPriority;
     private WorkSession workSession;
     private WorkStatus workStatus;
     private String acquisitionchannel;
     private Date creationdate;
     private Date scheduleddate;
     private Date acceptancedate;
     private Long duration;
     private String requestingphysician;
     private String clinicalquestion;
     private String rejectreason;
     private String anamnesys;
     private String notes;
     private Date created;
     private Date updated;
     private String userupdate;
     private Boolean active;
     private String cancelreason;
     private String identificationdocument;
     private Date executingdate;
     private Date executeddate;
     private Date reportingdate;
     private Date reporteddate;
     private Date signeddate;
     private Date delivereddate;
     private Date archiveddate;
     private Date canceleddate;
     private Set<OrderLog> orderLogs = new HashSet<OrderLog>(0);
     private Set<OrderService> orderServices = new HashSet<OrderService>(0);

    public Order() {
    }

	
    public Order(Patient patient, WorkPriority workPriority, WorkStatus workStatus, String acquisitionchannel, Date creationdate, Date created, Date updated, String userupdate, Boolean active) {
        this.patient = patient;
        this.workPriority = workPriority;
        this.workStatus = workStatus;
        this.acquisitionchannel = acquisitionchannel;
        this.creationdate = creationdate;
        this.created = created;
        this.updated = updated;
        this.userupdate = userupdate;
        this.active = active;
    }
    public Order(OrganizationUnit organizationUnitByOriginorganizationunitid, OrganizationUnit organizationUnitByTargetorganizationunitid, Patient patient, WorkPriority workPriority, WorkSession workSession, WorkStatus workStatus, String acquisitionchannel, Date creationdate, Date scheduleddate, Date acceptancedate, Long duration, String requestingphysician, String clinicalquestion, String rejectreason, String anamnesys, String notes, Date created, Date updated, String userupdate, Boolean active, String cancelreason, String identificationdocument, Date executingdate, Date executeddate, Date reportingdate, Date reporteddate, Date signeddate, Date delivereddate, Date archiveddate, Date canceleddate, Set<OrderLog> orderLogs, Set<OrderService> orderServices) {
       this.organizationUnitByOriginorganizationunitid = organizationUnitByOriginorganizationunitid;
       this.organizationUnitByTargetorganizationunitid = organizationUnitByTargetorganizationunitid;
       this.patient = patient;
       this.workPriority = workPriority;
       this.workSession = workSession;
       this.workStatus = workStatus;
       this.acquisitionchannel = acquisitionchannel;
       this.creationdate = creationdate;
       this.scheduleddate = scheduleddate;
       this.acceptancedate = acceptancedate;
       this.duration = duration;
       this.requestingphysician = requestingphysician;
       this.clinicalquestion = clinicalquestion;
       this.rejectreason = rejectreason;
       this.anamnesys = anamnesys;
       this.notes = notes;
       this.created = created;
       this.updated = updated;
       this.userupdate = userupdate;
       this.active = active;
       this.cancelreason = cancelreason;
       this.identificationdocument = identificationdocument;
       this.executingdate = executingdate;
       this.executeddate = executeddate;
       this.reportingdate = reportingdate;
       this.reporteddate = reporteddate;
       this.signeddate = signeddate;
       this.delivereddate = delivereddate;
       this.archiveddate = archiveddate;
       this.canceleddate = canceleddate;
       this.orderLogs = orderLogs;
       this.orderServices = orderServices;
    }
   
     @GenericGenerator(name="generator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", parameters={@Parameter(name="optimizer", value="none"), @Parameter(name="sequence_name", value="order_idorder_seq"), @Parameter(name="increment_size", value="1")})@Id @GeneratedValue(generator="generator")

    
    @Column(name="idorder", unique=true, nullable=false)
    public Long getIdorder() {
        return this.idorder;
    }
    
    public void setIdorder(Long idorder) {
        this.idorder = idorder;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="originorganizationunitid")
    public OrganizationUnit getOrganizationUnitByOriginorganizationunitid() {
        return this.organizationUnitByOriginorganizationunitid;
    }
    
    public void setOrganizationUnitByOriginorganizationunitid(OrganizationUnit organizationUnitByOriginorganizationunitid) {
        this.organizationUnitByOriginorganizationunitid = organizationUnitByOriginorganizationunitid;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="targetorganizationunitid")
    public OrganizationUnit getOrganizationUnitByTargetorganizationunitid() {
        return this.organizationUnitByTargetorganizationunitid;
    }
    
    public void setOrganizationUnitByTargetorganizationunitid(OrganizationUnit organizationUnitByTargetorganizationunitid) {
        this.organizationUnitByTargetorganizationunitid = organizationUnitByTargetorganizationunitid;
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
    @JoinColumn(name="idworksession")
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

    
    @Column(name="acquisitionchannel", nullable=false, length=20)
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

    
    @Column(name="requestingphysician")
    public String getRequestingphysician() {
        return this.requestingphysician;
    }
    
    public void setRequestingphysician(String requestingphysician) {
        this.requestingphysician = requestingphysician;
    }

    
    @Column(name="clinicalquestion")
    public String getClinicalquestion() {
        return this.clinicalquestion;
    }
    
    public void setClinicalquestion(String clinicalquestion) {
        this.clinicalquestion = clinicalquestion;
    }

    
    @Column(name="rejectreason")
    public String getRejectreason() {
        return this.rejectreason;
    }
    
    public void setRejectreason(String rejectreason) {
        this.rejectreason = rejectreason;
    }

    
    @Column(name="anamnesys")
    public String getAnamnesys() {
        return this.anamnesys;
    }
    
    public void setAnamnesys(String anamnesys) {
        this.anamnesys = anamnesys;
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

    
    @Column(name="cancelreason", length=500)
    public String getCancelreason() {
        return this.cancelreason;
    }
    
    public void setCancelreason(String cancelreason) {
        this.cancelreason = cancelreason;
    }

    
    @Column(name="identificationdocument")
    public String getIdentificationdocument() {
        return this.identificationdocument;
    }
    
    public void setIdentificationdocument(String identificationdocument) {
        this.identificationdocument = identificationdocument;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="order")
    public Set<OrderLog> getOrderLogs() {
        return this.orderLogs;
    }
    
    public void setOrderLogs(Set<OrderLog> orderLogs) {
        this.orderLogs = orderLogs;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="order")
    public Set<OrderService> getOrderServices() {
        return this.orderServices;
    }
    
    public void setOrderServices(Set<OrderService> orderServices) {
        this.orderServices = orderServices;
    }




}


