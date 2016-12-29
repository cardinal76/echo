package it.clevercom.echo.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.clevercom.echo.auth.model.entity.Login;
import it.clevercom.echo.auth.repository.LoginRepository;

/**
 * 
 * @author alx
 * @since 28/12/2016
 * Custom service class overriding default behavior for {@link UserDetailsService}
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Login user = this.loginRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return CustomUserDetailsFactory.create(user);
		}
	}

}
