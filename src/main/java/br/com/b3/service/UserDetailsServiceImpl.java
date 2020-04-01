package br.com.b3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.b3.entity.User;
import br.com.b3.repository.UserRepository;
import br.com.b3.security.UserSS;

/**
 * Representa a implementação da classe userDetailsService do Spring Security.
 * Repsonsavel por realizar a autenticacao.
 * 
 * @author j.a.vasconcelos
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repo;

	/**
	 * Metodo responsavel por realizar a autenticacao via spring security.
	 */
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		User usu = repo.findByLogin(login);

		if (usu == null) {
			throw new UsernameNotFoundException("Invalid login or password");
		}
		// Retorna o usuario encontrado(classe customizada).
		return new UserSS(usu.getId(), usu.getLogin(), usu.getPassword());
	}
}
