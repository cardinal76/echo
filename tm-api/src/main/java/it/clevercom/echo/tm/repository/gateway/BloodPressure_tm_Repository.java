package it.clevercom.echo.tm.repository.gateway;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.gateway.BloodPressureEvent;

/**
 * @author gfares
 * @since 0.1
 * 
 *        Jpa repository to insert blood pressure events received by the gateway
 */
public interface BloodPressure_tm_Repository extends JpaRepository<BloodPressureEvent, Long> {

}
