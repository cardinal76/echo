package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rd_modality_service database table.
 * 
 */
@Entity
@Table(name="rd_modality_service")
@NamedQuery(name="ModalityService.findAll", query="SELECT m FROM ModalityService m")
public class ModalityService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idmodalityservice;

	private Boolean active;

	private Timestamp created;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to Modality
	@ManyToOne
	@JoinColumn(name="idmodality")
	private Modality rdModality;

	//bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name="idservice")
	private Service rdService;

	public ModalityService() {
	}

	public Long getIdmodalityservice() {
		return this.idmodalityservice;
	}

	public void setIdmodalityservice(Long idmodalityservice) {
		this.idmodalityservice = idmodalityservice;
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

	public Modality getRdModality() {
		return this.rdModality;
	}

	public void setRdModality(Modality rdModality) {
		this.rdModality = rdModality;
	}

	public Service getRdService() {
		return this.rdService;
	}

	public void setRdService(Service rdService) {
		this.rdService = rdService;
	}

}