# spring-application-utils-mongo @ We Get IT

## Introduction
Following are mongo module utilities and tools:
- [@EnableMongoBaseSetup](#@EnableMongoBaseSetup)

## @EnableMongoBaseSetup
- Adds LocalDate and LocalDateTime converters to MongoCustomConversions.
- Creates index for all the mongo entities marked with spring data Document annotation.

Code sample:
```java
@SpringBootApplication
@EnableMongoBaseSetup
public class MySpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }
}
```
