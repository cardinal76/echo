package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.Municipality;

public interface IMunicipality_rd_Repository extends JpaRepository<Municipality, Long>, JpaSpecificationExecutor<Municipality> {

}