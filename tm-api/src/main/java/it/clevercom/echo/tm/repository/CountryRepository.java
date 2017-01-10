package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

}
