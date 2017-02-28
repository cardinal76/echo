package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.WorkReportLog;

public interface IWorkReportLog_rd_Repository extends JpaRepository<WorkReportLog, Long>, JpaSpecificationExecutor<WorkReportLog> {

}