package io.wegetit.sau.security;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class TestRestService {

    @GetMapping
    @PreAuthorize("'login1' == authentication.principal")
    public String test() {
        return "hello";
    }
}
