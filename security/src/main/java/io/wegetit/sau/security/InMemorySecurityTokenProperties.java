package io.wegetit.sau.security;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class InMemorySecurityTokenProperties {
    @Min(60)
    private Integer tokenExpires = 5 * 60;
    @Min(0)
    private Integer initialDelay = 1000;
    @Min(1000)
    private Integer fixedDelay = 1000;
}
