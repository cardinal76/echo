package it.clevercom.echo.initializer;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import it.clevercom.echo.config.SecurityConfig;

/**
 * 
 * @author alx
 * @since 28/12/2016
 * Global spring-security context bootstrap class
 * 
 */
public class SecurityAppInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityAppInitializer() {
		super(SecurityConfig.class);
	}

}
