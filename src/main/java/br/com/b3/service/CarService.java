package br.com.b3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.b3.entity.Car;
import br.com.b3.repository.CarRepository;
import br.com.b3.repository.UserRepository;
import br.com.b3.security.AuthUtil;
import br.com.b3.security.UserSS;

/**
 * Classe responsável por implementar as regras de negócio de um carro.
 * 
 * @author j.a.vasconcelos
 *
 */
@Service
public class CarService {

	@Autowired
	private CarRepository repo;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Método responsável por listar todos os carros de acordo com o usuario
	 * informado.
	 * 
	 * @return Lista de carros.
	 */
	public List<Car> findByUser() {

		UserSS userSS = AuthUtil.authenticated();

		return repo.findByUser(userRepository.findByLogin(userSS.getUsername()));
	}
}
