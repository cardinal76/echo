package it.clevercom.echo.rd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.Country;
import it.clevercom.echo.rd.model.entity.Province;
import it.clevercom.echo.rd.model.entity.Region;

public interface IProvince_rd_Repository extends JpaRepository<Province, Long>, JpaSpecificationExecutor<Province> {
	public List<Province> findByRegion(Region region);
}