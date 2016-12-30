package it.clevercom.echo.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.clevercom.echo.auth.model.dto.request.AuthenticationRequest;
import it.clevercom.echo.auth.model.dto.response.AuthenticationResponse;
import it.clevercom.echo.auth.security.CustomUserDetails;
import it.clevercom.echo.auth.security.TokenUtils;
import it.clevercom.echo.common.exception.model.BadRequestException;
import it.clevercom.echo.common.logging.annotation.Loggable;

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
	private UserDetailsService userDetailsService;


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
		Authentication authentication = this.authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authenticationRequest.getUsername(),
						authenticationRequest.getPassword()
						)
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Reload password post-authentication so we can generate token
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = this.tokenUtils.generateToken(userDetails, device);

		logger.info("User " + authenticationRequest.getUsername() + " successfully authenticated! Returning auth token " + token);
		// Return the token
		return new AuthenticationResponse(token);
	}

	//TODO study in deep token refresh mechanism and add this javadoc
	@Loggable
	@RequestMapping(value = "refresh", method = RequestMethod.GET)
	public @ResponseBody AuthenticationResponse authenticationRequest(HttpServletRequest request) throws BadRequestException {
		String token = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(token);
		CustomUserDetails user = (CustomUserDetails) this.userDetailsService.loadUserByUsername(username);
		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
			String refreshedToken = this.tokenUtils.refreshToken(token);
			return new AuthenticationResponse(refreshedToken);
		} else {
			throw new BadRequestException("Invalid Token! Token cannot be refreshed!");
		}
	}

}
