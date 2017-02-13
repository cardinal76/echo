package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Province;

public interface TmProvinceRepository extends JpaRepository<Province, Integer> {

}
