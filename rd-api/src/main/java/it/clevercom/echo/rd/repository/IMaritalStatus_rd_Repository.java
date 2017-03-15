package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.Maritalstatus;

public interface IMaritalStatus_rd_Repository extends JpaRepository<Maritalstatus, Long>, JpaSpecificationExecutor<Maritalstatus> {

}
