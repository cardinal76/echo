package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.WorkTask;

public interface IWorkTask_rd_Repository extends JpaRepository<WorkTask, Long>, JpaSpecificationExecutor<WorkTask> {

}