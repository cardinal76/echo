package it.clevercom.echo.tm.repository.gateway;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.gateway.BatteryLevelEvent;

/**
 * @author gfares
 * @since 0.1
 * 
 *        Jpa repository to insert battery level events received by the gateway regarding a device
 */
public interface BatteryLevel_tm_Repository extends JpaRepository<BatteryLevelEvent, Long> {

}
