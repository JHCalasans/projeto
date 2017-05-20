package br.com.ido.qpedido.dao.nativequery;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface OperatorCondition {
	
	OperatorEnum operator();
	
	public enum OperatorEnum {

		GREATER_EQUAL(">="), 
		LESS_EQUAL("<="), 
		GREATER(">"), 
		LESS("<"), 
		EQUAL("="), 
		LIKE(" like "), 
		CONDITION(" ");

		private final String value;

		private OperatorEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}
}
