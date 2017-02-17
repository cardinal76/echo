package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.DetectionType;

public interface TmDetectionTypeRepository extends JpaRepository<DetectionType, Integer> {

}
