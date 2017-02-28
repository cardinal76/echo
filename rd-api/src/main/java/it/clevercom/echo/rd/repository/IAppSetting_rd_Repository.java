package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.AppSetting;

public interface IAppSetting_rd_Repository extends JpaRepository<AppSetting, Long>, JpaSpecificationExecutor<AppSetting> {

}