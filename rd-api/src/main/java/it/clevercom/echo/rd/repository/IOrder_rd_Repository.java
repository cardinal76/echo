package it.clevercom.echo.rd.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.Order;
import it.clevercom.echo.rd.model.entity.WorkStatus;

public interface IOrder_rd_Repository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
	public List<Order> findByWorkStatus(WorkStatus status, Pageable request);
	public Long countByWorkStatus(WorkStatus status);
}