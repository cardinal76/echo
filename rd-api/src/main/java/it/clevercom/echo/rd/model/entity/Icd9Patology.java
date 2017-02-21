package it.clevercom.echo.rd.model.entity;
// Generated 21-feb-2017 16.05.29 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Icd9Patology generated by hbm2java
 */
@Entity
@Table(name = "icd9_patology")
public class Icd9Patology implements java.io.Serializable {

	private long idicd9patology;
	private Icd9PatologyGroup icd9PatologyGroup;
	private String code;
	private String description;
	private Date created;
	private Date updated;
	private String userupdate;
	private boolean active;

	public Icd9Patology() {
	}

	public Icd9Patology(long idicd9patology, String code, String description, Date created, Date updated,
			String userupdate, boolean active) {
		this.idicd9patology = idicd9patology;
		this.code = code;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
	}

	public Icd9Patology(long idicd9patology, Icd9PatologyGroup icd9PatologyGroup, String code, String description,
			Date created, Date updated, String userupdate, boolean active) {
		this.idicd9patology = idicd9patology;
		this.icd9PatologyGroup = icd9PatologyGroup;
		this.code = code;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
	}

	@Id

	@Column(name = "idicd9patology", unique = true, nullable = false)
	public long getIdicd9patology() {
		return this.idicd9patology;
	}

	public void setIdicd9patology(long idicd9patology) {
		this.idicd9patology = idicd9patology;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codeicd9group")
	public Icd9PatologyGroup getIcd9PatologyGroup() {
		return this.icd9PatologyGroup;
	}

	public void setIcd9PatologyGroup(Icd9PatologyGroup icd9PatologyGroup) {
		this.icd9PatologyGroup = icd9PatologyGroup;
	}

	@Column(name = "code", nullable = false, length = 16)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, length = 29)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = false, length = 29)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Column(name = "userupdate", nullable = false, length = 100)
	public String getUserupdate() {
		return this.userupdate;
	}

	public void setUserupdate(String userupdate) {
		this.userupdate = userupdate;
	}

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
