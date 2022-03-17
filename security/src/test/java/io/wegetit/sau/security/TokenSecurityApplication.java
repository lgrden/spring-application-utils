package io.wegetit.sau.security;

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
    public SecurityAuthenticationFacade securityAuthenticationFacade() {
        return new SecurityAuthenticationFacade() {
            @Override
            public boolean authenticate(String login, String password) {
                return (StringUtils.equals("login1", login) || StringUtils.equals("login2", login))
                    && StringUtils.equals("password", password);
            }

            @Override
            public UsernamePasswordAuthenticationToken getAuthenticationToken(SecurityTokenFacade.TokenDetails td) {
                return new UsernamePasswordAuthenticationToken(td.getLogin(), td.getLogin());
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
