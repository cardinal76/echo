package it.clevercom.echo.tm.repository.gateway;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.tm.model.entity.gateway.GatewayEvent;
import it.clevercom.echo.tm.model.entity.gateway.GatewayEventBloodPressure;

/**
 * @author gfares
 * @since 0.1
 * 
 *        Jpa repository to insert blood pressure events received by the gateway
 */
public interface BloodPressure_tm_Repository extends JpaRepository<GatewayEventBloodPressure, Long>, JpaSpecificationExecutor<GatewayEventBloodPressure> {

	public GatewayEventBloodPressure findByGatewayEvent (GatewayEvent event);
	
}
