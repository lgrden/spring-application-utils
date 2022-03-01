package io.wegetit.sau.slack;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
@Slf4j
public class SlackMessageService {

    private final RestTemplate slackRestTemplate;
    private final ObjectMapper slackObjectMapper;
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
                HttpEntity<String> request = new HttpEntity<>(slackObjectMapper.writeValueAsString(message), headers);
                slackRestTemplate.exchange(slack.getHook(), HttpMethod.POST, request, String.class);
                log.info("Send {} message: {}", slack.getType(), message);
            } catch (Exception e) {
                log.error("Problem sending {} message: {}", slack.getType(), message);
            }
        }
    }
}
