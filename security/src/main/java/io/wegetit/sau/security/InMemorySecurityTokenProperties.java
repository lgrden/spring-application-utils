package io.wegetit.sau.security;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class InMemorySecurityTokenProperties {
    private Integer expiresInSeconds = 600;
    @Min(1000)
    private Integer initialDelay = 1000;
    @Min(0)
    private Integer fixedDelay = 1000;
}
