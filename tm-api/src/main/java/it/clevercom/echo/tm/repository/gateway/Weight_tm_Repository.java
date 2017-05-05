package it.clevercom.echo.tm.repository.gateway;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.tm.model.entity.gateway.GatewayEventWeight;

/**
 * @author gfares
 * @since 0.1
 * 
 *        Jpa repository to insert battery level events received by the gateway regarding a device
 */
public interface Weight_tm_Repository extends JpaRepository<GatewayEventWeight, Long>, JpaSpecificationExecutor<GatewayEventWeight> {

}
