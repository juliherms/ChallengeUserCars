package br.com.b3.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.b3.controller.validation.annotation.UserInsert;
import br.com.b3.entity.Car;
import br.com.b3.entity.User;

/**
 * Classe representa um DTO para validação dos dados de entrada
 * 
 * @author j.a.vasconcelos
 *
 */
@UserInsert()
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 4 e 10 caracteres")
	private String firstName;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 4 e 10 caracteres")
	private String lastName;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email
	private String email;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String birthday;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 4 e 10 caracteres")
	private String login;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 4 e 10 caracteres")
	private String password;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String phone;
	
	List<CarDTO> Cars = new ArrayList<CarDTO>();
	

	public UserDTO() {
	
	}

	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
	//	this.birthday = user.getBirthday();
		this.login = user.getLogin();
	//	this.password = user.getP;
		this.phone = user.getPhone();
		
		List<CarDTO> list = new ArrayList<CarDTO>();
		
		for (Car car : user.getCars()) {
			
			CarDTO obj = new CarDTO(car);
			list.add(obj);
		}
		
		this.Cars = list;
		
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<CarDTO> getCars() {
		return Cars;
	}

	public void setCars(List<CarDTO> cars) {
		Cars = cars;
	}

}
