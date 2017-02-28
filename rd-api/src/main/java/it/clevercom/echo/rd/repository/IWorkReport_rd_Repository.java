package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.WorkReport;

public interface IWorkReport_rd_Repository extends JpaRepository<WorkReport, Long>, JpaSpecificationExecutor<WorkReport> {

}