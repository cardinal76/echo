package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.TreatmentTask;

public interface TmTreatmentTaskRepository extends JpaRepository<TreatmentTask, Integer> {

}
