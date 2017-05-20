package br.com.ido.qpedido.dao.nativequery;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface Conditions {
	Condition[] value();

	String valueIgnore() default "";

}
