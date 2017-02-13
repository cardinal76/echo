package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Measurementunit;

public interface TmMeasurementUnitRepository extends JpaRepository<Measurementunit, Integer> {

}
