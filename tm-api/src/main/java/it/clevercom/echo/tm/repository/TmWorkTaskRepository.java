package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.WorkTask;

public interface TmWorkTaskRepository extends JpaRepository<WorkTask, Integer>{

}
