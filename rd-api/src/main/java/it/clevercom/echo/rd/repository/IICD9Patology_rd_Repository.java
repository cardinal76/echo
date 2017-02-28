package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.Icd9Patology;

public interface IICD9Patology_rd_Repository extends JpaRepository<Icd9Patology, Long>, JpaSpecificationExecutor<Icd9Patology> {

}