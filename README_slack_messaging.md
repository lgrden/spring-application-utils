# spring-application-utils/slack_messaging @ We Get IT

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
