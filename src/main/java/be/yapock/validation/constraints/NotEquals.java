package be.yapock.validation.constraints;

import be.yapock.validation.validators.NotEqualsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEqualsValidator.class)
public @interface NotEquals {
    String value() default "test";
    String message() default "value can't be equal to a secret word";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
}
