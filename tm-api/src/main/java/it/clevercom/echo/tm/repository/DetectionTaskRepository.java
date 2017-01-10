package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Detectiontask;

public interface DetectionTaskRepository extends JpaRepository<Detectiontask, Integer> {

}
