package it.clevercom.echo.sso.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import it.clevercom.echo.common.enums.ApplicationEnum;
import it.clevercom.echo.sso.model.entity.LoginApplication;
import it.clevercom.echo.sso.repository.LoginApplicationRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private LoginApplicationRepository loginApplicationRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		CustomAuthenticationToken castedAuthentication = (CustomAuthenticationToken) authentication;

		LoginApplication appLogin = loginApplicationRepository.findByAppcodeAndUsername(castedAuthentication.getApplication().getCode(), castedAuthentication.getPrincipal());

		if (appLogin == null) {
			throw new UsernameNotFoundException(String.format("Lo user '%s' non ha alcuna utenza registrata per questa applicazione", castedAuthentication.getPrincipal()));
		}

		if (!passwordEncoder.matches(castedAuthentication.getCredentials(), appLogin.getLogin().getPassword())) {
			throw new BadCredentialsException(String.format("Password errata per lo username '%s'.", castedAuthentication.getPrincipal()));
		}

		return new CustomAuthenticationToken(appLogin.getLogin().getUsername(),
				appLogin.getLogin().getPassword(),
				ApplicationEnum.getByCode(appLogin.getApplication().getCode()),
				appLogin.getLogin().getEmail(),
				appLogin.getLogin().getLastPasswordReset(),
				appLogin.getLogin().isActive(),
				AuthorityUtils.commaSeparatedStringToAuthorityList(appLogin.getAuthorities()));
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (CustomAuthenticationToken.class.isAssignableFrom(authentication));
	}

}