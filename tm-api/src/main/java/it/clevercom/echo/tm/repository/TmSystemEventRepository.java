package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.SystemEvent;

public interface TmSystemEventRepository extends JpaRepository<SystemEvent, Integer> {

}
