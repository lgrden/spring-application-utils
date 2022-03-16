package io.wegetit.sau.security;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SecurityProperties {
    private String authTokenName = "auth_token";
    private List<String> webIgnoringAntMatchers = new ArrayList<>();
    private boolean csrfDisabled = true;
    private boolean corsDisabled = true;
}
