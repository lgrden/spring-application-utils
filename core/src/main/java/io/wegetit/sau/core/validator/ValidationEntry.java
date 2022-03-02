package io.wegetit.sau.core.validator;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class ValidationEntry {
    private ValidationType type;
    private String path;
    private String code;
    private String message;
}
