package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.Hl7InboundMessage;

public interface IHl7InboundMessage_rd_Repository extends JpaRepository<Hl7InboundMessage, Long>, JpaSpecificationExecutor<Hl7InboundMessage> {

}
