package br.com.b3.security;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Classe responsavel por retornar o usuario logado no sistema.
 * 
 * @author j.a.vasconcelos
 *
 */
public class AuthUtil {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
