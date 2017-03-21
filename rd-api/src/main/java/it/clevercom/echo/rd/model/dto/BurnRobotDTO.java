package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"created","updated","userupdate","active"})
public class BurnRobotDTO implements Serializable {
	private static final long serialVersionUID = 3123910630712760988L;

	private Long idburnrobot;
	private BaseObjectDTO organizationUnit;
	private String name;
	private String hostname;
	private String ipaddress;
	private String uri;
	private Date created;
	private Date updated;
	private String userupdate;
	private Boolean active;

	public BurnRobotDTO() {
	}

	/**
	 * @return the idburnrobot
	 */
	public Long getIdburnrobot() {
		return idburnrobot;
	}

	/**
	 * @param idburnrobot the idburnrobot to set
	 */
	public void setIdburnrobot(Long idburnrobot) {
		this.idburnrobot = idburnrobot;
	}

	/**
	 * @return the organizationUnit
	 */
	public BaseObjectDTO getOrganizationUnit() {
		return organizationUnit;
	}

	/**
	 * @param organizationUnit the organizationUnit to set
	 */
	public void setOrganizationUnit(BaseObjectDTO organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the hostname
	 */
	public String getHostname() {
		return hostname;
	}

	/**
	 * @param hostname the hostname to set
	 */
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	/**
	 * @return the ipaddress
	 */
	public String getIpaddress() {
		return ipaddress;
	}

	/**
	 * @param ipaddress the ipaddress to set
	 */
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the updated
	 */
	public Date getUpdated() {
		return updated;
	}

	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	/**
	 * @return the userupdate
	 */
	public String getUserupdate() {
		return userupdate;
	}

	/**
	 * @param userupdate the userupdate to set
	 */
	public void setUserupdate(String userupdate) {
		this.userupdate = userupdate;
	}

	/**
	 * @return the active
	 */
	public Boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
