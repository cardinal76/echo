package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.DetectionPlan;

public interface TmDetectionPlanRepository extends JpaRepository<DetectionPlan, Integer> {

}
