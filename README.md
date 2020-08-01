# spring-application-utils @ We Get IT

## Descriprion
Useful spring application utils and tools.

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

## Manifest REST Endpoint
Enabling manifest will create REST endpoint allowing to view manifest.mf content under the ```manifest.endpoint``` property. By default it serves it under ```/manifest```.

Code sample:
```java
@SpringBootApplication
@EnableManifestEndpoint
public class MySpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }
}
```

## Slack Messaging 
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