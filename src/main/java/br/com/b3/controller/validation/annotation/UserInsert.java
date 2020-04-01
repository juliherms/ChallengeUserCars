package br.com.b3.controller.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.b3.controller.validation.UserInsertValidator;

/**
 * Anotation customizada para validação de insert de usuarios no sistema
 * 
 * @author j.a.vasconcelos
 *
 */

@Constraint(validatedBy = UserInsertValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserInsert {

	String message() default "Error message";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
