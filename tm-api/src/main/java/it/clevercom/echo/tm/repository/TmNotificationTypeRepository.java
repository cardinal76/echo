package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Notificationtype;

public interface TmNotificationTypeRepository extends JpaRepository<Notificationtype, Integer> {

}
