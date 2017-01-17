package it.clevercom.echo.sso.security.provider;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import it.clevercom.echo.common.enums.ApplicationEnum;

public class CustomAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 1L;
	private final String principal;
	private String credentials;
	private ApplicationEnum application;
	private String email;
	private Date lastPasswordReset;
	private Boolean active;

	/**
	 * This constructor can be safely used by any code that wishes to create a
	 * <code>CustomAuthenticationToken</code>, as the {@link #isAuthenticated()}
	 * will return <code>false</code>.
	 *
	 */
	public CustomAuthenticationToken(String principal, String credentials, ApplicationEnum application) {
		super(null);
		this.principal = principal;
		this.credentials = credentials;
		this.application = application;
		setAuthenticated(false);
	}

	/**
	 * This constructor should only be used by <code>AuthenticationManager</code> or
	 * <code>AuthenticationProvider</code> implementations that are satisfied with
	 * producing a trusted (i.e. {@link #isAuthenticated()} = <code>true</code>)
	 * authentication token.
	 *
	 * @param principal
	 * @param credentials
	 * @param application
	 * @param email
	 * @param lastPasswordReset
	 * @param active
	 * @param authorities
	 * 
	 */
	public CustomAuthenticationToken(String principal, String credentials, ApplicationEnum application, String email, 
			Date lastPasswordReset, boolean active, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		this.application = application;
		super.setAuthenticated(true); // must use super, as we override
	}

	public String getCredentials() {
		return this.credentials;
	}
	public String getPrincipal() {
		return this.principal;
	}
	public ApplicationEnum getApplication() {
		return this.application;
	}
	public String getEmail() {
		return email;
	}
	public Date getLastPasswordReset() {
		return lastPasswordReset;
	}
	public Boolean isActive() {
		return active;
	}

	
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		if (isAuthenticated) {
			throw new IllegalArgumentException(
					"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		}

		super.setAuthenticated(false);
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		credentials = null;
	}

}
