package io.wegetit.sau.security;

import io.wegetit.sau.security.model.SecurityAuthorizeRequest;
import io.wegetit.sau.security.model.SecurityAuthorizeResponse;
import io.wegetit.sau.security.model.SecurityInvalidateRequest;
import io.wegetit.sau.security.model.SecurityInvalidateResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/security")
public class SecurityAuthenticationRestService {

    private final SecurityAuthenticationService securityAuthenticationService;

    // no permission, accessible for everyone
    @PostMapping("/authorize")
    public SecurityAuthorizeResponse authorize(@Valid @RequestBody SecurityAuthorizeRequest request) {
        return securityAuthenticationService.authorize(request);
    }

    // no permission, accessible for everyone
    @PostMapping("/invalidate")
    public SecurityInvalidateResponse invalidate(@Valid @RequestBody SecurityInvalidateRequest request) {
        return securityAuthenticationService.invalidate(request);
    }
}
