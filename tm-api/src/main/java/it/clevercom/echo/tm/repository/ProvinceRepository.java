package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Province;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {

}
