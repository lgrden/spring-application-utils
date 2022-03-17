package io.wegetit.sau.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;

public class SecurityAuthenticationProvider implements AuthenticationProvider {

    public Authentication authenticate(Authentication authentication) {
        return authentication;
    }

    public boolean supports(Class<?> arg0) {
        return true;
    }
}
