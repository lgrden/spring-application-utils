package io.wegetit.sau.validator;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ValidatorService {

    private final LocalValidatorFactoryBean validator;

    public <T> Consumer<T> consumerValidator(Class<?>... groups) {
        return t -> validateAndThrow(t, groups);
    }

    public <T> void validateAndThrow(T t, Class<?>... groups) {
        Set<ConstraintViolation<T>> errors = collect(t, groups);
        if (!errors.isEmpty()) {
            throw new ConstraintViolationException(errors);
        }
    }

    public <T> ValidationResult validate(T t, Class<?>... groups) {
        return result(collect(t, groups));
    }

    private <T> ValidationResult result(Set<ConstraintViolation<T>> errors) {
        return ValidationResult.builder()
            .list(errors.stream().map(p -> ValidationEntry.builder()
                    .type(StringUtils.isEmpty(p.getPropertyPath().toString()) ? ValidationType.BEAN : ValidationType.FIELD)
                    .path(p.getPropertyPath().toString())
                    .code(p.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName())
                    .message(p.getMessage())
                    .build())
                    .collect(Collectors.toList()))
            .build();
    }

    private <T> Set<ConstraintViolation<T>> collect(T t, Class<?>... groups) {
        return ArrayUtils.isEmpty(groups) ? validator.validate(t) : validator.validate(t, groups);
    }
}
