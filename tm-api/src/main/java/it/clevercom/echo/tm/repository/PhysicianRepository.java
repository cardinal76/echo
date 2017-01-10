package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Physician;

public interface PhysicianRepository extends JpaRepository<Physician, Integer> {

}
