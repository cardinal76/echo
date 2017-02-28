package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.Icd9PatologyGroup;

public interface IICD9PatologyGroup_rd_Repository extends JpaRepository<Icd9PatologyGroup, String>, JpaSpecificationExecutor<Icd9PatologyGroup> {

}