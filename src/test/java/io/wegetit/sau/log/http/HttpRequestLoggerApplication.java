package io.wegetit.sau.log.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableHttpRequestLogger
@SpringBootApplication
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
