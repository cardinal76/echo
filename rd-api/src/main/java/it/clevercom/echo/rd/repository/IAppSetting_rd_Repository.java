package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.rd.model.entity.AppSetting;

public interface IAppSetting_rd_Repository extends JpaRepository<AppSetting, Integer> {

}