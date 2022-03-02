package io.wegetit.sau.validator;

import io.wegetit.sau.utils.BaseConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Slf4j
@Configuration
public class ValidationConfiguration extends BaseConfiguration {

    @Bean
    public ValidatorService validatorService(LocalValidatorFactoryBean validator) {
        return new ValidatorService(validator);
    }
}
