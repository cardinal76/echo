package it.clevercom.echo.sso.security.provider;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

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

		if (appLogin.getLogin().getAdlogon()==1) {
			// Set up the environment for creating the initial context
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, "ldap://10.40.23.13:389/DC=hsangiovanni,DC=roma,DC=it");
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL, appLogin.getLogin().getUsername());
			env.put(Context.SECURITY_CREDENTIALS, authentication.getCredentials().toString());

			// Create the initial context
			try {
				DirContext ctx = new InitialDirContext(env);
			} catch (Exception e) {
				throw new BadCredentialsException(String.format("L'autenticazione su LDAP non è riuscita. Password errata per lo username '%s'.", castedAuthentication.getPrincipal()));
			}
		} else if (!passwordEncoder.matches(castedAuthentication.getCredentials(), appLogin.getLogin().getPassword())) {
			throw new BadCredentialsException(String.format("Password errata per lo username '%s'.", castedAuthentication.getPrincipal()));
		}

		return new CustomAuthenticationToken(appLogin.getLogin().getUsername(),
				appLogin.getLogin().getPassword(),
				ApplicationEnum.getByCode(appLogin.getApplication().getCode()),
				appLogin.getLogin().getEmail(),
				appLogin.getLogin().getLastpasswordreset(),
				appLogin.getLogin().isActive(),
				AuthorityUtils.commaSeparatedStringToAuthorityList(appLogin.getAuthorities()));
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (CustomAuthenticationToken.class.isAssignableFrom(authentication));
	}

}