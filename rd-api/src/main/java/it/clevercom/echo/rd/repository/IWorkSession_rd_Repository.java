package it.clevercom.echo.rd.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.Order;
import it.clevercom.echo.rd.model.entity.WorkSession;

public interface IWorkSession_rd_Repository extends JpaRepository<WorkSession, Long>, JpaSpecificationExecutor<WorkSession> {
	public WorkSession findByOrders(Set<Order> order);
}