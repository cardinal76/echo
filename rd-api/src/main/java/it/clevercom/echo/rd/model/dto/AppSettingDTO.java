package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.clevercom.echo.common.dto.AbstractEchoDTO;

@JsonIgnoreProperties({"created","updated","userupdate","active", "idd"})
public class AppSettingDTO extends AbstractEchoDTO implements Serializable {
	private static final long serialVersionUID = -300944911879884382L;
	
	private Long idappsetting;
	private String key;
	private String value;
	private Date created;
	private Date updated;
	private String userupdate;
	private Boolean active;
	private String feature;
	private String user;
	
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
	public AppSettingDTO(long idappsetting, String key, String value, Date created, Date updated, String userupdate,
			boolean active, String feature) {
		super();
		this.idappsetting = idappsetting;
		this.key = key;
		this.value = value;
		this.created = created;
		this.updated = updated;
		this.userupdate = userupdate;
		this.active = active;
		this.feature = feature;
	}

	/**
	 * @return the idappsetting
	 */
	public Long getIdappsetting() {
		return idappsetting;
	}

	/**
	 * @param idappsetting the idappsetting to set
	 */
	public void setIdappsetting(Long idappsetting) {
		this.idappsetting = idappsetting;
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
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * @return the feature
	 */
	public String getFeature() {
		return feature;
	}

	/**
	 * @param feature the feature to set
	 */
	public void setFeature(String feature) {
		this.feature = feature;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public Object getIdd() {
		return getIdappsetting();
	}

}
