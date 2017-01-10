package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Municipality;

public interface MunicipalityRepository extends JpaRepository<Municipality, Integer> {

}
