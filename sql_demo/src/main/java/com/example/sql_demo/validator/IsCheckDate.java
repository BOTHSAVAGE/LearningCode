package com.example.sql_demo.validator;

import com.example.sql_demo.validator.annotion.checkDate;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class IsCheckDate implements ConstraintValidator<checkDate, Date> {

	private boolean required = false;
	
	public void initialize(checkDate constraintAnnotation) {
		required = constraintAnnotation.required();
	}

	public boolean isValid(Date value, ConstraintValidatorContext context) {
		if(required) {
			return new Date().before(value);
		}else {
			if(StringUtils.isEmpty(value)) {
				return true;
			}else {
				return new Date().before(value);
			}
		}
	}

}