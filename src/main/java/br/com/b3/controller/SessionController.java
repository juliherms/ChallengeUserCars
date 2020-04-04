package br.com.b3.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.b3.controller.response.Error;
import br.com.b3.controller.response.Response;
import br.com.b3.dto.CredenciaisDTO;
import br.com.b3.dto.CurrentUserDTO;
import br.com.b3.entity.User;
import br.com.b3.security.JwtTokenUtil;
import br.com.b3.service.UserService;

/**
 * Controller responsável por autenticação no sistema
 * 
 * @author j.a.vasconcelos
 *
 */
@RestController
@RequestMapping(value = "/api")
public class SessionController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/**
	 * Método responsável por realizar o login no sistema
	 * @param credenciaisDTO
	 * @return
	 */
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody CredenciaisDTO credenciaisDTO) {

		
		Response<String> response = new Response<String>();
		
		if(userService.findByLogin(credenciaisDTO.getLogin()) == null) {
			
			response.getErrors().add(new Error("Invalid login or password", 1));
			return ResponseEntity.badRequest().body(response);
		}
		
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(credenciaisDTO.getLogin(), credenciaisDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		final UserDetails userDetails = userDetailsService.loadUserByUsername(credenciaisDTO.getLogin());

		final String token = jwtTokenUtil.generateToken(userDetails);
		final User user = userService.findByLogin(credenciaisDTO.getLogin());
		user.setPassword(null);
		
		userService.saveLastLogin(user.getId());

		return ResponseEntity.ok(new CurrentUserDTO(token, user));
	}

	/**
	 * Método responsável por retornar as informações de um usuário logado.
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public ResponseEntity<User> me(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
        String login = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.findByLogin(login);
		
		return ResponseEntity.ok().body(user);
	}

}
