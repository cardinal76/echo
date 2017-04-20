package it.clevercom.echo.sso.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import it.clevercom.echo.common.enums.ApplicationEnum;
import it.clevercom.echo.common.util.JwtTokenUtils;
import it.clevercom.echo.sso.model.entity.LoginApplication;
import it.clevercom.echo.sso.repository.LoginApplicationRepository;
import it.clevercom.echo.sso.security.provider.CustomAuthenticationToken;

/**
 * 
 * @author alx
 * @since 28/12/2016
 * Custom filter added to spring-security filters chain. Checks if any jwt token is provided in the request header 
 * and eventually stores security info retrieved from the token into spring-security context, so that they can be 
 * evaluated when forwarding requests to protected controllers.
 * If no jwt token is provided this filter will simply dispatch the request to the following block of the chain, 
 * without storing any security info in the context, and thus ensuring the access only to public APIs.
 * 
 */
public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

	/**
	 * Token key string used in http header
	 */
	@Value("${jwt.token.header}")
	private String tokenHeader;
	
	@Value("${echo.config.development.mode}")
	private String developmentMode;
	
	@Value("${echo.config.development.user}")
	private String developmentUser;
	
	@Value("${echo.config.development.application}")
	private String developmentApp;

	@Autowired
	private JwtTokenUtils tokenUtils;

	@Autowired
	private LoginApplicationRepository loginApplicationRepository;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authToken = httpRequest.getHeader(this.tokenHeader);
		
		if (developmentMode.equals("true")) {
			if (developmentUser != null && developmentApp != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				LoginApplication appLogin = loginApplicationRepository.findByAppcodeAndUsername(developmentApp, developmentUser);
				
				CustomAuthenticationToken authenticationToken = new CustomAuthenticationToken(
							appLogin.getLogin().getUsername(),
							appLogin.getLogin().getPassword(),
							ApplicationEnum.getByCode(appLogin.getApplication().getCode()),
							appLogin.getLogin().getEmail(),
							appLogin.getLogin().getLastpasswordreset(),
							appLogin.getLogin().isActive(),
							AuthorityUtils.commaSeparatedStringToAuthorityList(appLogin.getAuthorities()));

				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		} else {
			String username = this.tokenUtils.getUsernameFromToken(authToken);
			String applicationCode = this.tokenUtils.getIssuerFromToken(authToken);
			
			if (username != null && applicationCode != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				LoginApplication appLogin = loginApplicationRepository.findByAppcodeAndUsername(applicationCode, username);
				
				if (this.tokenUtils.validateToken(authToken, appLogin.getLogin().getUsername(), appLogin.getApplication().getCode(), appLogin.getLogin().getLastpasswordreset())) {
					
					CustomAuthenticationToken authenticationToken = new CustomAuthenticationToken(
							appLogin.getLogin().getUsername(),
							appLogin.getLogin().getPassword(),
							ApplicationEnum.getByCode(appLogin.getApplication().getCode()),
							appLogin.getLogin().getEmail(),
							appLogin.getLogin().getLastpasswordreset(),
							appLogin.getLogin().isActive(),
							AuthorityUtils.commaSeparatedStringToAuthorityList(appLogin.getAuthorities()));

					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			}
		}

		chain.doFilter(request, response);
	}

}
