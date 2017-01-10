package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Region;

public interface RegionRepository extends JpaRepository<Region, Integer> {

}
