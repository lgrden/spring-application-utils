package io.wegetit.sau.security;

import io.wegetit.sau.security.model.SecurityAuthorizeRequest;
import io.wegetit.sau.security.model.SecurityAuthorizeResponse;
import io.wegetit.sau.security.model.SecurityInvalidateRequest;
import io.wegetit.sau.security.model.SecurityInvalidateResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
public class SecurityAuthenticationService {

    private final SecurityAuthenticationFacade securityAuthenticationFacade;
    private final SecurityTokenFacade securityTokenFacade;

    public SecurityAuthorizeResponse authorize(@Valid @RequestBody SecurityAuthorizeRequest request) {
        if (securityAuthenticationFacade.authenticate(request.getLogin(), request.getPassword())) {
            SecurityTokenFacade.TokenDetails td = securityTokenFacade.store(request.getLogin());
            log.info("Token {} for login {} has created.", td.getToken(), td.getLogin());
            return SecurityAuthorizeResponse.builder()
                    .login(td.getLogin())
                    .token(td.getToken())
                    .expires(td.getExpires())
                    .build();
        }
        throw new SecurityException("Invalid login or password.");
    }

    public SecurityInvalidateResponse invalidate(@Valid @RequestBody SecurityInvalidateRequest request) {
        SecurityTokenFacade.TokenDetails td = securityTokenFacade.invalidate(request.getToken());
        if (td != null) {
            log.info("Token {} for login {} has been invalidated.", td.getToken(), td.getLogin());
        }
        return SecurityInvalidateResponse.builder().token(request.getToken()).build();
    }

    public Authentication getAuthentication(String token) {
        SecurityTokenFacade.TokenDetails td = securityTokenFacade.extend(token);
        return td != null ? securityAuthenticationFacade.getAuthenticationToken(td) : null;
    }
}
