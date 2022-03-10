package io.wegetit.sau.core.log.http;

import io.wegetit.sau.shared.configuration.BaseConfiguration;
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

    @Configuration
    class HttpRequestLoggerConfiguration extends BaseConfiguration {

        @Bean
        public HttpRequestLogger httpRequestLogger(ObjectProvider<HttpRequestFilter> provider) {
            return new HttpRequestLogger(getOrDefault(provider, (url, time, status) -> true));
        }
    }
}
