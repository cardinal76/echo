package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rd_work_priority database table.
 * 
 */
@Entity
@Table(name="rd_work_priority")
@NamedQuery(name="WorkPriority.findAll", query="SELECT w FROM WorkPriority w")
public class WorkPriority implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String code;

	private Boolean active;

	private Timestamp created;

	private String description;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="rdWorkPriority")
	private List<Order> rdOrders;

	//bi-directional many-to-one association to WorkTask
	@OneToMany(mappedBy="rdWorkPriority")
	private List<WorkTask> rdWorkTasks;

	public WorkPriority() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public List<Order> getRdOrders() {
		return this.rdOrders;
	}

	public void setRdOrders(List<Order> rdOrders) {
		this.rdOrders = rdOrders;
	}

	public Order addRdOrder(Order rdOrder) {
		getRdOrders().add(rdOrder);
		rdOrder.setRdWorkPriority(this);

		return rdOrder;
	}

	public Order removeRdOrder(Order rdOrder) {
		getRdOrders().remove(rdOrder);
		rdOrder.setRdWorkPriority(null);

		return rdOrder;
	}

	public List<WorkTask> getRdWorkTasks() {
		return this.rdWorkTasks;
	}

	public void setRdWorkTasks(List<WorkTask> rdWorkTasks) {
		this.rdWorkTasks = rdWorkTasks;
	}

	public WorkTask addRdWorkTask(WorkTask rdWorkTask) {
		getRdWorkTasks().add(rdWorkTask);
		rdWorkTask.setRdWorkPriority(this);

		return rdWorkTask;
	}

	public WorkTask removeRdWorkTask(WorkTask rdWorkTask) {
		getRdWorkTasks().remove(rdWorkTask);
		rdWorkTask.setRdWorkPriority(null);

		return rdWorkTask;
	}

}