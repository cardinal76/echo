package it.clevercom.echo.tm.model.dto.gateway;

import java.io.Serializable;

public class WeightEventDTO extends BaseGatewayEntityDTO implements Serializable {
	private static final long serialVersionUID = 2991166584587824554L;
	
	private Long idweight;
	private Long bmi;
	private Double weight;
	private Double height;
	private String uom;
	private String deviceType= "we";
	
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
}
