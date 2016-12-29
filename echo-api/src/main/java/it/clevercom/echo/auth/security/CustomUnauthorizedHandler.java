package it.clevercom.echo.auth.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 
 * @author alx
 * @since 28/12/2016
 * Custom handler overring default {@link AuthenticationEntryPoint} behavior when an {@link AuthenticationException} occurs (happens when trying to authenticate with wrong credentials)
 *
 */
@Component
public class CustomUnauthorizedHandler implements AuthenticationEntryPoint {
	
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		logger.warn("AuthenticationException occurred :", e);
		httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		httpServletResponse.getWriter().write("Authentication failed!");
	}

}
