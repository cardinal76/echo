package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rd_order_service database table.
 * 
 */
@Entity
@Table(name="rd_order_service")
@NamedQuery(name="OrderService.findAll", query="SELECT o FROM OrderService o")
public class OrderService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idorderservice;

	private Boolean active;

	private Timestamp created;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="idorder")
	private Order rdOrder;

	//bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name="idservice")
	private Service rdService;

	public OrderService() {
	}

	public Long getIdorderservice() {
		return this.idorderservice;
	}

	public void setIdorderservice(Long idorderservice) {
		this.idorderservice = idorderservice;
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

	public Service getRdService() {
		return this.rdService;
	}

	public void setRdService(Service rdService) {
		this.rdService = rdService;
	}

}