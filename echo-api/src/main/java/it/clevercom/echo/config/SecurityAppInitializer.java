package it.clevercom.echo.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityAppInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityAppInitializer() {
		super(SecurityConfig.class);
	}

}
