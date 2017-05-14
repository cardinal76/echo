package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.VModalitytypeAllocation;

public interface IVModalitytypeAllocation_rd_Repository extends JpaRepository<VModalitytypeAllocation, String>, JpaSpecificationExecutor<VModalitytypeAllocation> {

}
