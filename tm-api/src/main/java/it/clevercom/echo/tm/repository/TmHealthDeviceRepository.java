package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Healthdevice;

public interface TmHealthDeviceRepository extends JpaRepository<Healthdevice, Integer> {

}
