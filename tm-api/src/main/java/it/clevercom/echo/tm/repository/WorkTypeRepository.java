package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Worktype;

public interface WorkTypeRepository extends JpaRepository<Worktype, Integer>{

}
