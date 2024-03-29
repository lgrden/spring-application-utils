package io.wegetit.sau.core.slack;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SlackMessagingApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
public class SlackMessagingTest {

    @Autowired
    private SlackMessageService service;

    @Test
    public void sendMessage() throws URISyntaxException, JsonProcessingException {
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(SlackMessageService.SLACK_REST_TEMPLATE);

        SlackMessage message = SlackMessage.builder()
            .text("This is a slack test message")
            .attachments(List.of(SlackAttachment.of("Welcome Test", "red")))
            .channel("test-channel")
            .username("test-username")
            .iconEmoji(":robot_face:")
            .build();

        mockServer.expect(
            ExpectedCount.once(),
            requestTo(new URI("https://hooks.slack.com/services/Test")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(SlackMessageService.SLACK_OBJECT_MAPPER.writeValueAsString(message))
            );
        service.send("This is a slack test message", SlackAttachment.of("Welcome Test", "red"));
        mockServer.verify();
    }
}
