package it.clevercom.echo.auth.security;

import org.springframework.security.core.authority.AuthorityUtils;

import it.clevercom.echo.auth.model.entity.Login;

/**
 * 
 * @author alx
 * @since 28/12/2016
 * Factory utility class to build {@link CustomUserDetails} objects
 *
 */
public class CustomUserDetailsFactory {

	public static CustomUserDetails create(Login user) {
		return new CustomUserDetails(
				user.getIdLogin(),
				user.getUsername(),
				user.getPassword(),
				user.getEmail(),
				user.getLastPasswordReset(),
				AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities())
				);
	}

}
