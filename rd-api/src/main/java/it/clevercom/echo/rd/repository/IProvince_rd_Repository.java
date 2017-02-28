package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.Province;

public interface IProvince_rd_Repository extends JpaRepository<Province, Long>, JpaSpecificationExecutor<Province> {

}