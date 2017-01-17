package it.clevercom.echo.sso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.clevercom.echo.sso.model.entity.LoginApplication;

/**
 * 
 * @author alx
 * @since 17/01/2017
 * Repository for db operations on LoginApplication jpa entity
 *
 */
@Repository
public interface LoginApplicationRepository extends JpaRepository<LoginApplication, Long> {

	/**
	 * This method returns ONLY the authorities associated to the given USERNAME, for the given APPLICATION
	 * @param applicationCode
	 * @param username
	 * @return
	 */
	@Query("select lApp " +
			"from LoginApplication lApp " +
			"join lApp.login l " +
			"where lApp.application.code = ?1 " +
			"and l.username = ?2")
	public LoginApplication findByAppcodeAndUsername(String applicationCode, String username);

}
