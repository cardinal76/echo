package it.clevercom.echo.model.dto.factory;

import org.springframework.security.core.authority.AuthorityUtils;

import it.clevercom.echo.model.dto.security.CerberusUser;
import it.clevercom.echo.model.entity.Login;

public class CerberusUserFactory {

  public static CerberusUser create(Login user) {
    return new CerberusUser(
      (long) user.getIdLogin(),
      user.getUsername(),
      user.getPassword(),
      user.getEmail(),
      user.getLastPasswordReset(),
      AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities())
    );
  }

}
