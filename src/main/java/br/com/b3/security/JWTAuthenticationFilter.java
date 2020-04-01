package br.com.b3.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.b3.dto.CredenciaisDTO;

/**
 * Classe responsável por representar um filtro de autenticação Intercepta as
 * requisições de login.
 * 
 * @author j.a.vasconcelos
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private JWTUtil jwtUtil;

	/**
	 * Construtor da clase
	 * 
	 * @param authenticationManager
	 * @param jwtUtil
	 */
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	/**
	 * Realiza a autenticacao do sistema através do request.
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {

			// Metodo responsável por tentar autenticar
			CredenciaisDTO creds = new ObjectMapper().readValue(req.getInputStream(), CredenciaisDTO.class);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getLogin(),
					creds.getPassword());

			// Verifica se o usuario e senha sao validos.
			Authentication auth = authenticationManager.authenticate(authToken);

			return auth;

		} catch (IOException e) {

			throw new RuntimeException(e);
		}
	}

	/**
	 * Metodo responsável por retornar o token após a validação do login
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		// obtém o username logado
		String username = ((UserSS) auth.getPrincipal()).getUsername();
		// cria o token
		String token = jwtUtil.generateToken(username);
		// retorna para a header.
		res.addHeader("Authorization", "Bearer " + token);
		// libera o cors na aplicacao
		res.addHeader("access-control-expose-headers", "Authorization");
	}
}
