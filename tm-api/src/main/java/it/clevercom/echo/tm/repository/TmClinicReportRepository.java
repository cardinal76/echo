package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.Clinicreport;

public interface TmClinicReportRepository extends JpaRepository<Clinicreport, Integer> {

}
