package io.wegetit.sau.errorhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.wegetit.sau.json.ObjectMapperBuilder;
import io.wegetit.sau.utils.BaseConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ErrorHandlerConfiguration extends BaseConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ObjectMapper objectMapper() {
        return ObjectMapperBuilder.build();
    }

    @Bean
    public ErrorHandlerService errorHandlerService(ObjectMapper objectMapper) {
        return new ErrorHandlerService(objectMapper);
    }

    @Bean
    public ErrorHandlerRestService errorHandlerRestService(ErrorHandlerService errorHandlerService) {
        return new ErrorHandlerRestService(errorHandlerService);
    }
}
