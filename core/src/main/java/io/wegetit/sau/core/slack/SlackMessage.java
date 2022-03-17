package io.wegetit.sau.core.slack;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

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
