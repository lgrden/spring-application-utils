package io.wegetit.sau.core.message;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Locale;

@Data
public class MessageResolverProperties {
    private Locale defaultLocale;
    @NotEmpty
    private List<String> baseNames;
}
