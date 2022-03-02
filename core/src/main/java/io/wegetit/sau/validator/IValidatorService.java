package io.wegetit.sau.validator;

import java.util.function.Consumer;

public interface IValidatorService {

    <T> Consumer<T> consumerValidator(Class<?>... groups);

    <T> void validateAndThrow(T t, Class<?>... groups);

    <T> ValidationResult validate(T t, Class<?>... groups);
}

