package it.clevercom.echo.tm.model.dto.gateway;

import java.io.Serializable;

public class BatteryLevelEventDTO extends BaseGatewayEntityDTO implements Serializable {
	private static final long serialVersionUID = 4877366740829193820L;
	
	private Integer batteryLevel;
	private String deviceType= "bs";
	
	/**
	 * @return the batteryLevel
	 */
	public Integer getBatteryLevel() {
		return batteryLevel;
	}

	/**
	 * @param batteryLevel the batteryLevel to set
	 */
	public void setBatteryLevel(Integer batteryLevel) {
		this.batteryLevel = batteryLevel;
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
		this.deviceType = deviceType;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public Object getIdd() {
		return super.getIdd();
	}
}
