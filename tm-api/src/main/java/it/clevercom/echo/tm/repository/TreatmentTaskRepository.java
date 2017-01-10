package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Treatmenttask;

public interface TreatmentTaskRepository extends JpaRepository<Treatmenttask, Integer> {

}
