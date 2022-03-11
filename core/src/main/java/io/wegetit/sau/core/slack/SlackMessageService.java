package io.wegetit.sau.core.slack;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@AllArgsConstructor
@Slf4j
public class SlackMessageService {
    public static final RestTemplate SLACK_REST_TEMPLATE = new RestTemplate();
    public static final ObjectMapper SLACK_OBJECT_MAPPER = new ObjectMapper();

    private final SlackHook slackHook;

    public void send(String text, SlackAttachment... attachments) {
        send(slackHook, text, attachments);
    }

    public void send(SlackHook slack, String text, SlackAttachment... attachments) {
        if (slack.isEnabled()) {
            SlackMessage message = SlackMessage.builder()
                .text(text)
                .attachments(Arrays.asList(attachments))
                .channel(slack.getChannel())
                .username(slack.getUsername())
                .iconEmoji(slack.getIconEmoji())
                .build();
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> request = new HttpEntity<>(SLACK_OBJECT_MAPPER.writeValueAsString(message), headers);
                SLACK_REST_TEMPLATE.exchange(slack.getHook(), HttpMethod.POST, request, String.class);
                log.info("Send {} message: {}", slack.getType(), message);
            } catch (Exception e) {
                log.error("Problem sending {} message: {}", slack.getType(), message);
            }
        }
    }
}
