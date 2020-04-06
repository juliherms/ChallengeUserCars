package br.com.b3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.b3.dto.CarDTO;
import br.com.b3.entity.Car;
import br.com.b3.entity.User;
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

	/**
	 * Converte um objeto DTO para Car
	 * 
	 * @param objDto
	 * @return
	 */
	public Car fromDTO(CarDTO objDto) {

		Car car = new Car();
		car.setColor(objDto.getColor());
		car.setLicensePlate(objDto.getLicensePlate());
		car.setModel(objDto.getModel());
		car.setYear(objDto.getYear());

		return car;
	}
	
	/**
	 * Método responsável por atualizar um carro
	 * 
	 * @param obj - carro
	 * @return carro atualizado.
	 */
	public Car update(Car obj) {
		
		Car newObj = repo.findOne(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Car newObj, Car obj) {

		newObj.setId(obj.getId());
		newObj.setColor(obj.getColor());
		newObj.setLicensePlate(obj.getLicensePlate());
		newObj.setModel(obj.getModel());
		newObj.setYear(obj.getYear());
	}


	/**
	 * Método responsável por persistir um carro
	 * 
	 * @param obj
	 * @return
	 */
	public Car insert(Car obj) {

		UserSS userSS = AuthUtil.authenticated();

		obj.setUser(userRepository.findByLogin(userSS.getUsername()));

		obj.setId(null); // força um insert
		repo.save(obj);

		return obj;
	}

	/**
	 * Método responsável por retornar um carro de acordo com o id informado
	 * 
	 * @param id
	 * @return
	 */
	public Car find(Long id) {

		UserSS userSS = AuthUtil.authenticated();
		User user = userRepository.findByLogin(userSS.getUsername());

		return repo.findByUserAndId(user, id);
	}
	
	/**
	 * Método responsável por retornar um carro de acordo com a placa informada.
	 * @param licensePlate
	 * @return
	 */
	public Car findByLicensePlate(String licensePlate) {
		return repo.findByLicensePlate(licensePlate);
	}

	/**
	 * Método responsavel por deletar um carro.
	 * 
	 * @param id
	 */
	public void delete(Long id) {

		Car car = repo.findOne(id);
		User user = userRepository.findByLogin(car.getUser().getLogin());

		user.getCars().remove(car);

		userRepository.save(user);

		repo.delete(car);
	}
}
