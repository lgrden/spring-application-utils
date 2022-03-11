package io.wegetit.sau.swagger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SwaggerIndexRedirectApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
public class SwaggerIndexRedirectTest {

    @Autowired
    private SwaggerIndexRedirectController controller;

    @Test
    public void index() {
        assertEquals("forward:/swagger-ui.html", controller.index());
    }
}
