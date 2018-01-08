package com.example.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.validator.constraint.NameConstraint;

public class EmployeeNameValidator implements ConstraintValidator<NameConstraint, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value != null){
			if(value.length()>5)
				return true;
		}
		return false;
	}

}
