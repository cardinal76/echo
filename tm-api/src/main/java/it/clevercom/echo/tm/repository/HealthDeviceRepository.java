package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Healthdevice;

public interface HealthDeviceRepository extends JpaRepository<Healthdevice, Integer> {

}
