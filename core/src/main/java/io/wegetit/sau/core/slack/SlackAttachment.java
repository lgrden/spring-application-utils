package io.wegetit.sau.core.slack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class SlackAttachment {
    private String text;
    private String color;

    public static SlackAttachment of(String text, String color) {
        return SlackAttachment.builder()
            .text(text)
            .color(color)
            .build();
    }
}
