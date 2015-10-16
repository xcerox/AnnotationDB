package com.help.db.annotacion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	public ColumnType type();
	public int value() default 0;
	public String name() default "";
	public Constraint constraint() default @Constraint;
}
