package io.wegetit.sau.core.validator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class ValidationEntry {
    private ValidationType type;
    private String path;
    private String code;
    private String message;
}
