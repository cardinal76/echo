package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the worklist database table.
 * 
 */
@Entity
@Table(name="worklist")
@NamedQuery(name="Worklist.findAll", query="SELECT w FROM Worklist w")
public class Worklist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idWorkList;

	private Object active;

	private Timestamp created;

	private Timestamp updated;

	private String updateUser;

	//bi-directional many-to-one association to Worktask
	@OneToMany(mappedBy="worklist")
	private List<Worktask> worktasks;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="idLogin")
	private Login login;

	public Worklist() {
	}

	public int getIdWorkList() {
		return this.idWorkList;
	}

	public void setIdWorkList(int idWorkList) {
		this.idWorkList = idWorkList;
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

	public List<Worktask> getWorktasks() {
		return this.worktasks;
	}

	public void setWorktasks(List<Worktask> worktasks) {
		this.worktasks = worktasks;
	}

	public Worktask addWorktask(Worktask worktask) {
		getWorktasks().add(worktask);
		worktask.setWorklist(this);

		return worktask;
	}

	public Worktask removeWorktask(Worktask worktask) {
		getWorktasks().remove(worktask);
		worktask.setWorklist(null);

		return worktask;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}