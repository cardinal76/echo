package it.clevercom.echo.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.clevercom.echo.auth.model.entity.EchoUser;

/**
 * 
 * @author alx
 * @since 28/12/2016
 * Repository for db operations on Login jpa entity
 *
 */
@Repository
public interface LoginRepository extends JpaRepository<EchoUser, Long> {

  public EchoUser findByUsername(String username);

}
