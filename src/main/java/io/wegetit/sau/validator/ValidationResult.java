package io.wegetit.sau.validator;

import lombok.*;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class ValidationResult {

    private List<ValidationEntry> list = new ArrayList<>();

    public boolean hasErrors() {
        return !CollectionUtils.isEmpty(list);
    }
}

