package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Detectionresult;

public interface DetectionResultRepository extends JpaRepository<Detectionresult, Integer> {

}
