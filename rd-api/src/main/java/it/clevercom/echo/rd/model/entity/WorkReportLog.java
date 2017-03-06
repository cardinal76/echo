package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rd_work_report_log database table.
 * 
 */
@Entity
@Table(name="rd_work_report_log")
@NamedQuery(name="WorkReportLog.findAll", query="SELECT w FROM WorkReportLog w")
public class WorkReportLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idworkreportlog;

	private Long accessionnumber;

	private Boolean active;

	private String body;

	private Timestamp completiondate;

	private Timestamp created;

	private Timestamp creationdate;

	private Long idworksession;

	private Long idworktask;

	private String statuscode;

	private Timestamp updated;

	private String userupdate;

	//bi-directional many-to-one association to WorkReport
	@ManyToOne
	@JoinColumn(name="idworkreport")
	private WorkReport rdWorkReport;

	public WorkReportLog() {
	}

	public Long getIdworkreportlog() {
		return this.idworkreportlog;
	}

	public void setIdworkreportlog(Long idworkreportlog) {
		this.idworkreportlog = idworkreportlog;
	}

	public Long getAccessionnumber() {
		return this.accessionnumber;
	}

	public void setAccessionnumber(Long accessionnumber) {
		this.accessionnumber = accessionnumber;
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

	public Timestamp getCompletiondate() {
		return this.completiondate;
	}

	public void setCompletiondate(Timestamp completiondate) {
		this.completiondate = completiondate;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getCreationdate() {
		return this.creationdate;
	}

	public void setCreationdate(Timestamp creationdate) {
		this.creationdate = creationdate;
	}

	public Long getIdworksession() {
		return this.idworksession;
	}

	public void setIdworksession(Long idworksession) {
		this.idworksession = idworksession;
	}

	public Long getIdworktask() {
		return this.idworktask;
	}

	public void setIdworktask(Long idworktask) {
		this.idworktask = idworktask;
	}

	public String getStatuscode() {
		return this.statuscode;
	}

	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
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

	public WorkReport getRdWorkReport() {
		return this.rdWorkReport;
	}

	public void setRdWorkReport(WorkReport rdWorkReport) {
		this.rdWorkReport = rdWorkReport;
	}

}