# spring-application-utils-swagger @ We Get IT

## Introduction 
Following are swagger module utilities and tools:
 - [@EnableSwaggerIndexRedirect](#@EnableSwaggerIndexRedirect)

## @EnableSwaggerIndexRedirect
Redirects index page of / to swagger-ui.html

Code sample:
```java
@SpringBootApplication
@EnableSwaggerIndexRedirect
public class MySpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }
}
```
