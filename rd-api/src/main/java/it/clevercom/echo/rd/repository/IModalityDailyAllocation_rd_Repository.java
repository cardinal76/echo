package it.clevercom.echo.rd.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.Modality;
import it.clevercom.echo.rd.model.entity.ModalityDailyAllocation;

public interface IModalityDailyAllocation_rd_Repository extends JpaRepository<ModalityDailyAllocation, Long>, JpaSpecificationExecutor<ModalityDailyAllocation> {

	public ModalityDailyAllocation findByModalityAndDay(Modality modality, Date day);

}