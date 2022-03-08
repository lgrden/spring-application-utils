package io.wegetit.sau.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerIndexRedirectController {

    @RequestMapping("/")
    public String index() {
        return "forward:/swagger-ui.html";
    }
}
