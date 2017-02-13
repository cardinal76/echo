package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Notification;

public interface TmNotificationRepository extends JpaRepository<Notification, Integer> {

}
