package com.example.validator;

import com.example.validator.constraint.DepartmentConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DepartmentValidator implements ConstraintValidator<DepartmentConstraint,Integer> {

    @Override
    public void initialize(DepartmentConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if(value != null){
            if(value > 0)
                return true;
        }
        return false;
    }
}
