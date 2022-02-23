# spring-application-utils/http_request_logger @ We Get IT

## Http Request Logger
Enabling request logger in your spring application will log all the http requests in following format:

>  2020-08-01 14:44:30.253  INFO 88 --- [nio-9090-exec-1] i.w.sau.logger.HttpRequestLoggerFilter   : GET http://localhost:9090/api/somePath in 64 ms. Status 200.

Code sample:
```java
@SpringBootApplication
@EnableHttpRequestLogger
public class MySpringBootApplication {

    /**
        Optionally you can configure HttpRequestFilter.
        When not defined all http requests will be logged.
    **/
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
        SpringApplication.run(MySpringBootApplication.class, args);
    }
}
```