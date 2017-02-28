package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.WorkTaskLog;

public interface IWorkTaskLog_rd_Repository extends JpaRepository<WorkTaskLog, Long>, JpaSpecificationExecutor<WorkTaskLog> {

}