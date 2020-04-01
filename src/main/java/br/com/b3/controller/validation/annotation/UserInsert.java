package br.com.b3.controller.validation.annotation;

import javax.validation.Payload;

/**
 * Anotation customizada para validação de insert de usuarios no sistema
 * @author j.a.vasconcelos
 *
 */
public @interface UserInsert {

	String message() default "Error message";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
