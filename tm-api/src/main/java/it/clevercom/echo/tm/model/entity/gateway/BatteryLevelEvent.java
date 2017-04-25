package it.clevercom.echo.tm.model.entity.gateway;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author gfares
 * @since 0.1
 * 
 *        This class rapresents data sent from a device regarding battery status
 */

@Entity
@Table(name = "tm_gateway_event_battery")
@DiscriminatorValue(value = "bs")
public class BatteryLevelEvent extends BaseGatewayEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Column(name = "level", nullable = false)
	private Integer batteryLevel;

	/**
	 * @return the battery level
	 */
	public Integer getBatteryLevel() {
		return batteryLevel;
	}

	/**
	 * @param level the battery level to set
	 */
	public void setBatteryLevel(Integer level) {
		this.batteryLevel = level;
	}

	public BatteryLevelEvent() {
	}
	
}
