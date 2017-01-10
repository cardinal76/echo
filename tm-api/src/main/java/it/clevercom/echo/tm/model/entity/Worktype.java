package it.clevercom.echo.tm.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the worktype database table.
 * 
 */
@Entity
@Table(name="worktype")
@NamedQuery(name="Worktype.findAll", query="SELECT w FROM Worktype w")
public class Worktype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idWorkType;

	private Object active;

	private Timestamp created;

	private String description;

	private String name;

	private Timestamp updated;

	private String userUpdate;

	//bi-directional many-to-one association to Worktask
	@OneToMany(mappedBy="worktype")
	private List<Worktask> worktasks;

	public Worktype() {
	}

	public int getIdWorkType() {
		return this.idWorkType;
	}

	public void setIdWorkType(int idWorkType) {
		this.idWorkType = idWorkType;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getUserUpdate() {
		return this.userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	public List<Worktask> getWorktasks() {
		return this.worktasks;
	}

	public void setWorktasks(List<Worktask> worktasks) {
		this.worktasks = worktasks;
	}

	public Worktask addWorktask(Worktask worktask) {
		getWorktasks().add(worktask);
		worktask.setWorktype(this);

		return worktask;
	}

	public Worktask removeWorktask(Worktask worktask) {
		getWorktasks().remove(worktask);
		worktask.setWorktype(null);

		return worktask;
	}

}