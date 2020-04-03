package br.com.b3.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<List<CarDTO>> findAll() {

		List<Car> list = service.findByUser();
		
		List<CarDTO> listDTO = list.stream().map(obj -> new CarDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok(listDTO);
	}
	
	/**
	 * Metodo responsavel por cadastrar um carro de acordo com o usuario logado
	 * 
	 * @param usuario
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody CarDTO carDTO) {

		Car obj = service.fromDTO(carDTO);

		service.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}



}
