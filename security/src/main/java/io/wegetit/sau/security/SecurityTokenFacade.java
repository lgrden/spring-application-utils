package io.wegetit.sau.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public interface SecurityTokenFacade {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
    class TokenDetails {
        private String login;
        private String token;
        private LocalDateTime expires;
    }

    TokenDetails store(String login);

    TokenDetails invalidate(String token);

    TokenDetails extend(String token);
}
