package io.wegetit.sau.logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnableHttpRequestLoggerConfiguration {

    @Bean
    public HttpRequestLoggerFilter httpRequestLoggerFilter() {
        return new HttpRequestLoggerFilter();
    }

}
