package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rd_body_apparatus database table.
 * 
 */
@Entity
@Table(name="rd_body_apparatus")
@NamedQuery(name="BodyApparatus.findAll", query="SELECT b FROM BodyApparatus b")
public class BodyApparatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String code;

	private Boolean active;

	private Timestamp created;

	private String description;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to Service
	@OneToMany(mappedBy="rdBodyApparatus")
	private List<Service> rdServices;

	public BodyApparatus() {
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

	public List<Service> getRdServices() {
		return this.rdServices;
	}

	public void setRdServices(List<Service> rdServices) {
		this.rdServices = rdServices;
	}

	public Service addRdService(Service rdService) {
		getRdServices().add(rdService);
		rdService.setRdBodyApparatus(this);

		return rdService;
	}

	public Service removeRdService(Service rdService) {
		getRdServices().remove(rdService);
		rdService.setRdBodyApparatus(null);

		return rdService;
	}

}