package br.com.b3.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.b3.dto.CarDTO;
import br.com.b3.dto.UserDTO;
import br.com.b3.entity.Car;
import br.com.b3.entity.User;
import br.com.b3.repository.CarRepository;
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
	
	@Autowired
	private CarRepository carRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * M�todo responsavel por salvar o �ltimo login do usuario.
	 * 
	 * @param id
	 * @return
	 */
	public User saveLastLogin(Long id) {

		User obj = repo.findOne(id);
		obj.setLastLogin(new Date());

		return repo.save(obj);
	}

	/**
	 * M�todo respons�vel por retornar um usuario de acordo com o id informado
	 * 
	 * @param id
	 * @return
	 */
	public User find(Long id) {
		return repo.findOne(id);
	}

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
		repo.save(obj);
		
		for (Car car : obj.getCars()) {
			carRepository.save(car);
		}
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
	 * M�todo respons�vel por consultar um usu�ro por login informado
	 * 
	 * @param login
	 * @return
	 */
	public User findByLogin(String login) {
		return repo.findByLogin(login);
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
	
	/**
	 * Converte um objeto DTO para User
	 * 
	 * @param objDto
	 * @return
	 */
	public User fromDTO(UserDTO objDto) {

		User user = new User();
		user.setEmail(objDto.getEmail());
		user.setId(objDto.getId());
		user.setLastName(objDto.getLastName());
		user.setLogin(objDto.getLogin());
		user.setFirstName(objDto.getFirstName());
		user.setPassword(passwordEncoder.encode(objDto.getPassword()));
		user.setPhone(objDto.getPhone());
		
		for (CarDTO carDTO : objDto.getCars()) {
			
			Car car = new Car();
			car.setColor(carDTO.getColor());
			car.setLicensePlate(carDTO.getLicensePlate());
			car.setModel(carDTO.getModel());
			car.setYear(carDTO.getYear());
			
			user.addCar(car);
		}
	
		return user;
	}
}
