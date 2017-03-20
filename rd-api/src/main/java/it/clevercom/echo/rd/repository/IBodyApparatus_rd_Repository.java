package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.BodyApparatus;

public interface IBodyApparatus_rd_Repository extends JpaRepository<BodyApparatus, Long>, JpaSpecificationExecutor<BodyApparatus> {

}