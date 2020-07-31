package io.wegetit.sau.slack;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlackHook {
    private boolean enabled = true;
    private String type;
    private String hook;
    private String channel;
    private String username;
    private String iconEmoji;
}
