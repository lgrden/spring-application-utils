package io.wegetit.sau.validator;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class ValidationEntry {
    private ValidationType type;
    private String path;
    private String code;
    private String message;
}
