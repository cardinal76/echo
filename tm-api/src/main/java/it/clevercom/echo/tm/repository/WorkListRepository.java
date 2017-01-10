package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Worklist;

public interface WorkListRepository extends JpaRepository<Worklist, Integer> {

}
