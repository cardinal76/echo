package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rd_icd9_patology_group database table.
 * 
 */
@Entity
@Table(name="rd_icd9_patology_group")
@NamedQuery(name="Icd9PatologyGroup.findAll", query="SELECT i FROM Icd9PatologyGroup i")
public class Icd9PatologyGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String codeicd9group;

	private Boolean active;

	private Timestamp created;

	private String description;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to Icd9Patology
	@OneToMany(mappedBy="rdIcd9PatologyGroup")
	private List<Icd9Patology> rdIcd9Patologies;

	public Icd9PatologyGroup() {
	}

	public String getCodeicd9group() {
		return this.codeicd9group;
	}

	public void setCodeicd9group(String codeicd9group) {
		this.codeicd9group = codeicd9group;
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

	public List<Icd9Patology> getRdIcd9Patologies() {
		return this.rdIcd9Patologies;
	}

	public void setRdIcd9Patologies(List<Icd9Patology> rdIcd9Patologies) {
		this.rdIcd9Patologies = rdIcd9Patologies;
	}

	public Icd9Patology addRdIcd9Patology(Icd9Patology rdIcd9Patology) {
		getRdIcd9Patologies().add(rdIcd9Patology);
		rdIcd9Patology.setRdIcd9PatologyGroup(this);

		return rdIcd9Patology;
	}

	public Icd9Patology removeRdIcd9Patology(Icd9Patology rdIcd9Patology) {
		getRdIcd9Patologies().remove(rdIcd9Patology);
		rdIcd9Patology.setRdIcd9PatologyGroup(null);

		return rdIcd9Patology;
	}

}