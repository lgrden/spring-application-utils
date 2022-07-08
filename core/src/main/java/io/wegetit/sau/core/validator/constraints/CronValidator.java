package io.wegetit.sau.core.validator.constraints;

import io.wegetit.sau.core.utils.CronUtils;

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
        return CronUtils.isValid(contactField);
    }
}