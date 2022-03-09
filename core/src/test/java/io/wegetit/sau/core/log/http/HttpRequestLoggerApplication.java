package io.wegetit.sau.core.log.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@EnableHttpRequestLogger
@SpringBootApplication
@ComponentScan(useDefaultFilters = false)
public class HttpRequestLoggerApplication {

    @Bean
    public HttpRequestFilter httpRequestFilter() {
        return (url, time, status) -> status == 123;
    }

    public static void main(String[] args) {
        SpringApplication.run(HttpRequestLoggerApplication.class, args);
    }
}
