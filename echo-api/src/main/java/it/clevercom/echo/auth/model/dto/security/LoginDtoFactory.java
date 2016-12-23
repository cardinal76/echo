package it.clevercom.echo.auth.model.dto.security;

import org.springframework.security.core.authority.AuthorityUtils;

import it.clevercom.echo.auth.model.entity.Login;

public class LoginDtoFactory {

  public static LoginDto create(Login user) {
    return new LoginDto(
      user.getIdLogin(),
      user.getUsername(),
      user.getPassword(),
      user.getEmail(),
      user.getLastPasswordReset(),
      AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities())
    );
  }

}
