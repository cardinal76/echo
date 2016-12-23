package it.clevercom.echo.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.clevercom.echo.auth.model.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

  public Login findByUsername(String username);

}
