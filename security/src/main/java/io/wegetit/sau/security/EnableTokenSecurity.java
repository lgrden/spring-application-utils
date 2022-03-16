package io.wegetit.sau.security;

import io.wegetit.sau.shared.configuration.BaseConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableTokenSecurity.EnableTokenSecurityConfiguration.class)
public @interface EnableTokenSecurity {

    @Slf4j
    @Configuration
    class EnableTokenSecurityConfiguration extends BaseConfiguration {

        @Bean
        public SecurityAuthenticationProvider securityAuthenticationProvider() {
            return new SecurityAuthenticationProvider();
        }

        @Bean
        public SecurityAuthenticationService securityAuthenticationService(SecurityTokenFacade securityTokenFacade) {
            return new SecurityAuthenticationService(securityTokenFacade);
        }

        @Bean
        public SecurityAuthenticationRestService securityAuthenticationRestService(SecurityAuthenticationService securityAuthenticationService) {
            return new SecurityAuthenticationRestService(securityAuthenticationService);
        }

        @Bean
        public SecurityConfig securityConfig() {
            return new SecurityConfig();
        }

        @Bean
        public SecurityFilter securityFilter(SecurityAuthenticationService securityAuthenticationService) {
            return new SecurityFilter(securityAuthenticationService);
        }
    }
}
