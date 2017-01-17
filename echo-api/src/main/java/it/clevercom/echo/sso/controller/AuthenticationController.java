package it.clevercom.echo.sso.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.clevercom.echo.common.exception.model.BadRequestException;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.sso.model.dto.request.AuthenticationRequest;
import it.clevercom.echo.sso.model.dto.response.AuthenticationResponse;
import it.clevercom.echo.sso.model.entity.LoginApplication;
import it.clevercom.echo.sso.repository.LoginApplicationRepository;
import it.clevercom.echo.sso.security.jwt.TokenUtils;
import it.clevercom.echo.sso.security.provider.CustomAuthenticationToken;

/**
 * 
 * @author alx
 * @since 28/12/2016
 * Controller class exposing public auth-api.
 * 
 */
@RestController
@RequestMapping("auth")
public class AuthenticationController {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Value("${jwt.token.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private LoginApplicationRepository loginApplicationRepository;

	/**
	 * Simple acknowledge test
	 * 
	 * @return Test string
	 */
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test() {
		String testMessage = "ECHO is up and running!";
		logger.info(testMessage);
		return testMessage;
	}

	/**
	 * Handles usr and pwd authentication comparing request values with the ones retrieved from database.
	 * No token is required to invoke this api.
	 * 
	 * @param authenticationRequest containing usr and pwd
	 * @param device from which the request comes from
	 * @return valid auth token
	 * @throws AuthenticationException in case of bad credentials
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {
		logger.info("Trying to perform authentication for user " + authenticationRequest.getUsername());

		// Perform the authentication
		CustomAuthenticationToken customAuthentication = (CustomAuthenticationToken) this.authenticationManager.authenticate(
				new CustomAuthenticationToken(
						authenticationRequest.getUsername(),
						authenticationRequest.getPassword(),
						authenticationRequest.getApplication()
						)
				);
		SecurityContextHolder.getContext().setAuthentication(customAuthentication);
		String token = this.tokenUtils.generateToken(customAuthentication, device);

		logger.info("User " + authenticationRequest.getUsername() + " successfully authenticated! Returning auth token " + token);
		return new AuthenticationResponse(token);
	}

	//TODO study in deep token refresh mechanism and add this javadoc
	@Loggable
	@RequestMapping(value = "refresh", method = RequestMethod.GET)
	public @ResponseBody AuthenticationResponse authenticationRequest(HttpServletRequest request) throws BadRequestException {
		String token = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(token);
		String applicationCode = this.tokenUtils.getIssuerFromToken(token);
		
		LoginApplication applogin = loginApplicationRepository.findByAppcodeAndUsername(applicationCode, username);
//		CustomUserDetails user = (CustomUserDetails) this.userDetailsService.loadUserByUsername(username);
		if (this.tokenUtils.canTokenBeRefreshed(token, applogin.getLogin().getLastPasswordReset())) {
			String refreshedToken = this.tokenUtils.refreshToken(token);
			return new AuthenticationResponse(refreshedToken);
		} else {
			throw new BadRequestException("Invalid Token! Token cannot be refreshed!");
		}
	}

}
