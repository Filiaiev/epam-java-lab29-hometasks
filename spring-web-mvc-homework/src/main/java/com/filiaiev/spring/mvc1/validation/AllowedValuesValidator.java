package com.filiaiev.spring.mvc1.validation;

import com.filiaiev.spring.mvc1.util.Messages;
import com.filiaiev.spring.mvc1.validation.constraint.ValuesAllowed;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class AllowedValuesValidator implements ConstraintValidator<ValuesAllowed, String> {

    private List<String> expectedValues;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        boolean valid = expectedValues.contains(s);

        if(!valid) {
            context.disableDefaultConstraintViolation();
            String expectedPart = Messages.getMessage("messages.expected", LocaleContextHolder.getLocale());
            String butWasPart = Messages.getMessage("messages.but_was", LocaleContextHolder.getLocale());

            context.buildConstraintViolationWithTemplate(
                    expectedPart + ": " + expectedValues + " " + butWasPart + " '" + s + "'")
                    .addConstraintViolation();
        }
        return valid;
    }

    @Override
    public void initialize(ValuesAllowed constraintAnnotation) {
        expectedValues = Arrays.asList(constraintAnnotation.values());
    }
}
