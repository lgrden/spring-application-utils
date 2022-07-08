package io.wegetit.sau.core.validator.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { CronValidator.class })
public @interface Cron {
    String message() default "{invalid.cron}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
