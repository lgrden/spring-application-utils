package io.wegetit.sau.http;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnableHttpRequestLoggerConfiguration {

    @Bean
    public HttpRequestLogger httpRequestLogger(ObjectProvider<HttpRequestFilter> filter) {
        return new HttpRequestLogger(filter.getIfAvailable(HttpRequestFilter::new));
    }

}
