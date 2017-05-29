package it.clevercom.echo.tm.model.dto.gateway;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"created","updated","userupdate","active", "idd"})
public class WeightEventDTO extends BaseGatewayEntityDTO implements Serializable {
	private static final long serialVersionUID = 2991166584587824554L;
	
	private Long idweight;
	private Long bmi;
	private Double weight;
	private Double height;
	private String uom;
	private String deviceType= "we";
	private Date created;
	private Date updated;
	private String userupdate;
	private Boolean active;
	
	/**
	 * @return the idweight
	 */
	public Long getIdweight() {
		return idweight;
	}

	/**
	 * @param idweight the idweight to set
	 */
	public void setIdweight(Long idweight) {
		this.idweight = idweight;
	}

	/**
	 * @return the bmi
	 */
	public Long getBmi() {
		return bmi;
	}
	
	/**
	 * @param bmi the bmi to set
	 */
	public void setBmi(Long bmi) {
		this.bmi = bmi;
	}
	
	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}
	
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	/**
	 * @return the height
	 */
	public Double getHeight() {
		return height;
	}
	
	/**
	 * @param height the height to set
	 */
	public void setHeight(Double height) {
		this.height = height;
	}
	
	/**
	 * @return the uom
	 */
	public String getUom() {
		return uom;
	}
	
	/**
	 * @param uom the uom to set
	 */
	public void setUom(String uom) {
		this.uom = uom;
	}

	/**
	 * @return the deviceType
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = this.deviceType;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
}
