package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.ServiceCodingActor;
import it.clevercom.echo.rd.model.entity.ServiceCodingActorId;

public interface IServiceCodingActor_rd_Repository extends JpaRepository<ServiceCodingActor, ServiceCodingActorId>, JpaSpecificationExecutor<ServiceCodingActor> {

}