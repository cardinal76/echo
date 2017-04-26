package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.Order;
import it.clevercom.echo.rd.model.entity.OrderService;
import it.clevercom.echo.rd.model.entity.Service;

public interface IOrderService_rd_Repository extends JpaRepository<OrderService, Long>, JpaSpecificationExecutor<OrderService>  {
	public OrderService findByOrderAndService(Order order, Service service);
}