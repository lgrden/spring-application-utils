package io.wegetit.sau.security;

import io.wegetit.sau.security.model.SecurityAuthorizeUserRequest;
import io.wegetit.sau.security.model.SecurityAuthorizeUserResponse;
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
    public SecurityAuthorizeUserResponse authorize(@Valid @RequestBody SecurityAuthorizeUserRequest request) {
        return securityAuthenticationService.authorize(request);
    }

    // no permission, accessible for everyone
    @PostMapping("/invalidate")
    public SecurityInvalidateResponse invalidate(@Valid @RequestBody SecurityInvalidateRequest request) {
        return securityAuthenticationService.invalidate(request);
    }
}
