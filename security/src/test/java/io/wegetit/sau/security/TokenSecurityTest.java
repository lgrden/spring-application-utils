package io.wegetit.sau.security;

import io.wegetit.sau.security.model.SecurityAuthorizeUserRequest;
import io.wegetit.sau.security.model.SecurityAuthorizeUserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TokenSecurityApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
public class TokenSecurityTest {

    @Autowired
    private SecurityAuthenticationRestService sars;

    @Autowired
    private SecurityAuthenticationService sas;

    @Autowired
    private SecuredTestService service;

    @Test
    public void loginWithAccess() {
        SecurityAuthorizeUserRequest request = SecurityAuthorizeUserRequest.builder()
            .login(TokenSecurityApplication.LOGIN).password(TokenSecurityApplication.PASSWORD).build();
        SecurityAuthorizeUserResponse response = sars.authorize(request);
        assertNotNull(response.getToken());
        assertEquals(TokenSecurityApplication.LOGIN, response.getLogin());
        SecurityContextHolder.getContext().setAuthentication(sas.getAuthentication(response.getToken()));
        service.test();
    }

    @Test
    public void loginWithoutAccess() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("invalidLogin", "invalidLogin");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AccessDeniedException exception = assertThrows(AccessDeniedException.class, () -> {
            service.test();
        });
        assertEquals("Access is denied", exception.getMessage());
    }

    @Test
    public void invalidLoginOrPassword() {
        SecurityException exception = assertThrows(SecurityException.class, () -> {
            SecurityAuthorizeUserRequest request = SecurityAuthorizeUserRequest.builder()
                    .login(TokenSecurityApplication.LOGIN).password("invalidPassword").build();
            SecurityAuthorizeUserResponse response = sars.authorize(request);
        });
        assertEquals("Invalid login or password.", exception.getMessage());
    }
}
