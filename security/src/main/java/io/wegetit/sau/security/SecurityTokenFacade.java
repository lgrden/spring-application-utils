package io.wegetit.sau.security;

import io.wegetit.sau.security.model.SecurityAuthorizeRequest;
import io.wegetit.sau.security.model.SecurityAuthorizeResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface SecurityTokenFacade {

    boolean authenticate(SecurityAuthorizeRequest request);

    UsernamePasswordAuthenticationToken getAuthenticationToken(SecurityAuthorizeResponse response);
}
