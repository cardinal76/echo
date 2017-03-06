package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the rd_order_log database table.
 * 
 */
@Entity
@Table(name="rd_order_log")
@NamedQuery(name="OrderLog.findAll", query="SELECT o FROM OrderLog o")
public class OrderLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idorderlog;

	@Temporal(TemporalType.DATE)
	private Date acceptancedate;

	private String acquisitionchannel;

	private Boolean active;

	private String clinicalhistory;

	private Timestamp created;

	@Temporal(TemporalType.DATE)
	private Date creationdate;

	private Long duration;

	private Long idworksession;

	private String notes;

	private String orderreason;

	private Long originorganizationunitid;

	private String prioritycode;

	private String rejectreason;

	private String requestingphysician;

	@Temporal(TemporalType.DATE)
	private Date scheduleddate;

	private String statuscode;

	private Long targetorganizationunitid;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="idorder")
	private Order rdOrder;

	public OrderLog() {
	}

	public Long getIdorderlog() {
		return this.idorderlog;
	}

	public void setIdorderlog(Long idorderlog) {
		this.idorderlog = idorderlog;
	}

	public Date getAcceptancedate() {
		return this.acceptancedate;
	}

	public void setAcceptancedate(Date acceptancedate) {
		this.acceptancedate = acceptancedate;
	}

	public String getAcquisitionchannel() {
		return this.acquisitionchannel;
	}

	public void setAcquisitionchannel(String acquisitionchannel) {
		this.acquisitionchannel = acquisitionchannel;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getClinicalhistory() {
		return this.clinicalhistory;
	}

	public void setClinicalhistory(String clinicalhistory) {
		this.clinicalhistory = clinicalhistory;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Date getCreationdate() {
		return this.creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Long getDuration() {
		return this.duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Long getIdworksession() {
		return this.idworksession;
	}

	public void setIdworksession(Long idworksession) {
		this.idworksession = idworksession;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getOrderreason() {
		return this.orderreason;
	}

	public void setOrderreason(String orderreason) {
		this.orderreason = orderreason;
	}

	public Long getOriginorganizationunitid() {
		return this.originorganizationunitid;
	}

	public void setOriginorganizationunitid(Long originorganizationunitid) {
		this.originorganizationunitid = originorganizationunitid;
	}

	public String getPrioritycode() {
		return this.prioritycode;
	}

	public void setPrioritycode(String prioritycode) {
		this.prioritycode = prioritycode;
	}

	public String getRejectreason() {
		return this.rejectreason;
	}

	public void setRejectreason(String rejectreason) {
		this.rejectreason = rejectreason;
	}

	public String getRequestingphysician() {
		return this.requestingphysician;
	}

	public void setRequestingphysician(String requestingphysician) {
		this.requestingphysician = requestingphysician;
	}

	public Date getScheduleddate() {
		return this.scheduleddate;
	}

	public void setScheduleddate(Date scheduleddate) {
		this.scheduleddate = scheduleddate;
	}

	public String getStatuscode() {
		return this.statuscode;
	}

	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}

	public Long getTargetorganizationunitid() {
		return this.targetorganizationunitid;
	}

	public void setTargetorganizationunitid(Long targetorganizationunitid) {
		this.targetorganizationunitid = targetorganizationunitid;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getUserupdate() {
		return this.userupdate;
	}

	public void setUserupdate(String userupdate) {
		this.userupdate = userupdate;
	}

	public Order getRdOrder() {
		return this.rdOrder;
	}

	public void setRdOrder(Order rdOrder) {
		this.rdOrder = rdOrder;
	}

}