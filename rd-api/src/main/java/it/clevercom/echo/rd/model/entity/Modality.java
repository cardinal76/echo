package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rd_modality database table.
 * 
 */
@Entity
@Table(name="rd_modality")
@NamedQuery(name="Modality.findAll", query="SELECT m FROM Modality m")
public class Modality implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idmodality;

	private Boolean active;

	private Timestamp created;

	private String description;

	private String name;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to OrganizationUnit
	@ManyToOne
	@JoinColumn(name="idorganizationunit")
	private OrganizationUnit rdOrganizationUnit;

	//bi-directional many-to-one association to ModalityService
	@OneToMany(mappedBy="rdModality")
	private List<ModalityService> rdModalityServices;

	//bi-directional many-to-one association to WorkTask
	@OneToMany(mappedBy="rdModality")
	private List<WorkTask> rdWorkTasks;

	public Modality() {
	}

	public Long getIdmodality() {
		return this.idmodality;
	}

	public void setIdmodality(Long idmodality) {
		this.idmodality = idmodality;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public OrganizationUnit getRdOrganizationUnit() {
		return this.rdOrganizationUnit;
	}

	public void setRdOrganizationUnit(OrganizationUnit rdOrganizationUnit) {
		this.rdOrganizationUnit = rdOrganizationUnit;
	}

	public List<ModalityService> getRdModalityServices() {
		return this.rdModalityServices;
	}

	public void setRdModalityServices(List<ModalityService> rdModalityServices) {
		this.rdModalityServices = rdModalityServices;
	}

	public ModalityService addRdModalityService(ModalityService rdModalityService) {
		getRdModalityServices().add(rdModalityService);
		rdModalityService.setRdModality(this);

		return rdModalityService;
	}

	public ModalityService removeRdModalityService(ModalityService rdModalityService) {
		getRdModalityServices().remove(rdModalityService);
		rdModalityService.setRdModality(null);

		return rdModalityService;
	}

	public List<WorkTask> getRdWorkTasks() {
		return this.rdWorkTasks;
	}

	public void setRdWorkTasks(List<WorkTask> rdWorkTasks) {
		this.rdWorkTasks = rdWorkTasks;
	}

	public WorkTask addRdWorkTask(WorkTask rdWorkTask) {
		getRdWorkTasks().add(rdWorkTask);
		rdWorkTask.setRdModality(this);

		return rdWorkTask;
	}

	public WorkTask removeRdWorkTask(WorkTask rdWorkTask) {
		getRdWorkTasks().remove(rdWorkTask);
		rdWorkTask.setRdModality(null);

		return rdWorkTask;
	}

}