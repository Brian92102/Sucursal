package com.fravega.sucursales.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.fravega.sucursales.annotations.NumberConstraint;

public class NumberValidator implements 
ConstraintValidator<NumberConstraint, String> {
		
	@Override
	public void initialize(NumberConstraint arg0) {
		
	}

	@Override
	public boolean isValid(String nro, ConstraintValidatorContext cxt) {
			
		return StringUtils.isNumeric(nro);
	}

}
