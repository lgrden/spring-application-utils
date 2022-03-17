package io.wegetit.sau.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class InMemorySecurityTokenFacade implements SecurityTokenFacade {

    private static final int DEFAULT_EXPIRES = 600;
    private static final ConcurrentHashMap<String, TokenDetails> TOKENS = new ConcurrentHashMap();

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    private void tokenExpired() {
        TOKENS.values().stream().forEach(p -> {
            LocalDateTime now = LocalDateTime.now();
            if (now.isAfter(p.getExpires())) {
                log.info("Token {} for user {} has expired.", p.getToken(), p.getLogin());
                TOKENS.remove(p.getToken());
            }
        });
    }

    @Override
    public TokenDetails store(String login) {
        String token = UUID.randomUUID().toString();
        TokenDetails details = TokenDetails.builder()
                .login(login).token(token)
                .expires(LocalDateTime.now().plusSeconds(DEFAULT_EXPIRES))
                .build();
        TOKENS.put(token, details);
        return details;
    }

    @Override
    public TokenDetails invalidate(String token) {
        return TOKENS.remove(token);
    }

    @Override
    public TokenDetails extend(String token) {
        TokenDetails details = TOKENS.get(token);
        if (details != null) {
            details = details.toBuilder().expires(LocalDateTime.now().plusSeconds(DEFAULT_EXPIRES)).build();
            TOKENS.put(token, details);
            return details;
        }
        return null;
    }
}
