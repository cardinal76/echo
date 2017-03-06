package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rd_citizenship database table.
 * 
 */
@Entity
@Table(name="rd_citizenship")
@NamedQuery(name="Citizenship.findAll", query="SELECT c FROM Citizenship c")
public class Citizenship implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idcitizenship;

	private Boolean active;

	private Timestamp created;

	private String description;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to Patient
	@OneToMany(mappedBy="rdCitizenship")
	private List<Patient> rdPatients;

	public Citizenship() {
	}

	public Long getIdcitizenship() {
		return this.idcitizenship;
	}

	public void setIdcitizenship(Long idcitizenship) {
		this.idcitizenship = idcitizenship;
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

	public List<Patient> getRdPatients() {
		return this.rdPatients;
	}

	public void setRdPatients(List<Patient> rdPatients) {
		this.rdPatients = rdPatients;
	}

	public Patient addRdPatient(Patient rdPatient) {
		getRdPatients().add(rdPatient);
		rdPatient.setRdCitizenship(this);

		return rdPatient;
	}

	public Patient removeRdPatient(Patient rdPatient) {
		getRdPatients().remove(rdPatient);
		rdPatient.setRdCitizenship(null);

		return rdPatient;
	}

}