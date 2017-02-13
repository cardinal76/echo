package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Detectionplan;

public interface TmDetectionPlanRepository extends JpaRepository<Detectionplan, Integer> {

}
