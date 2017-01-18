package it.clevercom.echo.sso.model.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import it.clevercom.echo.common.enums.ApplicationEnum;

/**
 * 
 * @author alx
 * @since 28/12/2016
 * Inbound auth json object containing usr, pwd and caller application code
 * 
 */
public class AuthenticationRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull
	private String username;
	@NotNull
	private String password;
	@NotNull
	private ApplicationEnum application;

	public AuthenticationRequest() {
		super();
	}

	public AuthenticationRequest(String username, String password, ApplicationEnum application) {
		this.setUsername(username);
		this.setPassword(password);
		this.setApplication(application);
	}

	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ApplicationEnum getApplication() {
		return application;
	}
	public void setApplication(ApplicationEnum application) {
		this.application = application;
	}

	@Override
	public String toString() {
		return "AuthenticationRequest [username=" + username + ", password=" + password + ", application=" + application
				+ "]";
	}
}
