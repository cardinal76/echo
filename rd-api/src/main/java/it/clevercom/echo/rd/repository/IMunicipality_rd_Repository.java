package it.clevercom.echo.rd.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.Municipality;
import it.clevercom.echo.rd.model.entity.Province;

public interface IMunicipality_rd_Repository extends JpaRepository<Municipality, Long>, JpaSpecificationExecutor<Municipality> {
	public List<Municipality> findByProvince(Province province, Sort sort);
}