package pl.edu.pg.eti.kask.ucm.course.validation.binding;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import pl.edu.pg.eti.kask.ucm.course.validation.validator.CourseNameValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CourseNameValidator.class)
@Documented
public @interface ValidCourseName {
    String message() default "{course.validation.name.invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

