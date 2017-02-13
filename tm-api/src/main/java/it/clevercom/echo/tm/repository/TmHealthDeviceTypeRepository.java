package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Healthdevicetype;

public interface TmHealthDeviceTypeRepository extends JpaRepository<Healthdevicetype, Integer> {

}
