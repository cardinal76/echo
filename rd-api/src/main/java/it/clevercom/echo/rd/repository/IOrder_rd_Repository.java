package it.clevercom.echo.rd.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.Order;
import it.clevercom.echo.rd.model.entity.WorkStatus;

public interface IOrder_rd_Repository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
	/**
	 * @param status
	 * @param request
	 * @return
	 */
	public List<Order> findByWorkStatus(WorkStatus status, Pageable request);
	
	/**
	 * @param status
	 * @return
	 */
	public Long countByWorkStatus(WorkStatus status);
	
	/**
	 * @param from
	 * @param to
	 * @param request
	 * @return
	 */
	public Page<Order> findByCreationdateBetween (Date from, Date to, Pageable request);
	
	/**
	 * @param from
	 * @param to
	 * @return
	 */
	public Long countByCreationdateBetween (Date from, Date to);
	
//	/**
//	 * @param from
//	 * @param to
//	 * @param request
//	 * @return
//	 */
//	public Page<Order> findByCreationdateBetween (Date from, Date to, Specification<Order> specification, Pageable request);
//	
//	/**
//	 * @param from
//	 * @param to
//	 * @return
//	 */
//	public Long countByCreationdateBetween (Date from, Date to, Specification<Order> specification);
	
	/**
	 * @param from
	 * @param to
	 * @param request
	 * @return
	 */
	public Page<Order> findByCreationdateBetweenAndWorkStatus (Date from, Date to, WorkStatus status, Pageable request);
	
	/**
	 * @param from
	 * @param to
	 * @return
	 */
	public Long countByCreationdateBetweenAndWorkStatus (Date from, Date to, WorkStatus status);
	
//	/**
//	 * @param from
//	 * @param to
//	 * @param request
//	 * @return
//	 */
//	public Page<Order> findByCreationdateBetweenAndWorkStatus (Date from, Date to, WorkStatus status, Specification<Order> specification, Pageable request);
//	
//	/**
//	 * @param from
//	 * @param to
//	 * @return
//	 */
//	public Long countByCreationdateBetweenAndWorkStatus (Date from, Date to, WorkStatus status, Specification<Order> specification);
}