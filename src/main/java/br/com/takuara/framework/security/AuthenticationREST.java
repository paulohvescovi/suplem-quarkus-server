package br.com.takuara.framework.security;

import br.com.takuara.user.User;
import br.com.takuara.user.UserService;
import br.com.takuara.utils.TokenUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthenticationREST {

	@Inject PBKDF2Encoder passwordEncoder;
	@Inject UserService userService;

	@ConfigProperty(name = "br.com.takuara.jwt.duration") public Long duration;
	@ConfigProperty(name = "mp.jwt.verify.issuer") public String issuer;

	@PermitAll
	@POST
	@Path("/login") @Produces(MediaType.APPLICATION_JSON)
	public Response login(AuthRequest authRequest) {
		User u = userService.findByEmail(authRequest.getUsername());
		if (u != null && u.getPassword().equals(passwordEncoder.encode(authRequest.password))) {
			try {
				return Response.ok(new AuthResponse(TokenUtils.generateToken(u.getEmail(), u.getRoles(), duration, issuer))).build();
			} catch (Exception e) {
				return Response.status(Response.Status.UNAUTHORIZED).build();
			}
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

}
