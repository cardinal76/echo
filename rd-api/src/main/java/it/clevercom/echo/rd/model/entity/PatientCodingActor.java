package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rd_patient_coding_actor database table.
 * 
 */
@Entity
@Table(name="rd_patient_coding_actor")
@NamedQuery(name="PatientCodingActor.findAll", query="SELECT p FROM PatientCodingActor p")
public class PatientCodingActor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PatientCodingActorPK id;

	private Boolean active;

	private Timestamp created;

	private String externalcode;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to CodingActor
	@ManyToOne
	@JoinColumn(name="idcodingactor", insertable=false, updatable=false)
	private CodingActor rdCodingActor;

	//bi-directional many-to-one association to Patient
	@ManyToOne
	@JoinColumn(name="idpatient", insertable=false, updatable=false)
	private Patient rdPatient;

	public PatientCodingActor() {
	}

	public PatientCodingActorPK getId() {
		return this.id;
	}

	public void setId(PatientCodingActorPK id) {
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

	public Patient getRdPatient() {
		return this.rdPatient;
	}

	public void setRdPatient(Patient rdPatient) {
		this.rdPatient = rdPatient;
	}

}