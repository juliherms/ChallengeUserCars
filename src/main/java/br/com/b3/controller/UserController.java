package br.com.b3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.b3.controller.response.Response;
import br.com.b3.entity.User;
import br.com.b3.service.UserService;

/**
 * Representa um controller de usuários
 * 
 * @author j.a.vasconcelos
 *
 */
@RestController
@RequestMapping(value = "/api/users")
public class UserController {

	@Autowired
	private UserService service;

	/**
	 * Metodo responsavel por listar todos os usuarios da base.
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	/**
	 * Método responsável por deletar um usuario de acordo com o seu id informado.
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response<String>> delete(@PathVariable Long id) {
		Response<String> response = new Response<String>();

		User obj = service.findById(id);

		if (obj == null) {
			response.getErrors().add("Usuario não encontrado:" + id);
			return ResponseEntity.badRequest().body(response);
		}

		service.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}

	/// api/users/{id}

}
