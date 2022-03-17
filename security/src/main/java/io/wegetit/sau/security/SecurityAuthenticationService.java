package io.wegetit.sau.security;

import io.wegetit.sau.security.model.SecurityAuthorizeRequest;
import io.wegetit.sau.security.model.SecurityAuthorizeResponse;
import io.wegetit.sau.security.model.SecurityInvalidateRequest;
import io.wegetit.sau.security.model.SecurityInvalidateResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@AllArgsConstructor
public class SecurityAuthenticationService {

    private static final int DEFAULT_EXPIRES = 600;
    private static final ConcurrentHashMap<String, SecurityAuthorizeResponse> TOKENS = new ConcurrentHashMap();

    private final SecurityAuthenticationFacade securityAuthenticationFacade;

    @Scheduled(fixedDelay = 100, initialDelay = 100)
    private void tokenExpired() {
        TOKENS.values().stream().forEach(p -> {
            LocalDateTime now = LocalDateTime.now();
            if (now.isAfter(p.getExpires())) {
                log.info("Token {} for user {} has expired.", p.getToken(), p.getLogin());
                TOKENS.remove(p.getToken());
            }
        });
    }

    public SecurityAuthorizeResponse authorize(@Valid @RequestBody SecurityAuthorizeRequest request) {
        if (securityAuthenticationFacade.authenticate(request)) {
            String token = UUID.randomUUID().toString();
            log.info("Token {} for user {} has created.", token, request.getLogin());
            TOKENS.put(token, SecurityAuthorizeResponse.builder()
                    .login(request.getLogin())
                    .token(token)
                    .expires(LocalDateTime.now().plusSeconds(DEFAULT_EXPIRES))
                    .build());
            return TOKENS.get(token);
        }
        throw new SecurityException("Invalid login or password.");
    }

    public SecurityInvalidateResponse invalidate(@Valid @RequestBody SecurityInvalidateRequest request) {
        SecurityAuthorizeResponse response = TOKENS.get(request.getToken());
        if (response != null) {
            TOKENS.remove(request.getToken());
            log.info("Token {} for user {} has been invalidated.", request.getToken(), response.getLogin());
        }
        return SecurityInvalidateResponse.builder().token(request.getToken()).build();
    }

    public Authentication getAuthentication(String token) {
        SecurityAuthorizeResponse response = TOKENS.get(token);
        if (response != null) {
            TOKENS.put(token, response.toBuilder().expires(LocalDateTime.now().plusSeconds(DEFAULT_EXPIRES)).build());
            return securityAuthenticationFacade.getAuthenticationToken(response);
        }
        return null;
    }
}
