package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>  {

}
