package br.com.b3.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.b3.controller.response.Response;
import br.com.b3.dto.UserDTO;
import br.com.b3.entity.User;
import br.com.b3.service.UserService;

/**
 * Representa um controller de usu�rios
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
	 * Metodo respons�vel por atualizar um usuario de acordo com o id informado.
	 * @param objDto
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody UserDTO objDto, @PathVariable Long id) {

		User obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Metodo respons�vel por consultar um usuario por Id informado.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> find(@PathVariable Long id) {
		User obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	/**
	 * Metodo responsavel por registrar/cadastrar um usuario na aplicacao
	 * 
	 * @param usuario
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> registrar(@Valid @RequestBody UserDTO userDTO) {

		User obj = service.fromDTO(userDTO);

		service.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

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
	 * M�todo respons�vel por deletar um usuario de acordo com o seu id informado.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response<String>> delete(@PathVariable Long id) {
		Response<String> response = new Response<String>();

		User obj = service.findById(id);

		if (obj == null) {
			response.getErrors().add("Usuario n�o encontrado:" + id);
			return ResponseEntity.badRequest().body(response);
		}

		service.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}

}
