package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Nurse;

public interface NurseRepository extends JpaRepository<Nurse, Integer> {

}
