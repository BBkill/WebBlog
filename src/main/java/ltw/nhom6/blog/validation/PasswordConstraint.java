package ltw.nhom6.blog.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {PasswordConstraint.PasswordValidator.class})
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default "password must have at least 8 characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

        @Override
        public void initialize(PasswordConstraint constraintAnnotation) {
        }

        @Override
        public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
            return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$") ;
        }
    }
}
