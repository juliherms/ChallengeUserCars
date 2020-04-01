package br.com.b3.dto;

import br.com.b3.entity.User;

/**
 * Representa um DTO de usuário logado
 * @author j.a.vasconcelos
 *
 */
public class CurrentUserDTO {

	private String token;
	private User user;

	public CurrentUserDTO(String token, User user) {
		this.token = token;
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
