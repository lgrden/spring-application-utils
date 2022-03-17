package io.wegetit.sau.core.slack;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
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
