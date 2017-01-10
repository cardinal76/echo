package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the specialtytype database table.
 * 
 */
@Entity
@Table(name="specialtytype")
@NamedQuery(name="Specialtytype.findAll", query="SELECT s FROM Specialtytype s")
public class Specialtytype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSpecialtyType;

	private Object active;

	private Timestamp created;

	private String description;

	private String diagnosticOrTherapeutic;

	private String organOrTechnique;

	private String patientAgeRange;

	private String surgicalOrInternal;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-many association to Physician
	@ManyToMany(mappedBy="specialtytypes")
	private List<Physician> physicians;

	//bi-directional many-to-one association to Specialtytype
	@ManyToOne
	@JoinColumn(name="parentId")
	private Specialtytype specialtytype;

	//bi-directional many-to-one association to Specialtytype
	@OneToMany(mappedBy="specialtytype")
	private List<Specialtytype> specialtytypes;

	public Specialtytype() {
	}

	public int getIdSpecialtyType() {
		return this.idSpecialtyType;
	}

	public void setIdSpecialtyType(int idSpecialtyType) {
		this.idSpecialtyType = idSpecialtyType;
	}

	public Object getActive() {
		return this.active;
	}

	public void setActive(Object active) {
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

	public String getDiagnosticOrTherapeutic() {
		return this.diagnosticOrTherapeutic;
	}

	public void setDiagnosticOrTherapeutic(String diagnosticOrTherapeutic) {
		this.diagnosticOrTherapeutic = diagnosticOrTherapeutic;
	}

	public String getOrganOrTechnique() {
		return this.organOrTechnique;
	}

	public void setOrganOrTechnique(String organOrTechnique) {
		this.organOrTechnique = organOrTechnique;
	}

	public String getPatientAgeRange() {
		return this.patientAgeRange;
	}

	public void setPatientAgeRange(String patientAgeRange) {
		this.patientAgeRange = patientAgeRange;
	}

	public String getSurgicalOrInternal() {
		return this.surgicalOrInternal;
	}

	public void setSurgicalOrInternal(String surgicalOrInternal) {
		this.surgicalOrInternal = surgicalOrInternal;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public List<Physician> getPhysicians() {
		return this.physicians;
	}

	public void setPhysicians(List<Physician> physicians) {
		this.physicians = physicians;
	}

	public Specialtytype getSpecialtytype() {
		return this.specialtytype;
	}

	public void setSpecialtytype(Specialtytype specialtytype) {
		this.specialtytype = specialtytype;
	}

	public List<Specialtytype> getSpecialtytypes() {
		return this.specialtytypes;
	}

	public void setSpecialtytypes(List<Specialtytype> specialtytypes) {
		this.specialtytypes = specialtytypes;
	}

	public Specialtytype addSpecialtytype(Specialtytype specialtytype) {
		getSpecialtytypes().add(specialtytype);
		specialtytype.setSpecialtytype(this);

		return specialtytype;
	}

	public Specialtytype removeSpecialtytype(Specialtytype specialtytype) {
		getSpecialtytypes().remove(specialtytype);
		specialtytype.setSpecialtytype(null);

		return specialtytype;
	}

}