package br.com.b3.controller.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.b3.controller.exception.FieldMessage;
import br.com.b3.controller.validation.annotation.UserInsert;
import br.com.b3.dto.UserDTO;
import br.com.b3.repository.UserRepository;

/**
 * Classe responsável por realizar a validação do cadastro de usuários no
 * sistema
 * 
 * @author j.a.vasconcelos
 *
 */
public class UserInsertValidator implements ConstraintValidator<UserInsert, UserDTO> {

	@Autowired
	private UserRepository repo;

	public void initialize(UserInsert constraintAnnotation) {
		// TODO Auto-generated method stub

	}

	/**
	 * Verifica a entrada de um usuarios válido.
	 */

	public boolean isValid(UserDTO value, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<FieldMessage>();

		if (repo.findByLogin(value.getLogin()) != null) {
			list.add(new FieldMessage("login", "Login already exists"));
		}

		if (repo.findByEmail(value.getEmail()) != null) {
			list.add(new FieldMessage("email", "Email already exists"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
