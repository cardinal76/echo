package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Treatmenttask;

public interface TmTreatmentTaskRepository extends JpaRepository<Treatmenttask, Integer> {

}
