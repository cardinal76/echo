package it.clevercom.echo.rd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.Country;
import it.clevercom.echo.rd.model.entity.Region;

public interface IRegion_rd_Repository extends JpaRepository<Region, Long>, JpaSpecificationExecutor<Region>{
	public List<Region> findByCountry(Country country);
}