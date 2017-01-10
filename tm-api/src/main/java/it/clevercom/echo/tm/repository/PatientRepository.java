package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
