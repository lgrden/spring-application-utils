package io.wegetit.sau.security;

import io.wegetit.sau.security.model.SecurityAuthorizeRequest;
import io.wegetit.sau.security.model.SecurityAuthorizeResponse;
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

    @Bean
    public SecurityAuthenticationFacade securityTokenFacade() {
        return new SecurityAuthenticationFacade() {
            @Override
            public boolean authenticate(SecurityAuthorizeRequest request) {
                return (StringUtils.equals("login1", request.getLogin()) || StringUtils.equals("login2", request.getLogin()))
                    && StringUtils.equals("password", request.getPassword());
            }

            @Override
            public UsernamePasswordAuthenticationToken getAuthenticationToken(SecurityAuthorizeResponse response) {
                return new UsernamePasswordAuthenticationToken(response.getLogin(), response.getLogin());
            }
        };
    }

    @Bean
    public TestRestService securedTestService() {
        return new TestRestService();
    }

    public static void main(String[] args) {
        SpringApplication.run(TokenSecurityApplication.class, args);
    }
}
