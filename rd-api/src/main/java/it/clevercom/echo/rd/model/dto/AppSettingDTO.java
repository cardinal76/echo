package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"created","updated","userupdate","active"})
public class AppSettingDTO implements Serializable {
	private static final long serialVersionUID = -300944911879884382L;
	
	private Long idsetting;
	private String key;
	private String value;
	private Date created;
	private Date updated;
	private String userupdate;
	private Boolean active;
	private String group;

	public AppSettingDTO() {
	}

	/**
	 * @param idsetting
	 * @param key
	 * @param value
	 * @param created
	 * @param updated
	 * @param userupdate
	 * @param active
	 * @param group
	 */
	public AppSettingDTO(long idsetting, String key, String value, Date created, Date updated, String userupdate,
			boolean active, String group) {
		super();
		this.idsetting = idsetting;
		this.key = key;
		this.value = value;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
		this.group = group;
	}

	/**
	 * @return the idsetting
	 */
	public Long getIdsetting() {
		return idsetting;
	}

	/**
	 * @param idsetting the idsetting to set
	 */
	public void setIdsetting(Long idsetting) {
		this.idsetting = idsetting;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
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

	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}
}
