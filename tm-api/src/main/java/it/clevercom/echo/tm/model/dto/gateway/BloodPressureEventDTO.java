package it.clevercom.echo.tm.model.dto.gateway;

import java.io.Serializable;

public class BloodPressureEventDTO extends BaseGatewayEntityDTO implements Serializable {
	private static final long serialVersionUID = -3262759467412065437L;
	
	private Long systolicPressure;
	private Long diastolicPressure;
	private Long arterialPressure;
	private Long pulseRate;
	private Long measureStatus;
	private String uom;
	
	/**
	 * @return the systolicPressure
	 */
	public Long getSystolicPressure() {
		return systolicPressure;
	}
	
	/**
	 * @param systolicPressure the systolicPressure to set
	 */
	public void setSystolicPressure(Long systolicPressure) {
		this.systolicPressure = systolicPressure;
	}
	
	/**
	 * @return the diastolicPressure
	 */
	public Long getDiastolicPressure() {
		return diastolicPressure;
	}
	
	/**
	 * @param diastolicPressure the diastolicPressure to set
	 */
	public void setDiastolicPressure(Long diastolicPressure) {
		this.diastolicPressure = diastolicPressure;
	}
	
	/**
	 * @return the arterialPressure
	 */
	public Long getArterialPressure() {
		return arterialPressure;
	}
	
	/**
	 * @param arterialPressure the arterialPressure to set
	 */
	public void setArterialPressure(Long arterialPressure) {
		this.arterialPressure = arterialPressure;
	}
	
	/**
	 * @return the pulseRate
	 */
	public Long getPulseRate() {
		return pulseRate;
	}
	
	/**
	 * @param pulseRate the pulseRate to set
	 */
	public void setPulseRate(Long pulseRate) {
		this.pulseRate = pulseRate;
	}
	
	/**
	 * @return the measureStatus
	 */
	public Long getMeasureStatus() {
		return measureStatus;
	}
	
	/**
	 * @param measureStatus the measureStatus to set
	 */
	public void setMeasureStatus(Long measureStatus) {
		this.measureStatus = measureStatus;
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
}
