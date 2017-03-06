package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rd_icd9_patology database table.
 * 
 */
@Entity
@Table(name="rd_icd9_patology")
@NamedQuery(name="Icd9Patology.findAll", query="SELECT i FROM Icd9Patology i")
public class Icd9Patology implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idicd9patology;

	private Boolean active;

	private String code;

	private Timestamp created;

	private String description;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to Icd9PatologyGroup
	@ManyToOne
	@JoinColumn(name="codeicd9group")
	private Icd9PatologyGroup rdIcd9PatologyGroup;

	public Icd9Patology() {
	}

	public Long getIdicd9patology() {
		return this.idicd9patology;
	}

	public void setIdicd9patology(Long idicd9patology) {
		this.idicd9patology = idicd9patology;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Icd9PatologyGroup getRdIcd9PatologyGroup() {
		return this.rdIcd9PatologyGroup;
	}

	public void setRdIcd9PatologyGroup(Icd9PatologyGroup rdIcd9PatologyGroup) {
		this.rdIcd9PatologyGroup = rdIcd9PatologyGroup;
	}

}