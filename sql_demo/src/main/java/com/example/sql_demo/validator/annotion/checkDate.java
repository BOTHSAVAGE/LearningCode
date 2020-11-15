package com.example.sql_demo.validator.annotion;

import com.example.sql_demo.validator.IsCheckDate;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Future;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {IsCheckDate.class })
public @interface  checkDate {
	boolean required() default true;
	
	String message() default "日期不能早于当前日期";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}