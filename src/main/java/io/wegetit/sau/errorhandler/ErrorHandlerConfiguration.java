package io.wegetit.sau.errorhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.wegetit.sau.json.ObjectMapperBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorHandlerConfiguration {

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
