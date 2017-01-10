package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Meetingrequest;

public interface MeetingRequestRepository extends JpaRepository<Meetingrequest, Integer> {

}
