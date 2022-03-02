package io.wegetit.sau.slack;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
class SlackMessage {
    private String channel;
    private String username;
    @JsonProperty("icon_emoji")
    private String iconEmoji;
    private String text;
    private List<SlackAttachment> attachments = List.of();
}
