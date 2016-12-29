package it.clevercom.echo.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

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
