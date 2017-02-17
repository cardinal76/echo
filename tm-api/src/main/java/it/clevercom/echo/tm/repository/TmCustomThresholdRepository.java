package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.CustomThreshold;

public interface TmCustomThresholdRepository extends JpaRepository<CustomThreshold, Integer> {

}
