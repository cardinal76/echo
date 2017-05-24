package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.tm.model.entity.Maritalstatus;

public interface IMaritalStatus_tm_Repository extends JpaRepository<Maritalstatus, Long>, JpaSpecificationExecutor<Maritalstatus> {

}
