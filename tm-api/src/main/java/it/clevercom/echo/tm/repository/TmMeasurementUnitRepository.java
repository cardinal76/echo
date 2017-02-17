package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.MeasurementUnit;

public interface TmMeasurementUnitRepository extends JpaRepository<MeasurementUnit, Integer> {

}
