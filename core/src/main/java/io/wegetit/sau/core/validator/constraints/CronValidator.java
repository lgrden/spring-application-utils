package io.wegetit.sau.core.validator.constraints;

import org.springframework.scheduling.support.CronExpression;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CronValidator implements ConstraintValidator<Cron, String> {

    private Cron constraint;

    @Override
    public void initialize(Cron constraint) {
        this.constraint = constraint;
    }

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
        try {
            CronExpression.parse(contactField);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}