package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rd_order database table.
 * 
 */
@Entity
@Table(name="rd_order")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idorder;

	@Temporal(TemporalType.DATE)
	private Date acceptancedate;

	private String acquisitionchannel;

	private Boolean active;

	private String clinicalhistory;

	private Timestamp created;

	@Temporal(TemporalType.DATE)
	private Date creationdate;

	private Long duration;

	private String notes;

	private String orderreason;

	private String rejectreason;

	private String requestingphysician;

	@Temporal(TemporalType.DATE)
	private Date scheduleddate;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to OrganizationUnit
	@ManyToOne
	@JoinColumn(name="originorganizationunitid")
	private OrganizationUnit rdOrganizationUnit1;

	//bi-directional many-to-one association to OrganizationUnit
	@ManyToOne
	@JoinColumn(name="targetorganizationunitid")
	private OrganizationUnit rdOrganizationUnit2;

	//bi-directional many-to-one association to WorkPriority
	@ManyToOne
	@JoinColumn(name="prioritycode")
	private WorkPriority rdWorkPriority;

	//bi-directional many-to-one association to WorkSession
	@ManyToOne
	@JoinColumn(name="idworksession")
	private WorkSession rdWorkSession;

	//bi-directional many-to-one association to WorkStatus
	@ManyToOne
	@JoinColumn(name="statuscode")
	private WorkStatus rdWorkStatus;

	//bi-directional many-to-one association to OrderLog
	@OneToMany(mappedBy="rdOrder")
	private List<OrderLog> rdOrderLogs;

	//bi-directional many-to-one association to OrderService
	@OneToMany(mappedBy="rdOrder")
	private List<OrderService> rdOrderServices;

	public Order() {
	}

	public Long getIdorder() {
		return this.idorder;
	}

	public void setIdorder(Long idorder) {
		this.idorder = idorder;
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

	public OrganizationUnit getRdOrganizationUnit1() {
		return this.rdOrganizationUnit1;
	}

	public void setRdOrganizationUnit1(OrganizationUnit rdOrganizationUnit1) {
		this.rdOrganizationUnit1 = rdOrganizationUnit1;
	}

	public OrganizationUnit getRdOrganizationUnit2() {
		return this.rdOrganizationUnit2;
	}

	public void setRdOrganizationUnit2(OrganizationUnit rdOrganizationUnit2) {
		this.rdOrganizationUnit2 = rdOrganizationUnit2;
	}

	public WorkPriority getRdWorkPriority() {
		return this.rdWorkPriority;
	}

	public void setRdWorkPriority(WorkPriority rdWorkPriority) {
		this.rdWorkPriority = rdWorkPriority;
	}

	public WorkSession getRdWorkSession() {
		return this.rdWorkSession;
	}

	public void setRdWorkSession(WorkSession rdWorkSession) {
		this.rdWorkSession = rdWorkSession;
	}

	public WorkStatus getRdWorkStatus() {
		return this.rdWorkStatus;
	}

	public void setRdWorkStatus(WorkStatus rdWorkStatus) {
		this.rdWorkStatus = rdWorkStatus;
	}

	public List<OrderLog> getRdOrderLogs() {
		return this.rdOrderLogs;
	}

	public void setRdOrderLogs(List<OrderLog> rdOrderLogs) {
		this.rdOrderLogs = rdOrderLogs;
	}

	public OrderLog addRdOrderLog(OrderLog rdOrderLog) {
		getRdOrderLogs().add(rdOrderLog);
		rdOrderLog.setRdOrder(this);

		return rdOrderLog;
	}

	public OrderLog removeRdOrderLog(OrderLog rdOrderLog) {
		getRdOrderLogs().remove(rdOrderLog);
		rdOrderLog.setRdOrder(null);

		return rdOrderLog;
	}

	public List<OrderService> getRdOrderServices() {
		return this.rdOrderServices;
	}

	public void setRdOrderServices(List<OrderService> rdOrderServices) {
		this.rdOrderServices = rdOrderServices;
	}

	public OrderService addRdOrderService(OrderService rdOrderService) {
		getRdOrderServices().add(rdOrderService);
		rdOrderService.setRdOrder(this);

		return rdOrderService;
	}

	public OrderService removeRdOrderService(OrderService rdOrderService) {
		getRdOrderServices().remove(rdOrderService);
		rdOrderService.setRdOrder(null);

		return rdOrderService;
	}

}