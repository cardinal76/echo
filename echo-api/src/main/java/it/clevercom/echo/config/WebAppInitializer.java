package it.clevercom.echo.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import it.clevercom.echo.sso.config.DataBase_sso_Config;

/**
 * 
 * @author alx
 * @since 28/12/2016
 * Global spring context bootstrap class
 * 
 */
public class WebAppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {

		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(WebConfig.class, DataBase_sso_Config.class);
		ctx.setServletContext(container);

		// registering spring MVC rest servlet
		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}

}
