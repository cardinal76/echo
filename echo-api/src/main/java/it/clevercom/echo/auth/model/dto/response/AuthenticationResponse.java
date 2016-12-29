package it.clevercom.echo.auth.model.dto.response;

import java.io.Serializable;

/**
 * 
 * @author alx
 * @since 28/12/2016
 * Outbound auth json object containing jwt token to access protected APIs
 *
 */
public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String token;

	public AuthenticationResponse() {
		super();
	}

	public AuthenticationResponse(String token) {
		this.setToken(token);
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "AuthenticationResponse [token=" + token + "]";
	}
}
