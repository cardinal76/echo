package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Country;

public interface TmCountryRepository extends JpaRepository<Country, Integer> {

}
