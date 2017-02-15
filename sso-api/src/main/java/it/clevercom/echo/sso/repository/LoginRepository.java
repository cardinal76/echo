package it.clevercom.echo.sso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.clevercom.echo.sso.model.entity.Login;

/**
 * 
 * @author alx
 * @since 28/12/2016
 * Repository for db operations on Login jpa entity
 *
 */
@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
	public Login findByUsername(String username);

}
