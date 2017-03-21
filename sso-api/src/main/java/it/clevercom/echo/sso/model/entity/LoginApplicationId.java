package it.clevercom.echo.sso.model.entity;
// Generated 17-feb-2017 17.36.09 by Hibernate Tools 5.2.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * LoginApplicationId generated by hbm2java
 */
@Embeddable
public class LoginApplicationId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3451460316992467948L;
	private long applicationIdapplication;
	private long loginIdlogin;

	public LoginApplicationId() {
	}

	public LoginApplicationId(long applicationIdapplication, long loginIdlogin) {
		this.applicationIdapplication = applicationIdapplication;
		this.loginIdlogin = loginIdlogin;
	}

	@Column(name = "application_idapplication", nullable = false)
	public long getApplicationIdapplication() {
		return this.applicationIdapplication;
	}

	public void setApplicationIdapplication(long applicationIdapplication) {
		this.applicationIdapplication = applicationIdapplication;
	}

	@Column(name = "login_idlogin", nullable = false)
	public long getLoginIdlogin() {
		return this.loginIdlogin;
	}

	public void setLoginIdlogin(long loginIdlogin) {
		this.loginIdlogin = loginIdlogin;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LoginApplicationId))
			return false;
		LoginApplicationId castOther = (LoginApplicationId) other;

		return (this.getApplicationIdapplication() == castOther.getApplicationIdapplication())
				&& (this.getLoginIdlogin() == castOther.getLoginIdlogin());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getApplicationIdapplication();
		result = 37 * result + (int) this.getLoginIdlogin();
		return result;
	}

}
