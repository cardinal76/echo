package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Detectionresult;

public interface TmDetectionResultRepository extends JpaRepository<Detectionresult, Integer> {

}
