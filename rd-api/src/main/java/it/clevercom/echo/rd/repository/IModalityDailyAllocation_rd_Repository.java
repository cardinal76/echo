package it.clevercom.echo.rd.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.Modality;
import it.clevercom.echo.rd.model.entity.ModalityDailyAllocation;

public interface IModalityDailyAllocation_rd_Repository extends JpaRepository<ModalityDailyAllocation, Long>, JpaSpecificationExecutor<ModalityDailyAllocation> {

	/**
	 * 
	 * @param modality
	 * @param day
	 * @return
	 */
	public ModalityDailyAllocation findByModalityAndDay(Modality modality, Date day);

	/**
	 * 
	 * @param modality
	 * @param from
	 * @param to
	 * @return
	 */
	public List<ModalityDailyAllocation> findByModalityAndDayBetween(Modality modality, Date from, Date to);

}