package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.rd.model.entity.Country;

public interface ICountry_rd_Repository extends JpaRepository<Country, Integer> {

}