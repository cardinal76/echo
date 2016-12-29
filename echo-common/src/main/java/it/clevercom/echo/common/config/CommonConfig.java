package it.clevercom.echo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import it.clevercom.echo.common.logging.aspect.MethodLogger;

/**
 * 
 * @author alx
 * @since 28/12/2016
 * Common configuration detected in main echo-api war
 */
@Configuration
@EnableAspectJAutoProxy // enables aspects detection
public class CommonConfig {

	@Bean // custom AspectJ logging service
	public MethodLogger methodLogger() {
		return new MethodLogger();
	}
}
