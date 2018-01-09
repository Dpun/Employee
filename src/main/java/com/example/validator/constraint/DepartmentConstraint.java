package com.example.validator.constraint;

import com.example.validator.DepartmentValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DepartmentValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DepartmentConstraint {
    String message() default "Please assign department to Employee.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
