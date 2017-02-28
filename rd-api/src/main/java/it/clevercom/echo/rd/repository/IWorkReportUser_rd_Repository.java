package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.WorkReportUser;

public interface IWorkReportUser_rd_Repository extends JpaRepository<WorkReportUser, Long>, JpaSpecificationExecutor<WorkReportUser> {

}