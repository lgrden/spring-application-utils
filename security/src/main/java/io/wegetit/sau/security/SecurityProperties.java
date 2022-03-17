package io.wegetit.sau.security;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SecurityProperties {
    private String authTokenName = "auth_token";
    private List<String> webIgnoringAntMatchers = new ArrayList<>();
    private boolean csrfDisabled = true;
    private boolean corsDisabled = true;
}
