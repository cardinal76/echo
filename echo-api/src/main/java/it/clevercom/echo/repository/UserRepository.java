package it.clevercom.echo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.clevercom.echo.model.entity.Login;

@Repository
public interface UserRepository extends JpaRepository<Login, Long> {

  public Login findByUsername(String username);

}
