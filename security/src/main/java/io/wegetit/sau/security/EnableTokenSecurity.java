package io.wegetit.sau.security;

import io.wegetit.sau.shared.configuration.BaseConfiguration;
import io.wegetit.sau.shared.properties.YamlPropertySourceFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableTokenSecurity.EnableTokenSecurityConfiguration.class)
public @interface EnableTokenSecurity {

    @Configuration
    @PropertySource(value = "classpath:sau-security-application.yml", factory = YamlPropertySourceFactory.class)
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    class EnableTokenSecurityConfiguration extends BaseConfiguration {

        @Bean
        @Validated
        @ConfigurationProperties(prefix = "security")
        public SecurityProperties securityProperties() {
            return new SecurityProperties();
        }

        @Bean
        public SecurityAuthenticationProvider securityAuthenticationProvider() {
            return new SecurityAuthenticationProvider();
        }

        @Bean
        public SecurityAuthenticationService securityAuthenticationService(SecurityAuthenticationFacade authenticationFacade,
                                                                           SecurityTokenFacade tokenFacade) {
            return new SecurityAuthenticationService(authenticationFacade, tokenFacade);
        }

        @Bean
        public SecurityAuthenticationRestService securityAuthenticationRestService(SecurityAuthenticationService service) {
            return new SecurityAuthenticationRestService(service);
        }

        @Bean
        public SecurityWebService SecurityWebService(SecurityAuthenticationProvider provider,
                 SecurityAuthenticationService service, SecurityProperties securityProperties) {
            return new SecurityWebService(provider, service, securityProperties);
        }

        @Bean
        @Validated
        @ConfigurationProperties(prefix = "security.in-memory-token")
        @ConditionalOnProperty(prefix = "security.in-memory-token", name = "enabled", havingValue = "true")
        public InMemorySecurityTokenProperties inMemorySecurityTokenProperties() {
            return new InMemorySecurityTokenProperties();
        }

        @Bean
        @ConditionalOnProperty(prefix = "security.in-memory-token", name = "enabled", havingValue = "true")
        public InMemorySecurityTokenFacade securityTokenFacade(InMemorySecurityTokenProperties properties) {
            return new InMemorySecurityTokenFacade(properties);
        }
    }
}
