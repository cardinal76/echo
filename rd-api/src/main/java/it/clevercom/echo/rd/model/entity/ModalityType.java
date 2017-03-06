package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rd_modality_type database table.
 * 
 */
@Entity
@Table(name="rd_modality_type")
@NamedQuery(name="ModalityType.findAll", query="SELECT m FROM ModalityType m")
public class ModalityType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idmodalitytype;

	private Boolean active;

	private Timestamp created;

	private String type;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to Service
	@OneToMany(mappedBy="rdModalityType")
	private List<Service> rdServices;

	public ModalityType() {
	}

	public Long getIdmodalitytype() {
		return this.idmodalitytype;
	}

	public void setIdmodalitytype(Long idmodalitytype) {
		this.idmodalitytype = idmodalitytype;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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
		rdService.setRdModalityType(this);

		return rdService;
	}

	public Service removeRdService(Service rdService) {
		getRdServices().remove(rdService);
		rdService.setRdModalityType(null);

		return rdService;
	}

}