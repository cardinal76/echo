package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Organizationunit;

public interface TmOrganizationUnitRepository extends JpaRepository<Organizationunit, Integer> {

}