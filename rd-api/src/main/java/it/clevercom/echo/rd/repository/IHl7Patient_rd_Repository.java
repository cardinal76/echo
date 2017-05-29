package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.Hl7Patient;

public interface IHl7Patient_rd_Repository extends JpaRepository<Hl7Patient, Long>, JpaSpecificationExecutor<Hl7Patient> {

}
