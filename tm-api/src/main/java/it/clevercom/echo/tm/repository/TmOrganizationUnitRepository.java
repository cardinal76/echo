package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.OrganizationUnit;

public interface TmOrganizationUnitRepository extends JpaRepository<OrganizationUnit, Integer> {

}
