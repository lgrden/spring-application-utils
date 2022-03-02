package io.wegetit.sau.core.validator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class ValidationResult {

    private List<ValidationEntry> list;

    public boolean hasErrors() {
        return !CollectionUtils.isEmpty(list);
    }
}

