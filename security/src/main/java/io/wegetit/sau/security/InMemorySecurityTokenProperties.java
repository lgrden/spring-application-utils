package io.wegetit.sau.security;

import lombok.Data;

@Data
public class InMemorySecurityTokenProperties {
    private int expiresInSeconds = 600;
    private int initialDelay = 1000;
    private int fixedDelay = 1000;
}