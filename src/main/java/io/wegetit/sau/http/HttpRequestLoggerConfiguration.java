package io.wegetit.sau.http;

import io.wegetit.sau.utils.BaseConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class HttpRequestLoggerConfiguration extends BaseConfiguration {

    @Bean
    public HttpRequestLogger httpRequestLogger(ObjectProvider<HttpRequestFilter> filter) {
        return new HttpRequestLogger(filter.getIfAvailable(HttpRequestFilter::new));
    }
}
