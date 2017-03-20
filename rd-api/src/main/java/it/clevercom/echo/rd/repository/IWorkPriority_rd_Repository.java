package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.WorkPriority;

public interface IWorkPriority_rd_Repository extends JpaRepository<WorkPriority, Long>, JpaSpecificationExecutor<WorkPriority> {

}