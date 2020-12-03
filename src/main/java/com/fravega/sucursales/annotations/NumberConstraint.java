package com.fravega.sucursales.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;



import javax.validation.Constraint;
import javax.validation.Payload;

import com.fravega.sucursales.validators.NumberValidator;

@Documented
@Constraint(validatedBy = NumberValidator.class)
@Target( { ElementType.PARAMETER , ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberConstraint {

	String message() default "Debe ingresar un valor numerico";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
}
