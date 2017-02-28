package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.ModalityType;

public interface IModalityType_rd_Repository extends JpaRepository<ModalityType, Long>, JpaSpecificationExecutor<ModalityType> {

}