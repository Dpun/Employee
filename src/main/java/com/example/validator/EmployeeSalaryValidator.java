package com.example.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.validator.constraint.SalaryConstraint;

public class EmployeeSalaryValidator implements ConstraintValidator<SalaryConstraint, Integer> {

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if(value != null && value !=0){
			if(value>5000 && value<50000){
				return true;
			}
		}
		return false;
	}

}
