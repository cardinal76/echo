package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Worktask;

public interface TmWorkTaskRepository extends JpaRepository<Worktask, Integer>{

}
