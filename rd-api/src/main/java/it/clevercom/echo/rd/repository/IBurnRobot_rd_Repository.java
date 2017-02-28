package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.BurnRobot;

public interface IBurnRobot_rd_Repository extends JpaRepository<BurnRobot, Long>, JpaSpecificationExecutor<BurnRobot> {

}