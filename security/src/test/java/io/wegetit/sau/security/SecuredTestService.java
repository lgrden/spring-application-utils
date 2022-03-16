package io.wegetit.sau.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class SecuredTestService {

    @PreAuthorize("'userLogin' == authentication.principal")
    public String test() {
        return "ok";
    }
}
