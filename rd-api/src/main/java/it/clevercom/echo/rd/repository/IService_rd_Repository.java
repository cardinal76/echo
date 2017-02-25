package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.Service;

public interface IService_rd_Repository extends JpaRepository<Service, Long>, JpaSpecificationExecutor<Service> {

}