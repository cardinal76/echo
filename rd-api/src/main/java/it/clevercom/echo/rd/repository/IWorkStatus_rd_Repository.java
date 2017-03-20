package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.WorkStatus;

public interface IWorkStatus_rd_Repository extends JpaRepository<WorkStatus, Long>, JpaSpecificationExecutor<WorkStatus> {

}