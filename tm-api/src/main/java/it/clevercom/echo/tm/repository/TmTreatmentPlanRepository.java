package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.TreatmentPlan;

public interface TmTreatmentPlanRepository extends JpaRepository<TreatmentPlan, Integer> {

}
