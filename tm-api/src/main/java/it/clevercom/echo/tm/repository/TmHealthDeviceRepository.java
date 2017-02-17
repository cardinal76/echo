package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.HealthDevice;

public interface TmHealthDeviceRepository extends JpaRepository<HealthDevice, Integer> {

}
