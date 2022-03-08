# spring-application-utils-core @ We Get IT

## Introduction
Following are core module utilities and tools:
- [@EnableErrorHandler](#@EnableErrorHandler)
- [@EnableExecutionLogger](#@EnableExecutionLogger)
- [@EnableHttpRequestLogger](#@EnableHttpRequestLogger)
- [@EnableSlackMessaging](#@EnableSlackMessaging)
- [@EnableSystemInfo](#@EnableSystemInfo)
- [@EnableValidation](#@EnableValidation)

## @EnableErrorHandler
TODO
## @EnableExecutionLogger
TODO
## @EnableHttpRequestLogger
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
## @EnableSlackMessaging
Enabling slack messaging will allow to send messages to particular slack hook.

Code sample:
```java
@SpringBootApplication
@EnableSlackMessaging
@Configuration
public class MySpringBootApplication {
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Primary
    @Bean
    @Validated
    @ConfigurationProperties(prefix = "slack.test")
    public SlackHook alerting() {
        return new SlackHook();
    }

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }
}
```
properties file:
```java
slack.test.type=test
slack.test.enabled=true
slack.test.hook=https://hooks.slack.com/services/Test
slack.test.channel=test-channel
slack.test.username=test-username
slack.test.icon_emoji=:robot_face:
```
## @EnableSystemInfo
TODO
## @EnableValidation
TODO