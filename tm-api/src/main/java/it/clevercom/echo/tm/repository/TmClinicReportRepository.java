package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.ClinicReport;

public interface TmClinicReportRepository extends JpaRepository<ClinicReport, Integer> {

}
