package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Worklist;

public interface TmWorkListRepository extends JpaRepository<Worklist, Integer> {

}
