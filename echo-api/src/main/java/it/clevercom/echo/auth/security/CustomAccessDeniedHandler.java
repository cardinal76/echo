package it.clevercom.echo.auth.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * 
 * @author alx
 * @since 28/12/2016
 * Custom handler overring default {@link AccessDeniedHandler} behavior when an {@link AccessDeniedException} occurs (happens when trying to access a given resource without having the proper grants)
 *
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
		logger.warn("AccessDeniedException occurred :", e);
		httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
		httpServletResponse.getWriter().write("Access Denied!");
	}
}
