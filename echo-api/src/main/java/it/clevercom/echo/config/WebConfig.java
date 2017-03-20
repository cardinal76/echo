package it.clevercom.echo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * @author alx
 * @since 28/12/2016 Global spring web configuration
 *
 */
@Configuration
@EnableWebMvc // enables @RestController, @RequestMapping annotations
@ComponentScan({ "${echo.base.package.sso}", "${echo.base.package.tm}", "${echo.base.package.rd}" })

@PropertySource("classpath:application.properties")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Value("${jwt.token.header}")
	private String tokenHeader;

	@Bean
	public DeviceResolverHandlerInterceptor deviceResolverHandlerInterceptor() {
		return new DeviceResolverHandlerInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(deviceResolverHandlerInterceptor());
		// registry.addInterceptor(new CorsInterceptor());
	}

	@Bean
	public DeviceHandlerMethodArgumentResolver deviceHandlerMethodArgumentResolver() {
		return new DeviceHandlerMethodArgumentResolver();
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(deviceHandlerMethodArgumentResolver());
	}

	 @Override
	 public void addCorsMappings(CorsRegistry registry) {
	 registry.addMapping("/**")
	 // TODO export allowed origins into property file
	 .allowedOrigins("*")
	 .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name())
	 .allowedHeaders("Origin" , "X-Requested-With", "Content-Type", "Accept", tokenHeader)
	 .allowCredentials(false)
	 .maxAge(3600);
	 }
}