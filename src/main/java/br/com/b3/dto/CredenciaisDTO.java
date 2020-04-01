package br.com.b3.dto;

import java.io.Serializable;

/**
 * Classe respons�vel por representar um DTO de autentica��o.
 * 
 * @author j.a.vasconcelos
 *
 */
public class CredenciaisDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;
	private String password;

	public CredenciaisDTO() {
	};

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

}
