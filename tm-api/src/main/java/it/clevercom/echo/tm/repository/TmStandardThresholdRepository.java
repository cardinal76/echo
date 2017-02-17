package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.StandardThreshold;

public interface TmStandardThresholdRepository extends JpaRepository<StandardThreshold, Integer>{

}
