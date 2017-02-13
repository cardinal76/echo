package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Measurementtype;

public interface TmMeasurementTypeRepository extends JpaRepository<Measurementtype, Integer> {

}
