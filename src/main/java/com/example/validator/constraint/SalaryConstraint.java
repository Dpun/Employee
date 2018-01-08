package com.example.validator.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.validator.EmployeeSalaryValidator;

@Documented
@Constraint(validatedBy=EmployeeSalaryValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SalaryConstraint {
	String message() default "Salary must be between 5000 and 50000.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
