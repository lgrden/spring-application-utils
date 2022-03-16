package io.wegetit.sau.security;

import io.wegetit.sau.security.model.SecurityAuthorizeRequest;
import io.wegetit.sau.security.model.SecurityAuthorizeResponse;
import io.wegetit.sau.security.model.SecurityInvalidateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TokenSecurityApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public class TokenSecurityTest {

    @Autowired
    private SecurityAuthenticationRestService sars;

    @Autowired
    private SecurityAuthenticationService sas;

    @Autowired
    private TestRestTemplate template;

    public SecurityAuthorizeResponse successLogin(String login, String password) {
        SecurityAuthorizeRequest request = SecurityAuthorizeRequest.builder().login(login).password(password).build();
        ResponseEntity<SecurityAuthorizeResponse> authResponse = template.postForEntity("/security/authorize", request, SecurityAuthorizeResponse.class);
        assertEquals(HttpStatus.OK, authResponse.getStatusCode());
        assertEquals(login, authResponse.getBody().getLogin());
        assertNotNull(authResponse.getBody().getToken());
        assertNotNull(authResponse.getBody().getExpires());
        return authResponse.getBody();
    }

    @Test
    public void loginWithAccess() {
        SecurityAuthorizeResponse authResponse = successLogin("login1", "password");

        ResponseEntity<String> response = template.getForEntity("/test?auth_token=" + authResponse.getToken(), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("hello", response.getBody());
    }

    @Test
    public void loginWithoutAccess() {
        SecurityAuthorizeResponse authResponse = successLogin("login2", "password");

        ResponseEntity<String> response = template.getForEntity("/test?auth_token=" + authResponse.getToken(), String.class);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void loginAndInvalidate() {
        SecurityAuthorizeResponse authResponse = successLogin("login1", "password");

        SecurityInvalidateRequest request = SecurityInvalidateRequest.builder().token(authResponse.getToken()).build();
        ResponseEntity<String> response = template.postForEntity("/security/invalidate", request, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void loginWithInvalidLogin() {
        SecurityAuthorizeRequest request = SecurityAuthorizeRequest.builder().login("invalid").password("password").build();
        ResponseEntity<SecurityAuthorizeResponse> response = template.postForEntity("/security/authorize", request, SecurityAuthorizeResponse.class);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void getWithoutToken() {
        ResponseEntity<String> response = template.getForEntity("/test?auth_token=none", String.class);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
}
