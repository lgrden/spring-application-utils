# spring-application-utils-security @ We Get IT

## Introduction
Following are swagger module utilities and tools:
- [@EnableTokenSecurity](#@EnableTokenSecurity)

## @EnableTokenSecurity
Enables web token security. By default in memory token is enabled.

Code sample:
```java
@SpringBootApplication
@EnableTokenSecurity
public class MySpringBootApplication {

    @Bean
    public SecurityAuthenticationFacade securityAuthenticationFacade() {
        return new SecurityAuthenticationFacade() {
            @Override
            public boolean authenticate(String login, String password) {
                return true;
            }

            @Override
            public UsernamePasswordAuthenticationToken getAuthenticationToken(SecurityTokenFacade.TokenDetails td) {
                return new UsernamePasswordAuthenticationToken(td.getLogin(), td.getLogin());
            }
        };
    }
    
    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }
}
```
