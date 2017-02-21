package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.rd.model.entity.AppSetting;
import it.clevercom.echo.rd.model.entity.WorkTaskLog;

public interface IWorkTaskLog_rd_Repository extends JpaRepository<WorkTaskLog, Integer> {

}