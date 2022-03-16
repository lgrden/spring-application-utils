package io.wegetit.sau.security;

import io.wegetit.sau.security.model.SecurityAuthorizeUserRequest;
import io.wegetit.sau.security.model.SecurityAuthorizeUserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TokenSecurityApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
public class TokenSecurityTest {

    @Autowired
    private SecurityAuthenticationRestService restService;

    @Test
    public void login() {
        SecurityAuthorizeUserRequest request = SecurityAuthorizeUserRequest.builder()
            .login(TokenSecurityApplication.LOGIN).password(TokenSecurityApplication.PASSWORD).build();
        SecurityAuthorizeUserResponse response = restService.authorize(request);
        assertEquals(TokenSecurityApplication.LOGIN, response.getLogin());
    }
}
