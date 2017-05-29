package it.clevercom.echo.rd.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.VModalityAllocation;

public interface IVModalityAllocation_rd_Repository extends JpaRepository<VModalityAllocation, String>, JpaSpecificationExecutor<VModalityAllocation> {
	/**
	 * 
	 * @param idmodality
	 * @param from
	 * @param to
	 * @return
	 */
	public List<VModalityAllocation> findByIdmodalityAndScheduledateBetween(Long idmodality, Date from, Date to);
}
