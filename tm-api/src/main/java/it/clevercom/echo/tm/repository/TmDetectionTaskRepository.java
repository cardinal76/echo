package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.DetectionTask;

public interface TmDetectionTaskRepository extends JpaRepository<DetectionTask, Integer> {

}
