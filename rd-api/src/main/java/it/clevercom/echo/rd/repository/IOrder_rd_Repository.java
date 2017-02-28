package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.Order;

public interface IOrder_rd_Repository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

}