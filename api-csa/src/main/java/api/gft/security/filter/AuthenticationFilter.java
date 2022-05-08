package api.gft.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import api.gft.security.entity.User;
import api.gft.security.service.AuthenticationService;
import api.gft.security.service.UserService;

public class AuthenticationFilter extends OncePerRequestFilter {

	private AuthenticationService authenticationService;
	private UserService userService;

	public AuthenticationFilter(AuthenticationService authenticationService, UserService userService) {
		this.authenticationService = authenticationService;
		this.userService = userService;
	}

	// Methods
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");

		String token = null;

		if (header != null && header.startsWith("Bearer ")) {

			token = header.substring(7, header.length());
		}

		// If authenticated has access
		if (authenticationService.verifyToken(token)) {
			Long idUser = authenticationService.getIdUsuer(token);

			User user = userService.getUserById(idUser);
			SecurityContextHolder.getContext()
					.setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
		}

		filterChain.doFilter(request, response);

	}

}
