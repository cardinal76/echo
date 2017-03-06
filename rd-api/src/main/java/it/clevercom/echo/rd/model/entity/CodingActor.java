package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rd_coding_actor database table.
 * 
 */
@Entity
@Table(name="rd_coding_actor")
@NamedQuery(name="CodingActor.findAll", query="SELECT c FROM CodingActor c")
public class CodingActor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idcodingactor;

	private Boolean active;

	private Timestamp created;

	private String name;

	private String updated;

	private String userupdate;

	//bi-directional many-to-one association to PatientCodingActor
	@OneToMany(mappedBy="rdCodingActor")
	private List<PatientCodingActor> rdPatientCodingActors;

	//bi-directional many-to-one association to ServiceCodingActor
	@OneToMany(mappedBy="rdCodingActor")
	private List<ServiceCodingActor> rdServiceCodingActors;

	public CodingActor() {
	}

	public Long getIdcodingactor() {
		return this.idcodingactor;
	}

	public void setIdcodingactor(Long idcodingactor) {
		this.idcodingactor = idcodingactor;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdated() {
		return this.updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getUserupdate() {
		return this.userupdate;
	}

	public void setUserupdate(String userupdate) {
		this.userupdate = userupdate;
	}

	public List<PatientCodingActor> getRdPatientCodingActors() {
		return this.rdPatientCodingActors;
	}

	public void setRdPatientCodingActors(List<PatientCodingActor> rdPatientCodingActors) {
		this.rdPatientCodingActors = rdPatientCodingActors;
	}

	public PatientCodingActor addRdPatientCodingActor(PatientCodingActor rdPatientCodingActor) {
		getRdPatientCodingActors().add(rdPatientCodingActor);
		rdPatientCodingActor.setRdCodingActor(this);

		return rdPatientCodingActor;
	}

	public PatientCodingActor removeRdPatientCodingActor(PatientCodingActor rdPatientCodingActor) {
		getRdPatientCodingActors().remove(rdPatientCodingActor);
		rdPatientCodingActor.setRdCodingActor(null);

		return rdPatientCodingActor;
	}

	public List<ServiceCodingActor> getRdServiceCodingActors() {
		return this.rdServiceCodingActors;
	}

	public void setRdServiceCodingActors(List<ServiceCodingActor> rdServiceCodingActors) {
		this.rdServiceCodingActors = rdServiceCodingActors;
	}

	public ServiceCodingActor addRdServiceCodingActor(ServiceCodingActor rdServiceCodingActor) {
		getRdServiceCodingActors().add(rdServiceCodingActor);
		rdServiceCodingActor.setRdCodingActor(this);

		return rdServiceCodingActor;
	}

	public ServiceCodingActor removeRdServiceCodingActor(ServiceCodingActor rdServiceCodingActor) {
		getRdServiceCodingActors().remove(rdServiceCodingActor);
		rdServiceCodingActor.setRdCodingActor(null);

		return rdServiceCodingActor;
	}

}