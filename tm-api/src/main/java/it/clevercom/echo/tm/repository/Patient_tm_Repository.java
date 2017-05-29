package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import it.clevercom.echo.tm.model.entity.Patient;

public interface Patient_tm_Repository extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {


}
