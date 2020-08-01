package io.wegetit.sau.logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableHttpRequestLogger
@Configuration
public class HttpRequestLoggerApplication {

    @Bean
    public HttpRequestFilter httpRequestFilter() {
        return new HttpRequestFilter() {
            @Override
            public boolean logUrl(String url, long time, int status) {
                return status == 123;
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(HttpRequestLoggerApplication.class, args);
    }
}
