package io.wegetit.sau.swagger;

import io.wegetit.sau.shared.BaseConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableSwagger.SwaggerConfiguration.class)
public @interface EnableSwagger {

    @Slf4j
    @Configuration
    class SwaggerConfiguration extends BaseConfiguration {

        @Bean
        public SwaggerIndexController swaggerIndexController() {
            return new SwaggerIndexController();
        }
    }
}
