package io.wegetit.sau.core.validator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ValidatorApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ValidatorTest {

    @Autowired
    private ValidatorService validatorService;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class User {
        @NotNull
        private String name;
    }

    @Test
    public void validate() {
        ValidationResult result = validatorService.validate(new User());
        assertTrue(result.hasErrors());
        assertThat(result.getList(), hasSize(1));
        assertEquals("name", result.getList().get(0).getPath());
        assertEquals("NotNull", result.getList().get(0).getCode());
        assertEquals("must not be null", result.getList().get(0).getMessage());
        assertEquals(ValidationType.FIELD, result.getList().get(0).getType());
    }

    @Test
    public void validateAndThrow() {
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> validatorService.validateAndThrow(new User()));
        assertThat(exception.getConstraintViolations(), hasSize(1));
        ConstraintViolation<?> cv = exception.getConstraintViolations().stream().findAny().orElseThrow();
        assertEquals("name", cv.getPropertyPath().toString());
        assertEquals("must not be null", cv.getMessage());
    }

    @Test
    public void consumerValidator() {
        validatorService.consumerValidator().accept(User.builder().name("test").build());
    }
}
