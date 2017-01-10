package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Detectionplan;

public interface DetectionPlanRepository extends JpaRepository<Detectionplan, Integer> {

}
