package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.tm.model.entity.Citizenship;

public interface ICitizenship_tm_Repository extends JpaRepository<Citizenship, Long>, JpaSpecificationExecutor<Citizenship> {

}
