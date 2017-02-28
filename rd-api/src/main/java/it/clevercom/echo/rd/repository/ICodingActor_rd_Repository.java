package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.CodingActor;

public interface ICodingActor_rd_Repository extends JpaRepository<CodingActor, Long>, JpaSpecificationExecutor<CodingActor> {

}