package io.wegetit.sau.validator;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.constraints.NotNull;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
    ValidatorApplication.class
})
@TestInstance(Lifecycle.PER_CLASS)
public class ValidatorTest {

    @Autowired
    private ValidatorService validatorService;

    @Data
    private static class User {
        @NotNull
        private String name;
    }

    @Test
    public void validate() {
        ValidationResult result = validatorService.validate(new User());
        assertTrue(result.hasErrors());
        assertEquals("name", result.getList().get(0).getPath());
    }
}
