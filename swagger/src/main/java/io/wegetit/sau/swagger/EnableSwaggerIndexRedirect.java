package io.wegetit.sau.swagger;

import io.wegetit.sau.shared.configuration.BaseConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableSwaggerIndexRedirect.SwaggerIndexRedirectConfiguration.class)
public @interface EnableSwaggerIndexRedirect {

    @Slf4j
    @Configuration
    class SwaggerIndexRedirectConfiguration extends BaseConfiguration {

        @Bean
        public SwaggerIndexRedirectController swaggerIndexController() {
            return new SwaggerIndexRedirectController();
        }
    }
}
