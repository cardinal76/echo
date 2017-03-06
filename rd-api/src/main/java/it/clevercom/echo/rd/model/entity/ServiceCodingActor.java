package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rd_service_coding_actor database table.
 * 
 */
@Entity
@Table(name="rd_service_coding_actor")
@NamedQuery(name="ServiceCodingActor.findAll", query="SELECT s FROM ServiceCodingActor s")
public class ServiceCodingActor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ServiceCodingActorPK id;

	private Boolean active;

	private Timestamp created;

	private String externalcode;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to CodingActor
	@ManyToOne
	@JoinColumn(name="idcodingactor", insertable=false, updatable=false)
	private CodingActor rdCodingActor;

	//bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name="idservice", insertable=false, updatable=false)
	private Service rdService;

	public ServiceCodingActor() {
	}

	public ServiceCodingActorPK getId() {
		return this.id;
	}

	public void setId(ServiceCodingActorPK id) {
		this.id = id;
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

	public String getExternalcode() {
		return this.externalcode;
	}

	public void setExternalcode(String externalcode) {
		this.externalcode = externalcode;
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

	public CodingActor getRdCodingActor() {
		return this.rdCodingActor;
	}

	public void setRdCodingActor(CodingActor rdCodingActor) {
		this.rdCodingActor = rdCodingActor;
	}

	public Service getRdService() {
		return this.rdService;
	}

	public void setRdService(Service rdService) {
		this.rdService = rdService;
	}

}