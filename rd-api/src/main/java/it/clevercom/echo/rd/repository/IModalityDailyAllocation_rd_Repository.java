package it.clevercom.echo.rd.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import it.clevercom.echo.rd.model.entity.Modality;
import it.clevercom.echo.rd.model.entity.ModalityDailyAllocation;

public interface IModalityDailyAllocation_rd_Repository extends JpaRepository<ModalityDailyAllocation, Long>, JpaSpecificationExecutor<ModalityDailyAllocation> {

	public ModalityDailyAllocation findByModalityAndDay(Modality modality, Date day);

//	@Query(value = "select new com.path.to.class.SurveyAnswerStatistics(v.answer, count(v)) from Survey v group by v.answer")
//	List<SurveyAnswerStatistics> findSurveyCount();
}