package io.wegetit.sau.log.http;

import io.wegetit.sau.utils.BaseConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableHttpRequestLogger.HttpRequestLoggerConfiguration.class)
public @interface EnableHttpRequestLogger {

    @Slf4j
    @Configuration
    class HttpRequestLoggerConfiguration extends BaseConfiguration {

        @Bean
        public HttpRequestLogger httpRequestLogger(ObjectProvider<HttpRequestFilter> filter) {
            return new HttpRequestLogger(filter.getIfAvailable(HttpRequestFilter::new));
        }
    }
}
