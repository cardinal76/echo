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

import it.clevercom.echo.auth.model.dto.json.request.AuthenticationRequest;
import it.clevercom.echo.auth.model.dto.json.response.AuthenticationResponse;
import it.clevercom.echo.auth.model.dto.security.LoginDto;
import it.clevercom.echo.auth.security.TokenUtils;
import it.clevercom.echo.exception.model.BadRequestException;

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

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test() throws Exception {
		return "ECHO is up and running!";
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody AuthenticationResponse authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {

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

		// Return the token
		return new AuthenticationResponse(token);
	}

	@RequestMapping(value = "refresh", method = RequestMethod.GET)
	public @ResponseBody AuthenticationResponse authenticationRequest(HttpServletRequest request) throws BadRequestException {
		String token = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(token);
		LoginDto user = (LoginDto) this.userDetailsService.loadUserByUsername(username);
		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
			String refreshedToken = this.tokenUtils.refreshToken(token);
			return new AuthenticationResponse(refreshedToken);
		} else {
			throw new BadRequestException("Invalid Token! Token cannot be refreshed!");
		}
	}

}
