package joachim.lejeune.peditrack.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Password must be at least 8 characters and contain an uppercase letter, a lowercase letter, a digit, and a special character (@#$%^&+=!*()_-)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}