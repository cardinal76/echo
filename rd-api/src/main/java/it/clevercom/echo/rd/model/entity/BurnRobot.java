package it.clevercom.echo.rd.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rd_burn_robot database table.
 * 
 */
@Entity
@Table(name="rd_burn_robot")
@NamedQuery(name="BurnRobot.findAll", query="SELECT b FROM BurnRobot b")
public class BurnRobot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idburnrobot;

	private Boolean active;

	private Timestamp created;

	private String hostname;

	private String ipaddress;

	private String name;

	private Timestamp updated;

	private String uri;

	private String userupdate;

	//bi-directional many-to-one association to OrganizationUnit
	@ManyToOne
	@JoinColumn(name="idorganizationunit")
	private OrganizationUnit rdOrganizationUnit;

	public BurnRobot() {
	}

	public Long getIdburnrobot() {
		return this.idburnrobot;
	}

	public void setIdburnrobot(Long idburnrobot) {
		this.idburnrobot = idburnrobot;
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

	public String getHostname() {
		return this.hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getIpaddress() {
		return this.ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
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

	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getUserupdate() {
		return this.userupdate;
	}

	public void setUserupdate(String userupdate) {
		this.userupdate = userupdate;
	}

	public OrganizationUnit getRdOrganizationUnit() {
		return this.rdOrganizationUnit;
	}

	public void setRdOrganizationUnit(OrganizationUnit rdOrganizationUnit) {
		this.rdOrganizationUnit = rdOrganizationUnit;
	}

}