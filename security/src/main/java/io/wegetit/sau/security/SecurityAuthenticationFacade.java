package io.wegetit.sau.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface SecurityAuthenticationFacade {

    boolean authenticate(String login, String password);

    UsernamePasswordAuthenticationToken getAuthenticationToken(SecurityTokenFacade.TokenDetails td);
}
