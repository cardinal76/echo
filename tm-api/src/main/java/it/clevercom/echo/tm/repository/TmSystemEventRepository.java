package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Systemevent;

public interface TmSystemEventRepository extends JpaRepository<Systemevent, Integer> {

}
