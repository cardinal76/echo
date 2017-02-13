package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Treatmentplan;

public interface TmTreatmentPlanRepository extends JpaRepository<Treatmentplan, Integer> {

}
