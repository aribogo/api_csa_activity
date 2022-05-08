package api.gft.security.service;

import java.util.Date;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import api.gft.security.dto.AuthenticationDTO;
import api.gft.security.dto.TokenDTO;
import api.gft.security.entity.User;

@Service
public class AuthenticationService {

	private final AuthenticationManager authManager;

	@Value("${csa.jwt.expiration}")
	private String expiration;
	@Value("${csa.jwt.secret}")
	private String secret;
	@Value("${csa.jwt.issuer}")
	private String issuer;

	// Constructor
	public AuthenticationService(AuthenticationManager authManager) {
		this.authManager = authManager;
	}

	// Methods
	/**
	 * Authenticates user.
	 * 
	 * @param authFrom
	 * @return
	 * @throws AuthenticationException
	 */
	public TokenDTO authenticate(AuthenticationDTO authFrom) throws AuthenticationException {

		Authentication authenticate = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(authFrom.getEmail(), authFrom.getSenha()));

		String token = generateToken(authenticate);

		return new TokenDTO(token);

	}

	/**
	 * Creates an algorithm using HmacSHA256 and the JSON Web Token's secret.
	 * 
	 * @return algorithm created.
	 */
	private Algorithm createAlgorithm() {
		return Algorithm.HMAC256(secret);
	}

	/**
	 * Generates token for authenticated user using JSON Web Token.
	 * 
	 * @param authenticate
	 * @return
	 */
	private String generateToken(Authentication authenticate) {
		User principal = (User) authenticate.getPrincipal();

		Date now = new Date();
		Date expirationDate = new Date(now.getTime() + Long.parseLong(expiration));

		return JWT.create().withIssuer(issuer).withExpiresAt(expirationDate).withSubject(principal.getId().toString())
				.sign(createAlgorithm());
	}

	/**
	 * Verifies if a token is valid.
	 * 
	 * @param token
	 * @return
	 */
	public boolean verifyToken(String token) {
		try {
			if (token == null) {
				return false;
			} else {
				JWT.require(this.createAlgorithm()).withIssuer(issuer).build().verify(token);

				return true;
			}
		} catch (JWTVerificationException e) {
			return false;
		}

	}

	/**
	 * Gets the id from user based on token as String and transforms it in type
	 * Long.
	 * 
	 * @param token
	 * @return Long id
	 */
	public Long getIdUsuer(String token) {

		String subject = JWT.require(this.createAlgorithm()).withIssuer(issuer).build().verify(token).getSubject();
		return Long.parseLong(subject);
	}
}
