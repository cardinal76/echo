package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.clevercom.echo.tm.model.entity.Login;

/**
 * 
 * @author alx
 * @since 28/12/2016
 * Repository for db operations on Login jpa entity
 *
 */
@Repository
public interface LoginRepository1 extends JpaRepository<Login, Long> {

  public Login findByUsername(String username);

}
