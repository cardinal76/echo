package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Systemevent;

public interface SystemEventRepository extends JpaRepository<Systemevent, Integer> {

}
