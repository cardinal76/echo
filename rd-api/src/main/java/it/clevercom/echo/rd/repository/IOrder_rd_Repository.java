package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.rd.model.entity.Order;

public interface IOrder_rd_Repository extends JpaRepository<Order, Integer> {

}