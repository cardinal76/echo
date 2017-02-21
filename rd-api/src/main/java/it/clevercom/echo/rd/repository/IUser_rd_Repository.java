package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.rd.model.entity.User;

public interface IUser_rd_Repository extends JpaRepository<User, Integer> {

}