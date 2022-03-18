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
Creates global error handler and exposes details via rest '/error-types'.
Every exception that is registered will be logged to console.

> 2022-03-09 00:56:54.178  INFO 12880 --- [           main] i.w.s.c.e.ErrorHandlerService            : Exception EntityNotFoundException has been registered.

Code sample:
```java
@SpringBootApplication
@EnableErrorHandler
public class MySpringBootApplication {

    @Autowired
    private ErrorHandlerService errorHandlerService;
    
    /**
        Optionally you can register additional exceptions.
     **/
    @PostConstruct
    private void init() {
        errorHandlerService.registerType(ExceptionType.builder().errorClass(EntityNotFoundException.class)
            .status(HttpStatus.NOT_FOUND).evalMessage(p -> "MSG: " + p.getMessage()).build());
    }

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }
}
```

## @EnableExecutionLogger
Measures and logs time executing method with its status.

> 2022-03-09 01:22:47.915  INFO 19260 --- [           main] MySpringBootApplication       : MySpringBootApplication.execute executed with SUCCESS in 3016 ms.

```java
@SpringBootApplication
@EnableExecutionLogger
public class MySpringBootApplication {

    @LogExecution
    public void execute() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
    }

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }
}
```

## @EnableHttpRequestLogger
Enabling request logger in your spring application will log all the http requests in following format:

> 2020-08-01 14:44:30.253  INFO 88 --- [           main] i.w.sau.logger.HttpRequestLoggerFilter   : GET http://localhost:9090/api/somePath in 64 ms. Status 200.

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
        return (url, time, status) -> status == 123;
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
public class MySpringBootApplication {

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
```properties
slack.test.type=test
slack.test.enabled=true
slack.test.hook=https://hooks.slack.com/services/Test
slack.test.channel=test-channel
slack.test.username=test-username
slack.test.icon_emoji=:robot_face:
```

## @EnableSystemInfo
Reads all properties with prefix system.build.* and exposes details via rest '/system-info'. 

Code sample:
```java
@SpringBootApplication
@EnableSystemInfo
public class MySpringBootApplication {

    @Validated
    @Bean
    @ConfigurationProperties(prefix = "system.build")
    public SystemBuild systemBuild() {
        return new SystemBuild();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }
}
```

## @EnableValidation
Allows to use ValidatorService to manually validate entities with validation constraints.

Code sample:
```java
@SpringBootApplication
@EnableValidation
public class MySpringBootApplication {

    @Autowired
    private ValidatorService validatorService;

    public ValidationResult validate() {
        return validatorService.validate(new User());
    }
    
    public void validateAndThrow() {
        validatorService.validateAndThrow(new User());
    }
    
    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }
}
```