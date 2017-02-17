package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.MeetingRequest;

public interface TmMeetingRequestRepository extends JpaRepository<MeetingRequest, Integer> {

}
