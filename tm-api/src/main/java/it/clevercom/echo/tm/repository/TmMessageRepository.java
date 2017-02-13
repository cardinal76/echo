package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Message;

public interface TmMessageRepository extends JpaRepository<Message, Integer>  {

}
