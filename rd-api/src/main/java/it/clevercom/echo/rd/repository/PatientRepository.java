package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.rd.model.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}