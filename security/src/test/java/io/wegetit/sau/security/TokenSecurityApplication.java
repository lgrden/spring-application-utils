package io.wegetit.sau.security;

import io.wegetit.sau.security.model.SecurityAuthorizeUserRequest;
import io.wegetit.sau.security.model.SecurityAuthorizeUserResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@EnableTokenSecurity
@SpringBootApplication
@ComponentScan(useDefaultFilters = false)
public class TokenSecurityApplication {

    public static final String LOGIN = "userLogin";
    public static final String PASSWORD = "userPassword";

    @Bean
    public SecurityTokenFacade securityTokenFacade() {
        return new SecurityTokenFacade() {
            @Override
            public boolean authenticate(SecurityAuthorizeUserRequest request) {
                return StringUtils.equals(LOGIN, request.getLogin()) && StringUtils.equals(PASSWORD, request.getPassword());
            }

            @Override
            public UsernamePasswordAuthenticationToken getAuthenticationToken(SecurityAuthorizeUserResponse response) {
                return new UsernamePasswordAuthenticationToken(response.getLogin(), response.getLogin());
            }
        };
    }

    @Bean
    public SecuredTestService securedTestService() {
        return new SecuredTestService();
    }

    public static void main(String[] args) {
        SpringApplication.run(TokenSecurityApplication.class, args);
    }
}
