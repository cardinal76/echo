package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.ModalityService;

public interface IModalityService_rd_Repository extends JpaRepository<ModalityService, Long>, JpaSpecificationExecutor<ModalityService> {

}