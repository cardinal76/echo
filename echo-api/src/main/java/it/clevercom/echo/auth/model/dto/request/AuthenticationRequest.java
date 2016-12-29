package it.clevercom.echo.auth.model.dto.request;

import java.io.Serializable;

/**
 * 
 * @author alx
 * @since 28/12/2016
 * Inbound auth json object containing usr & pwd
 * 
 */
public class AuthenticationRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	public AuthenticationRequest() {
		super();
	}

	public AuthenticationRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
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

	@Override
	public String toString() {
		return "AuthenticationRequest [username=" + username + ", password=" + password + "]";
	}
}
