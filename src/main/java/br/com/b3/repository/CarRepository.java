package br.com.b3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.b3.entity.Car;
import br.com.b3.entity.User;

/**
 * Classe responsavel por manipular os dados de um carro
 * 
 * @author j.a.vasconcelos
 *
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	/**
	 * Lista os carros de acordo com o usuario informado.
	 * @param user
	 * @return
	 */
	List<Car> findByUser(User user);

	/**
	 * Retorna um carro por id e usuario informado.
	 * @param user usuario logado
	 * @param id identifador do carro
	 * @return
	 */
	Car findByUserAndId(User user,Long id);
	
	/**
	 * Retorna um carro de acordo com a placa informada.
	 * @param licensePlate
	 * @return
	 */
	Car findByLicensePlate(String licensePlate);
	
}
