package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rd_service database table.
 * 
 */
@Entity
@Table(name="rd_service")
@NamedQuery(name="Service.findAll", query="SELECT s FROM Service s")
public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idservice;

	private Boolean active;

	private Timestamp created;

	private String description;

	private Long duration;

	private Long prepcode;

	private Boolean schedulable;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to ModalityService
	@OneToMany(mappedBy="rdService")
	private List<ModalityService> rdModalityServices;

	//bi-directional many-to-one association to OrderService
	@OneToMany(mappedBy="rdService")
	private List<OrderService> rdOrderServices;

	//bi-directional many-to-one association to BodyApparatus
	@ManyToOne
	@JoinColumn(name="bodyapparatuscode")
	private BodyApparatus rdBodyApparatus;

	//bi-directional many-to-one association to ModalityType
	@ManyToOne
	@JoinColumn(name="idmodalitytype")
	private ModalityType rdModalityType;

	//bi-directional many-to-one association to ServiceCodingActor
	@OneToMany(mappedBy="rdService")
	private List<ServiceCodingActor> rdServiceCodingActors;

	//bi-directional many-to-one association to WorkTask
	@OneToMany(mappedBy="rdService")
	private List<WorkTask> rdWorkTasks;

	public Service() {
	}

	public Long getIdservice() {
		return this.idservice;
	}

	public void setIdservice(Long idservice) {
		this.idservice = idservice;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getDuration() {
		return this.duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Long getPrepcode() {
		return this.prepcode;
	}

	public void setPrepcode(Long prepcode) {
		this.prepcode = prepcode;
	}

	public Boolean getSchedulable() {
		return this.schedulable;
	}

	public void setSchedulable(Boolean schedulable) {
		this.schedulable = schedulable;
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

	public List<ModalityService> getRdModalityServices() {
		return this.rdModalityServices;
	}

	public void setRdModalityServices(List<ModalityService> rdModalityServices) {
		this.rdModalityServices = rdModalityServices;
	}

	public ModalityService addRdModalityService(ModalityService rdModalityService) {
		getRdModalityServices().add(rdModalityService);
		rdModalityService.setRdService(this);

		return rdModalityService;
	}

	public ModalityService removeRdModalityService(ModalityService rdModalityService) {
		getRdModalityServices().remove(rdModalityService);
		rdModalityService.setRdService(null);

		return rdModalityService;
	}

	public List<OrderService> getRdOrderServices() {
		return this.rdOrderServices;
	}

	public void setRdOrderServices(List<OrderService> rdOrderServices) {
		this.rdOrderServices = rdOrderServices;
	}

	public OrderService addRdOrderService(OrderService rdOrderService) {
		getRdOrderServices().add(rdOrderService);
		rdOrderService.setRdService(this);

		return rdOrderService;
	}

	public OrderService removeRdOrderService(OrderService rdOrderService) {
		getRdOrderServices().remove(rdOrderService);
		rdOrderService.setRdService(null);

		return rdOrderService;
	}

	public BodyApparatus getRdBodyApparatus() {
		return this.rdBodyApparatus;
	}

	public void setRdBodyApparatus(BodyApparatus rdBodyApparatus) {
		this.rdBodyApparatus = rdBodyApparatus;
	}

	public ModalityType getRdModalityType() {
		return this.rdModalityType;
	}

	public void setRdModalityType(ModalityType rdModalityType) {
		this.rdModalityType = rdModalityType;
	}

	public List<ServiceCodingActor> getRdServiceCodingActors() {
		return this.rdServiceCodingActors;
	}

	public void setRdServiceCodingActors(List<ServiceCodingActor> rdServiceCodingActors) {
		this.rdServiceCodingActors = rdServiceCodingActors;
	}

	public ServiceCodingActor addRdServiceCodingActor(ServiceCodingActor rdServiceCodingActor) {
		getRdServiceCodingActors().add(rdServiceCodingActor);
		rdServiceCodingActor.setRdService(this);

		return rdServiceCodingActor;
	}

	public ServiceCodingActor removeRdServiceCodingActor(ServiceCodingActor rdServiceCodingActor) {
		getRdServiceCodingActors().remove(rdServiceCodingActor);
		rdServiceCodingActor.setRdService(null);

		return rdServiceCodingActor;
	}

	public List<WorkTask> getRdWorkTasks() {
		return this.rdWorkTasks;
	}

	public void setRdWorkTasks(List<WorkTask> rdWorkTasks) {
		this.rdWorkTasks = rdWorkTasks;
	}

	public WorkTask addRdWorkTask(WorkTask rdWorkTask) {
		getRdWorkTasks().add(rdWorkTask);
		rdWorkTask.setRdService(this);

		return rdWorkTask;
	}

	public WorkTask removeRdWorkTask(WorkTask rdWorkTask) {
		getRdWorkTasks().remove(rdWorkTask);
		rdWorkTask.setRdService(null);

		return rdWorkTask;
	}

}