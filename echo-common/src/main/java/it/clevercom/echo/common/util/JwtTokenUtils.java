package it.clevercom.echo.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * @author alx
 * @since 28/12/2016
 * Utility class used to handle operations on jwt tokens.
 *
 */
@Component
public class JwtTokenUtils {

	private final String AUDIENCE_UNKNOWN   = "unknown";
	private final String AUDIENCE_WEB       = "web";
	private final String AUDIENCE_MOBILE    = "mobile";
	private final String AUDIENCE_TABLET    = "tablet";

	/**
	 * secret string used to decode jwt tokens
	 */
	@Value("${jwt.token.secret}")
	private String secret;

	/**
	 * token expiration time in seconds
	 */
	@Value("${jwt.token.expiration}")
	private Long expiration;

	/**
	 * Extracts username plain string from jwt token 
	 * @param token to decode
	 * @return username contained in token
	 */
	public String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	/**
	 * Extracts issuer plain string from jwt token 
	 * @param token to decode
	 * @return issuer (application code) contained in token
	 */
	public String getIssuerFromToken(String token) {
		String username;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			username = claims.getIssuer();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	/**
	 * Extracts plain creation date from jwt token 
	 * @param token to decode
	 * @return token creation date
	 */
	public Date getCreatedDateFromToken(String token) {
		Date created;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			created = new Date((Long) claims.get("created"));
		} catch (Exception e) {
			created = null;
		}
		return created;
	}

	/**
	 * Extracts plain expiration date from jwt token 
	 * @param token to decode
	 * @return token expiration date
	 */
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	/**
	 * Extracts plain audience (caller device type) string  from jwt token 
	 * @param token to decode
	 * @return audience (device) that generated the token
	 */
	public String getAudienceFromToken(String token) {
		String audience;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			audience = (String) claims.get("audience");
		} catch (Exception e) {
			audience = null;
		}
		return audience;
	}

	/**
	 * extracts all Claims json object (containing all user related info {@linkplain}http://self-issued.info/docs/draft-ietf-oauth-json-web-token.html#rfc.section.4 ) from jwt token
	 * @param token to decode
	 * @return complete token claims
	 */
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(this.secret)
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	private Date generateCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + this.expiration * 1000);
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = this.getExpirationDateFromToken(token);
		return expiration.before(this.generateCurrentDate());
	}

	private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
		return (lastPasswordReset != null && created.before(lastPasswordReset));
	}

	private String generateAudience(Device device) {
		String audience = this.AUDIENCE_UNKNOWN;
		if (device.isNormal()) {
			audience = this.AUDIENCE_WEB;
		} else if (device.isTablet()) {
			audience = AUDIENCE_TABLET;
		} else if (device.isMobile()) {
			audience = AUDIENCE_MOBILE;
		}
		return audience;
	}

	private Boolean ignoreTokenExpiration(String token) {
		String audience = this.getAudienceFromToken(token);
		return (this.AUDIENCE_TABLET.equals(audience) || this.AUDIENCE_MOBILE.equals(audience));
	}

	public String generateToken(String username, String commaSeparatedAuthorities, String applicationCode, Device device) {
		Map<String, Object> claims = new HashMap<String, Object>();
		// username claim
		claims.put("sub", username);
		// spring security roles claim
		claims.put("scopes", commaSeparatedAuthorities);
		// application claim (token is generated with roles per application)
		claims.put("iss", applicationCode);
		// device claim
		claims.put("audience", this.generateAudience(device));
		// creation time claim
		claims.put("created", this.generateCurrentDate());
		return this.generateToken(claims);
	}

	private String generateToken(Map<String, Object> claims) {
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(this.generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, this.secret)
				.compact();
	}

	/**
	 * Checks if a given token can be refreshed extending its period of validity
	 * @param token to check
	 * @param lastPasswordReset date of the last pwd reset of the token owner
	 * @return can or cannot be refreshed
	 */
	public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
		final Date created = this.getCreatedDateFromToken(token);
		return (!(this.isCreatedBeforeLastPasswordReset(created, lastPasswordReset)) 
				&& (!(this.isTokenExpired(token)) || this.ignoreTokenExpiration(token)));
	}

	/**
	 * Refreshes an existing token generating a new one with the same claims
	 * @param token to refresh
	 * @return refreshed token
	 */
	//TODO study in deep token refresh mechanism and integrate this javadoc
	public String refreshToken(String token) {
		String refreshedToken;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			claims.put("created", this.generateCurrentDate());
			refreshedToken = this.generateToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}

	/**
	 * Checks the validity of a given token
	 * @param token to check
	 * @param username of the owner of the token
	 * @return is or isn't valid
	 */
	/**
	 * Checks the validity of a given token
	 * @param token to check
	 * @param username must match with the one contained in the token
	 * @param applicationCode must match with the one contained in the token
	 * @param lastPasswordReset must be before token creation date
	 * @return is or isn't valid
	 */
	//TODO study indeep token expiration mechanism and integrate this javadoc
	public Boolean validateToken(String token, String username, String applicationCode, Date lastPasswordReset) {
		final String usernameToValidate = this.getUsernameFromToken(token);
		final String applicationCodeToValidate = this.getIssuerFromToken(token);
		final Date created = this.getCreatedDateFromToken(token);
		final Date expiration = this.getExpirationDateFromToken(token);
		return (usernameToValidate.equals(username) 
				&& applicationCodeToValidate.equals(applicationCode)
				&& !(this.isTokenExpired(token)) 
				&& !(this.isCreatedBeforeLastPasswordReset(created, lastPasswordReset)));
	}

}
