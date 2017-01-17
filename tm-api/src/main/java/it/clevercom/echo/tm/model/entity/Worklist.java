package it.clevercom.echo.tm.model.entity;
// Generated 17-gen-2017 15.09.29 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Worklist generated by hbm2java
 */
@Entity
@Table(name = "worklist", catalog = "tmdw")
public class Worklist implements java.io.Serializable {

	private Integer idWorkList;
	private String username;
	private Date created;
	private Date updated;
	private boolean active;
	private String updateUser;
	private Set<Worktask> worktasks = new HashSet<Worktask>(0);

	public Worklist() {
	}

	public Worklist(String username, Date created, Date updated, boolean active, String updateUser) {
		this.username = username;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
	}

	public Worklist(String username, Date created, Date updated, boolean active, String updateUser,
			Set<Worktask> worktasks) {
		this.username = username;
		this.created = created;
		this.updated = updated;
		this.active = active;
		this.updateUser = updateUser;
		this.worktasks = worktasks;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idWorkList", unique = true, nullable = false)
	public Integer getIdWorkList() {
		return this.idWorkList;
	}

	public void setIdWorkList(Integer idWorkList) {
		this.idWorkList = idWorkList;
	}

	@Column(name = "username", nullable = false, length = 100)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = false, length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name = "updateUser", nullable = false, length = 100)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "worklist")
	public Set<Worktask> getWorktasks() {
		return this.worktasks;
	}

	public void setWorktasks(Set<Worktask> worktasks) {
		this.worktasks = worktasks;
	}

}
