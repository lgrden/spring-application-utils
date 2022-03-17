package io.wegetit.sau.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InMemorySecurityTokenProperties {
    private int expires = 600;
    private int initialDelay = 1000;
    private int fixedDelay = 1000;
}
