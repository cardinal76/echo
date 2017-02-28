package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.OrganizationUnit;

public interface IOrganizationUnit_rd_Repository extends JpaRepository<OrganizationUnit, Long>, JpaSpecificationExecutor<OrganizationUnit> {

}