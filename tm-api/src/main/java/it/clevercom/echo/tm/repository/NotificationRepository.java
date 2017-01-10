package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}
