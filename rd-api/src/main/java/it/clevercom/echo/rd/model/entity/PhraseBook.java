package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rd_phrase_book database table.
 * 
 */
@Entity
@Table(name="rd_phrase_book")
@NamedQuery(name="PhraseBook.findAll", query="SELECT p FROM PhraseBook p")
public class PhraseBook implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idphrasebook;

	private Boolean active;

	private String body;

	private Timestamp created;

	private String title;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="iduser")
	private User rdUser;

	public PhraseBook() {
	}

	public Long getIdphrasebook() {
		return this.idphrasebook;
	}

	public void setIdphrasebook(Long idphrasebook) {
		this.idphrasebook = idphrasebook;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public User getRdUser() {
		return this.rdUser;
	}

	public void setRdUser(User rdUser) {
		this.rdUser = rdUser;
	}

}