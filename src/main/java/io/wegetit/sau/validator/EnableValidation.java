package io.wegetit.sau.validator;

import io.wegetit.sau.utils.BaseConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableValidation.ValidationConfiguration.class)
public @interface EnableValidation {

    @Slf4j
    @Configuration
    class ValidationConfiguration extends BaseConfiguration {

        @Bean
        public ValidatorService validatorService(LocalValidatorFactoryBean validator) {
            return new ValidatorService(validator);
        }
    }
}
