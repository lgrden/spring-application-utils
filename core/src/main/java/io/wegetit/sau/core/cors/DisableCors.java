package io.wegetit.sau.core.cors;

import io.wegetit.sau.shared.configuration.BaseConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(DisableCors.DisableCorsConfiguration.class)
public @interface DisableCors {

    @Configuration
    class DisableCorsConfiguration extends BaseConfiguration {

        @Bean
        public DisableCorsWebMvcConfigurer disableCorsWebMvcConfigurer() {
            return new DisableCorsWebMvcConfigurer();
        }
    }
}
