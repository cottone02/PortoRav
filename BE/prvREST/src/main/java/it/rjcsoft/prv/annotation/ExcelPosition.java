package it.rjcsoft.prv.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import it.rjcsoft.prv.enums.FormatType;

@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelPosition {

	int position();

	String name();

	FormatType formatType();

	String dateFormat() default "";

}
