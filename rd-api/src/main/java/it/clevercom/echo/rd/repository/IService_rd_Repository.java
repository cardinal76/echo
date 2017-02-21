package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.rd.model.entity.Service;

public interface IService_rd_Repository extends JpaRepository<Service, Integer> {

}