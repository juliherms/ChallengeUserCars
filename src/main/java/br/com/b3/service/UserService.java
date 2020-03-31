package br.com.b3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.b3.entity.User;
import br.com.b3.repository.UserRepository;

/**
 * Classe respons�vel por implementar as regras de neg�cio de um usuario.
 * 
 * @author j.a.vasconcelos
 *
 */
@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	//@Autowired
	//private PasswordEncoder passwordEncoder;

	/**
	 * M�todo respons�vel por listar todos os usuarios do banco de dados.
	 * 
	 * @return Lista de usuarios.
	 */
	public List<User> findAll() {
		return repo.findAll();
	}

	/**
	 * M�todo respons�vel por persistir um usu�rio.
	 * 
	 * @param obj
	 * @return
	 */
	public User insert(User obj) {
		obj.setId(null); // for�a um insert
		//obj.setPassword(passwordEncoder.encode(obj.getPassword()));
		repo.save(obj);
		return obj;
	}

	/**
	 * M�todo respons�vel por consultar um usuario por id
	 * 
	 * @param id - codigo do usuario
	 * @return Objeto usuario
	 */
	public User findById(Long id) {

		User obj = repo.findOne(id);
		return obj;
	}

	/**
	 * M�todo responsavel por deletar um usuario.
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		repo.delete(repo.findOne(id));
	}

	/**
	 * M�todo respons�vel por atualizar um usuario
	 * 
	 * @param obj - usuario
	 * @return usuario atualizado.
	 */
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(User newObj, User obj) {

		newObj.setId(obj.getId());
		newObj.setLogin(obj.getLogin());
	}
}
