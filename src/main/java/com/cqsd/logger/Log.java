package com.cqsd.logger;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
@Documented
public @interface Log {
	String value() default "";
	Level level() default Level.info;
	 enum Level{
		info,
		error,
		warn,
		debug,
		tarce,
	}
}
