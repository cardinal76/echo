package it.clevercom.echo.tm.model.dto.gateway;

import java.io.Serializable;

public class BatteryLevelEventDTO extends BaseGatewayEntityDTO implements Serializable {
	private static final long serialVersionUID = 4877366740829193820L;
	
	private Integer batteryLevel;

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

	@Override
	public Object getIdd() {
		return super.getIdd();
	}
}
