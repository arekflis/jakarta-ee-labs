package pl.edu.pg.eti.kask.ucm.course.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.edu.pg.eti.kask.ucm.course.validation.binding.ValidCourseName;

public class CourseNameValidator implements ConstraintValidator<ValidCourseName, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        
        if (value.trim().length() < 3) {
            return false;
        }
        
        String trimmed = value.trim();
        if (!Character.isUpperCase(trimmed.charAt(0))) {
            return false;
        }
        
        return true;
    }
}

