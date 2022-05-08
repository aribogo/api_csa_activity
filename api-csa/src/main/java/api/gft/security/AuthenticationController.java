package api.gft.security;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.gft.security.dto.AuthenticationDTO;
import api.gft.security.dto.TokenDTO;
import api.gft.security.service.AuthenticationService;

@RestController
@RequestMapping("v1/auth")
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody AuthenticationDTO authForm) {

		try {
			return ResponseEntity.ok(authenticationService.authenticate(authForm));
		} catch (AuthenticationException ae) {
			ae.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

	}

}
