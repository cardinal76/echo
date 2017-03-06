package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rd_work_report_user database table.
 * 
 */
@Entity
@Table(name="rd_work_report_user")
@NamedQuery(name="WorkReportUser.findAll", query="SELECT w FROM WorkReportUser w")
public class WorkReportUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idworkreportuser;

	private Boolean active;

	private Timestamp created;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="iduser")
	private User rdUser;

	//bi-directional many-to-one association to WorkReport
	@ManyToOne
	@JoinColumn(name="idworkreport")
	private WorkReport rdWorkReport;

	public WorkReportUser() {
	}

	public Long getIdworkreportuser() {
		return this.idworkreportuser;
	}

	public void setIdworkreportuser(Long idworkreportuser) {
		this.idworkreportuser = idworkreportuser;
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

	public WorkReport getRdWorkReport() {
		return this.rdWorkReport;
	}

	public void setRdWorkReport(WorkReport rdWorkReport) {
		this.rdWorkReport = rdWorkReport;
	}

}