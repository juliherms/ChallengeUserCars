package br.com.b3.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.b3.controller.response.Error;
import br.com.b3.controller.response.Response;
import br.com.b3.dto.CarDTO;
import br.com.b3.entity.Car;
import br.com.b3.service.CarService;

/**
 * Representa um controller de carros
 * 
 * @author j.a.vasconcelos
 *
 */
@RestController
@RequestMapping(value = "/api/cars")
public class CarController {

	@Autowired
	private CarService service;

	/**
	 * Metodo responsavel por listar todos os carros do usuario logado.
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Response<List<CarDTO>>> findAll() {

		Response<List<CarDTO>> response = new Response<List<CarDTO>>();

		List<Car> list = service.findByUser();

		List<CarDTO> listDTO = list.stream().map(obj -> new CarDTO(obj)).collect(Collectors.toList());

		response.setData(listDTO);

		return ResponseEntity.ok(response);
	}

	/**
	 * Metodo responsavel por cadastrar um carro de acordo com o usuario logado
	 * 
	 * @param usuario
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@Valid @RequestBody CarDTO carDTO) {

		Response<String> response = new Response<String>();

		Car obj = service.fromDTO(carDTO);

		ValdateUpdateCar(obj, response, false);

		if (!response.getErrors().isEmpty()) {
			return ResponseEntity.badRequest().body(response);
		}

		service.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	/**
	 * Metodo responsável por consultar um carro por Id informado de acordo com o
	 * usuario logado
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response<Car>> find(@PathVariable Long id) {

		Response<Car> response = new Response<Car>();

		Car obj = service.find(id);

		response.setData(obj);

		return ResponseEntity.ok(response);
	}

	/**
	 * Método responsável por deletar um carro de acordo com o seu id informado.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response<String>> delete(@PathVariable Long id) {
		Response<String> response = new Response<String>();

		Car obj = service.find(id);

		if (obj == null) {
			response.getErrors().add(new Error("Carro não encontrado", 1));
			return ResponseEntity.badRequest().body(response);
		}

		service.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}

	private void ValdateUpdateCar(Car carro, Response<String> response, boolean isUpdate) {

		if (service.findByLicensePlate(carro.getLicensePlate()) != null) {

			response.getErrors().add(new Error("License plate already exists", 3));
		}
	}

}
