package io.wegetit.sau.core.slack;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class SlackHook {
    private boolean enabled = true;
    @NotEmpty
    private String type;
    @NotEmpty
    private String hook;
    @NotEmpty
    private String channel;
    @NotEmpty
    private String username;
    @NotEmpty
    private String iconEmoji;
}
