package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.rd.model.entity.OrderLog;

public interface IOrderLog_rd_Repository extends JpaRepository<OrderLog, Integer> {

}