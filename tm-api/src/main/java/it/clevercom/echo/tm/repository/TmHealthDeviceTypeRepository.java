package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.HealthDevicetype;

public interface TmHealthDeviceTypeRepository extends JpaRepository<HealthDevicetype, Integer> {

}
