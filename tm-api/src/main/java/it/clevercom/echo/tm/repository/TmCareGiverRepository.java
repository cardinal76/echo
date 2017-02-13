package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Caregiver;

public interface TmCareGiverRepository extends JpaRepository<Caregiver, Integer> {

}