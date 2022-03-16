package io.wegetit.sau.security;

import io.wegetit.sau.security.model.SecurityAuthorizeUserRequest;
import io.wegetit.sau.security.model.SecurityAuthorizeUserResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface SecurityTokenFacade {

    boolean authenticate(SecurityAuthorizeUserRequest request);

    UsernamePasswordAuthenticationToken getAuthenticationUser(SecurityAuthorizeUserResponse response);
}
