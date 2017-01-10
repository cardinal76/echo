package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Healthkit;

public interface HealthKitRepository extends JpaRepository<Healthkit, Integer> {

}
